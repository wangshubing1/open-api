package cn.com.belle.bdc.openapi.conf.interceptor;

import cn.com.belle.bdc.openapi.common.Constants;
import cn.com.belle.bdc.openapi.common.exception.SignErrorException;
import cn.com.belle.bdc.openapi.service.ApiKeyService;
import cn.com.belle.bdc.openapi.util.MD5Encryptor;
import cn.com.belle.bdc.openapi.util.SHA1Encryptor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class SignInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(SignInterceptor.class);
    private static final String API_KEYMETADATA_REDIS_KEY = "api_keymetadata_redis_key";
    private static final String API_LICENSE_REDIS_KEY = "api_license_redis_key";
    public static final String API_ENABLE_REDIS_KEY = "api_enable_redis_key";
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Resource
    private ApiKeyService apiKeyService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String,String[]> params = request.getParameterMap();
        //先签名验证
        String signc = params.get(Constants.SIGN_NAME)==null?null:params.get(Constants.SIGN_NAME)[0];
        String appKey = params.get(Constants.APP_KEY)==null?null:params.get(Constants.APP_KEY)[0];
        String signMethod = params.get(Constants.SIGN_METHOD)==null?null:params.get(Constants.SIGN_METHOD)[0];
        if(StringUtils.isEmpty(signMethod)){//默认为md5
            signMethod = Constants.MD5;
        }
        Map<String, Object> map = this.getApikeyMetadataById(appKey);
        if (MapUtils.isEmpty(map)) {
            logger.error("提供的app_key:"+appKey+"未能被数据中台开放平台识别");
            throw  new SignErrorException("您提供的app_key:"+appKey+"未能被数据中台开放平台识别,请确认app_key是否正确.");
        }
        if ("0".equals(MapUtils.getString(map, "status"))) {
            logger.error("提供的app_key:"+appKey+"已关闭禁用");
            throw  new SignErrorException("您提供的app_key:"+appKey+"已关闭禁用,请联系数据中台开放平台相关人员.");
        }
        if (signMethod != null && !Constants.MD5.equalsIgnoreCase(signMethod) && !Constants.SHA1.equalsIgnoreCase(signMethod)) {
            logger.error("签名加密方法错误");
            throw  new SignErrorException("签名加密方法错误,输入参数签名加密方法只支持[md5,sha-1].");
        }
        if (!signc.equals(toEncryptKey(params, MapUtils.getString(map, "app_secret"), signMethod))) {
            logger.error("签名错误");
            throw  new SignErrorException("输入参数签名加密错误,请联系数据中台开放平台技术支持.");
        }

        //然后权限验证
        String method = params.get(Constants.METHOD)==null?null:params.get(Constants.METHOD)[0];
        // 校验API是否启用,并根据方法名返回apiid
        String apiId = this.isApiEnable(method);
        if (StringUtils.isEmpty(apiId)) {
            logger.error("API已被禁用或无对应此方法的api明细信息");
            throw  new SignErrorException("API已被禁用或无对应此方法的api明细信息");
        }

        // 校验商家是否已获得访问API的授权
        if (!this.isLicense(apiId, appKey)) {
            logger.error("无权访问该API");
            throw  new SignErrorException("您无权访问该API.");
        }
        return true;

    }
    private Map<String, Object> getApikeyMetadataById(String appKey) {
        List<Map<String, Object>> list = null;
        //先从缓存取
        try {
            list = (List<Map<String, Object>>) redisTemplate.opsForValue().get(API_KEYMETADATA_REDIS_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //若为空再从数据库取
        if (CollectionUtils.isEmpty(list)) {
            list = apiKeyService.queryApiKeyMetadata();
            redisTemplate.opsForValue().set(API_KEYMETADATA_REDIS_KEY, list, 5, TimeUnit.MINUTES);//缓存
            logger.info(MessageFormat.format("重新查询API_KEYMETADATA_REDIS_KEY加载到缓存！总共：{0}。", CollectionUtils.isNotEmpty(list) ? list.size() : 0));
        }

        if (CollectionUtils.isNotEmpty(list)) {
            return this.getApikeyMetadataById(list, appKey);
        }

        return null;
    }
    private Map<String, Object> getApikeyMetadataById(List<Map<String, Object>> objs, String appKey) {
        if (CollectionUtils.isNotEmpty(objs)) {
            for (Map<String, Object> apiKeyMetadata : objs) {
                if (appKey.equals(MapUtils.getString(apiKeyMetadata, "app_key"))) {
                    return apiKeyMetadata;
                }
            }
        }

        return null;
    }
    /**
     * 签名加密
     *
     * @param context
     * @param token
     * @param signMethod
     * @return String
     */
    private String toEncryptKey(Map<String, String[]> context, String token, String signMethod) {
        int paramIndex = 0;
        String[] params = new String[context.size() - 1];
        for (Map.Entry<String, String[]> entry : context.entrySet()) {
            if ("sign".equalsIgnoreCase(entry.getKey())) {
                continue;
            }
            params[paramIndex++] = entry.getKey() + entry.getValue()[0];
        }

        Arrays.sort(params);
        token += StringUtils.join(params, "");
        if(Constants.SHA1.equals(signMethod)){
            logger.info(SHA1Encryptor.encrypt(token));
        }else{
            logger.info(MD5Encryptor.encrypt(token));
        }
        return Constants.SHA1.equals(signMethod) ? SHA1Encryptor.encrypt(token) : MD5Encryptor.encrypt(token);
    }
    /**
     * 判断API接口是否启用
     *
     * @param method
     * @return apiid
     */
    private String isApiEnable(String method) {
        if (StringUtils.isBlank(method))
            return null;

        List<Map<String, Object>> list = null;
        try {
            list = (List<Map<String, Object>>) redisTemplate.opsForValue().get(API_ENABLE_REDIS_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(list)) {
            //查询数据库
            list = new ArrayList<Map<String, Object>>();
            try {
                // 校验商家是否已获得访问API的授权
                list = apiKeyService.isApiEnable();
                redisTemplate.opsForValue().set(API_ENABLE_REDIS_KEY, list, 5, TimeUnit.MINUTES);
                logger.info(MessageFormat.format("重新查询API_ENABLE_REDIS_KEY加载到缓存！总共：{0}。", CollectionUtils.isNotEmpty(list) ? list.size() : 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return this.isEnableApi(method, list);
    }
    private String isEnableApi(String method, List<Map<String, Object>> list) {
        if (CollectionUtils.isEmpty(list))
            return null;

        for (Map<String, Object> map : list) {
            if (method.equals(MapUtils.getString(map, "api_method"))) {
                String isEnable = MapUtils.getString(map, "is_enable");
                Boolean flag =  "0".equals(isEnable) ? true : false;
                if(flag){
                    return MapUtils.getString(map, "id");
                }
            }
        }

        return null;
    }
    /**
     * 判断商家是否已经获得访问API的授权
     *
     * @param apiId
     * @param appKey
     * @return
     */
    private boolean isLicense(String apiId, String appKey) {
        List<Map<String, Object>> list = null;
        try {
            list = (List<Map<String, Object>>) redisTemplate.opsForValue().get(API_LICENSE_REDIS_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(list)) {
            list = apiKeyService.queryApiLicenseList();
            redisTemplate.opsForValue().set(API_LICENSE_REDIS_KEY, list, 5, TimeUnit.MINUTES);
            logger.info(MessageFormat.format("重新查询API_LICENSE_REDIS_KEY加载到缓存！总共：{0}。", CollectionUtils.isNotEmpty(list) ? list.size() : 0));
        }

        return this.containValue(apiId, appKey, list);
    }
    private boolean containValue(String apiId, String appKey, List<Map<String, Object>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }

        for (Map<String, Object> apiLicense : list) {
            if (apiId.equals(MapUtils.getString(apiLicense, "api_id")) && appKey.equals(MapUtils.getString(apiLicense, "app_key"))) {
                return true;
            }
        }

        return false;
    }
}
