package cn.com.belle.bdc.openapi.service;

import cn.com.belle.bdc.openapi.model.QueryParams;

import java.util.List;
import java.util.Map;

public interface DaogoubaoService {
    //T-1
    List<Map<String, Object>> getStoreSalamtsame(QueryParams queryParams, String cacheKey);

    List<Map<String, Object>> getRegionBrandSalamtsame(QueryParams queryParams, String cacheKey);

    List<Map<String, Object>> getStoreDoubleorder(QueryParams queryParams, String cacheKey);

    List<Map<String, Object>> getRegionBrandDoubleorder(QueryParams queryParams, String cacheKey);

    List<Map<String, Object>> getStoreOrders(QueryParams queryParams, String cacheKey);

    List<Map<String, Object>> getAssistantOrders(QueryParams queryParams, String cacheKey);

    List<Map<String, Object>> getStoreSalamt(QueryParams queryParams, String cacheKey);

    List<Map<String, Object>> getAssistantSalamt(QueryParams queryParams, String cacheKey);

    List<Map<String, Object>> getStoreSalCompletionRate(QueryParams queryParams, String cacheKey);

    List<Map<String, Object>> getAssistantSalCompletionRate(QueryParams queryParams, String cacheKey);

    //实时
    List<Map<String, Object>> getStoreSaleDayTarget(QueryParams queryParams);

    List<Map<String, Object>> getStoreSaleMonthTarget(QueryParams queryParams);

    List<Map<String, Object>> getAssistantSaleDayTarget(QueryParams queryParams);

    List<Map<String, Object>> getStoreSaleCurrentDayTarget(QueryParams queryParams);

    List<Map<String, Object>> getAssistantSaleCurrentDayTarget(QueryParams queryParams);
}
