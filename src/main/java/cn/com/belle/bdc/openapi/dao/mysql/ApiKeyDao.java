package cn.com.belle.bdc.openapi.dao.mysql;

import cn.com.belle.bdc.openapi.model.ApiKey;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface ApiKeyDao {

    int insert(ApiKey record);

    List<Map<String, Object>> queryApiKeyMetadata();

    List<Map<String, Object>> isApiEnable();

    List<Map<String, Object>> queryApiLicenseList();
}

