package cn.com.belle.bdc.openapi.service;

import java.util.List;
import java.util.Map;

public interface CacheService {

    List<Map<String, Object>> queryUpdateTime(Map<String, Object> params);

    List<Map<String, Object>> queryEndTime(Map<String, Object> params);

}
