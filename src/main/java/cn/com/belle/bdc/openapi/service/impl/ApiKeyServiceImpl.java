package cn.com.belle.bdc.openapi.service.impl;

import cn.com.belle.bdc.openapi.dao.mysql.ApiKeyDao;
import cn.com.belle.bdc.openapi.model.ApiKey;
import cn.com.belle.bdc.openapi.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

//	private Logger logger = Logger.getLogger(ApiKeyServiceImpl.class);
	@Autowired
	private ApiKeyDao apiKeyDao;

	@Override
	public int insert(ApiKey record) {
		return apiKeyDao.insert(record);
	}

	@Override
	public List<Map<String, Object>> queryApiKeyMetadata() {
		return apiKeyDao.queryApiKeyMetadata();
	}

	@Override
	public List<Map<String, Object>> isApiEnable() {
		return apiKeyDao.isApiEnable();
	}

	@Override
	public List<Map<String, Object>> queryApiLicenseList() {
		return apiKeyDao.queryApiLicenseList();
	}
}
