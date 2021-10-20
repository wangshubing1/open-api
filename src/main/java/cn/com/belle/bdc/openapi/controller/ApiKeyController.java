package cn.com.belle.bdc.openapi.controller;


import cn.com.belle.bdc.openapi.model.ApiKey;
import cn.com.belle.bdc.openapi.service.ApiKeyService;
import cn.com.belle.bdc.openapi.util.DateUtil;
import cn.com.belle.bdc.openapi.util.MD5Encryptor;
import cn.com.belle.bdc.openapi.util.UUIDUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/openapimgt/apikey")
public class ApiKeyController {

//    private static final Logger LOGGER = Logger.getLogger(ApiKeyController.class);

    @Resource
    private ApiKeyService apiKeyService;

    /**
     * 生成API密钥信息  http://localhost:8888/bdc-open-api/openapimgt/apikey/generateApiKey?sign=23332423
     *
     * @param
     * @param request
     * @return String
     */
    @RequestMapping("/generateApiKey")
    public void generateApiKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            // 生成密匙
            String appKey = new java.rmi.server.UID().toString().replaceAll("(:|-)", "_");
            // 生成密匙口令
            List<String> appSecretSections = Arrays.asList(appKey.split(""));
            Collections.shuffle(appSecretSections);
            String appSecret = MD5Encryptor.encrypt(appSecretSections.toString());
            ApiKey apiKey = new ApiKey();
            apiKey.setId(UUIDUtil.getUUID());
            apiKey.setAppKey(appKey);
            apiKey.setAppSecret(appSecret);
            apiKey.setStatus("关闭");
            apiKey.setUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
            apiKey.setUpdateUser("admin");
            apiKeyService.insert(apiKey);
//            JsonConfig jsonConfig = new JsonConfig();
//            jsonConfig.setExcludes(new String[] { "apiKeyMetadatas" });
//            IOUtils.write(JSONObject.fromObject(apiKey, jsonConfig).toString(), response.getWriter());

//            MerchantOperationLog operationLog=new MerchantOperationLog();
//            operationLog.setId(UUIDUtil.getUUID());
//            operationLog.setMerchantCode(appKey);
//            operationLog.setOperated(new Date());
//            operationLog.setOperationNotes("新建appkey");
//            operationLog.setOperationType(OperationType.AppKey_ADD);
//            operationLog.setOperator(user.getUsername());
//            merchantOperationLogService.saveMerchantOperationLog(operationLog);
        } catch (Exception ex) {
//            LOGGER.error(ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            IOUtils.write("生成AppKey异常", response.getWriter());
        }
    }

}
