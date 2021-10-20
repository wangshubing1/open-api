package cn.com.belle.bdc.openapi.service.impl;

import cn.com.belle.bdc.openapi.dao.impala.ImpalaCacheDao;
import cn.com.belle.bdc.openapi.dao.mysql.MysqlCacheDao;
import cn.com.belle.bdc.openapi.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CacheServiceImpl implements CacheService {
    @Autowired
    ImpalaCacheDao impalaCacheDao;

    @Autowired
    MysqlCacheDao mysqlCacheDao;

    @Override
    public List<Map<String, Object>> queryUpdateTime(Map<String, Object> params) {
        return impalaCacheDao.impalaQuery(params);
    }

    @Override
    public List<Map<String, Object>> queryEndTime(Map<String, Object> params) {
        return mysqlCacheDao.mysqlQuery(params);
    }

}
