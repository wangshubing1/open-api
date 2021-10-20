package cn.com.belle.bdc.openapi.service;

import cn.com.belle.bdc.openapi.model.ApiKey;

import java.util.List;
import java.util.Map;

public interface ApiKeyService {

	int insert(ApiKey record);

	List<Map<String, Object>> queryApiKeyMetadata();

	List<Map<String, Object>> isApiEnable();

	List<Map<String, Object>> queryApiLicenseList();
}
