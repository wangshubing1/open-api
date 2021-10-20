package cn.com.belle.bdc.openapi.dao.impala;

import cn.com.belle.bdc.openapi.model.QueryParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface DaogoubaoDao {
    List<Map<String, Object>> getStoreSalamtsame(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getRegionBrandSalamtsame(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getStoreDoubleorder(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getRegionBrandDoubleorder(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getStoreOrders(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getAssistantOrders(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getStoreSalamt(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getAssistantSalamt(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getStoreSalCompletionRate(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getAssistantSalCompletionRate(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getStoreSaleDayTarget(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getStoreSaleMonthTarget(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getAssistantSaleDayTarget(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getStoreSaleCurrentDayTarget(@Param("params") QueryParams queryParams);

    List<Map<String, Object>> getAssistantSaleCurrentDayTarget(@Param("params") QueryParams queryParams);
}
