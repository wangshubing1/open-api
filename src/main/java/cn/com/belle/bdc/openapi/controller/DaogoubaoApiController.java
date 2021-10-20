package cn.com.belle.bdc.openapi.controller;


import cn.com.belle.bdc.openapi.common.exception.BaseApiException;
import cn.com.belle.bdc.openapi.conf.interceptor.SignInterceptor;
import cn.com.belle.bdc.openapi.model.QueryParams;
import cn.com.belle.bdc.openapi.model.QueryReturnInfo;
import cn.com.belle.bdc.openapi.service.DaogoubaoService;
import cn.com.belle.bdc.openapi.util.CommonUtil;
import cn.com.belle.bdc.openapi.util.QueryReturnUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bdc_daogoubao")
public class DaogoubaoApiController {
    private static Logger logger = LoggerFactory.getLogger(SignInterceptor.class);

    @Autowired
    DaogoubaoService daogoubaoService;

    //*********************************************T-1数据*******************************************
    /**
     *导购宝-销额同比（到店）
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/store_salamtsame_get")
    @ResponseBody
    public QueryReturnInfo queryStoreSalamtsame(@Valid QueryParams queryParams,
                                                BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //校验必选参数
            this.validateParam(queryParams);
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getStoreSalamtsame(queryParams, CommonUtil.transMapToString(queryParams));

            //返回json格式

            //返回xml格式

            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *导购宝-销额同比（到品牌到大区）
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/regionBrand_salamtsame_get")
    @ResponseBody
    public QueryReturnInfo queryRegionBrandSalamtsame(@Valid QueryParams queryParams,
                                                BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getRegionBrandSalamtsame(queryParams, CommonUtil.transMapToString(queryParams));
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *导购宝-孖单数（到店）
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/store_doubleorder_get")
    @ResponseBody
    public QueryReturnInfo queryStoreDoubleorder(@Valid QueryParams queryParams,
                                                BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getStoreDoubleorder(queryParams, CommonUtil.transMapToString(queryParams));
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *导购宝-孖单数（到品牌到大区）
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/regionBrand_doubleorder_get")
    @ResponseBody
    public QueryReturnInfo queryRegionBrandDoubleorder(@Valid QueryParams queryParams,
                                                      BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getRegionBrandDoubleorder(queryParams, CommonUtil.transMapToString(queryParams));
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/store_orders_get")
    @ResponseBody
    public QueryReturnInfo queryStoreOrders(@Valid QueryParams queryParams,
                                                 BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getStoreOrders(queryParams, CommonUtil.transMapToString(queryParams));
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/assistant_orders_get")
    @ResponseBody
    public QueryReturnInfo queryAssistantOrders(@Valid QueryParams queryParams,
                                                       BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getAssistantOrders(queryParams, CommonUtil.transMapToString(queryParams));
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/store_salamt_get")
    @ResponseBody
    public QueryReturnInfo queryStoreSalamt(@Valid QueryParams queryParams,
                                            BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getStoreSalamt(queryParams, CommonUtil.transMapToString(queryParams));
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/assistant_salamt_get")
    @ResponseBody
    public QueryReturnInfo queryAssistantSalamt(@Valid QueryParams queryParams,
                                                BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getAssistantSalamt(queryParams, CommonUtil.transMapToString(queryParams));
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/store_sal_completion_rate_get")
    @ResponseBody
    public QueryReturnInfo queryStoreSalCompletionRate(@Valid QueryParams queryParams,
                                            BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getStoreSalCompletionRate(queryParams, CommonUtil.transMapToString(queryParams));
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/assistant_sal_completion_rate_get")
    @ResponseBody
    public QueryReturnInfo queryAssistantSalCompletionRate(@Valid QueryParams queryParams,
                                                BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getAssistantSalCompletionRate(queryParams, CommonUtil.transMapToString(queryParams));
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     * 校验参数
     * @param
     * @throws
     */
    private void validateParam(QueryParams params){
        //method

        //app_key

        //sign

        //sign_method

        //format（xml、json）
    }

    /**
     * @Title: initParamsMap
     * @Description: 初始化参数
     * @param @param queryParams
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @throws
     */
    private QueryParams initParamsMap(QueryParams queryParams) {
        String store_nos = queryParams.getStore_no();
        if(StringUtils.isNotEmpty(store_nos)){
            store_nos = store_nos.replace(",","','");
            store_nos = "'" + store_nos + "'";
            queryParams.setStore_no(store_nos);
        }
        String region_no = queryParams.getRegion_no();
        if(StringUtils.isNotEmpty(region_no)){
            region_no = region_no.replace(",","','");
            region_no = "'" + region_no + "'";
            queryParams.setRegion_no(region_no);
        }
        String brand_no= queryParams.getBrand_no();
        if(StringUtils.isNotEmpty(brand_no)){
            brand_no = brand_no.replace(",","','");
            brand_no = "'" + brand_no + "'";
            queryParams.setBrand_no(brand_no);
        }
        String staff_id = queryParams.getStaff_id();
        if(StringUtils.isNotEmpty(staff_id)){
            staff_id = staff_id.replace(",","','");
            staff_id = "'" + staff_id + "'";
            queryParams.setStaff_id(staff_id);
        }
        return queryParams;
    }

    //*******************************************实时数据******************************************
    /**
     *
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/store_sale_day_target_get")
    @ResponseBody
    public QueryReturnInfo queryStoreSaleDayTarget(@Valid QueryParams queryParams,
                                            BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getStoreSaleDayTarget(queryParams);
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/store_sale_month_target_get")
    @ResponseBody
    public QueryReturnInfo queryStoreSaleMonthTarget(@Valid QueryParams queryParams,
                                                   BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getStoreSaleMonthTarget(queryParams);
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/assistant_sale_day_target_get")
    @ResponseBody
    public QueryReturnInfo queryAssistantSaleDayTarget(@Valid QueryParams queryParams,
                                                   BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getAssistantSaleDayTarget(queryParams);
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/store_sale_currentday_target_get")
    @ResponseBody
    public QueryReturnInfo queryStoreSaleCurrentDayTarget(@Valid QueryParams queryParams,
                                                   BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getStoreSaleCurrentDayTarget(queryParams);
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }

    /**
     *
     * @param queryParams
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/assistant_sale_currentday_target_get")
    @ResponseBody
    public QueryReturnInfo queryAssistantSaleCurrentDayTarget(@Valid QueryParams queryParams,
                                                       BindingResult bindingResult, HttpServletRequest request)  throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                return QueryReturnUtil.fail(error.getDefaultMessage());
            }
        }

        try{
            //参数处理
            queryParams = this.initParamsMap(queryParams);
            //查询出数据
            List<Map<String, Object>> datas = daogoubaoService.getAssistantSaleCurrentDayTarget(queryParams);
            return QueryReturnUtil.success(datas);

        } catch (Exception e) {
            logger.error("接口查询异常"+e.getStackTrace());
            throw  new BaseApiException("接口查询异常.");
        }
    }
}
