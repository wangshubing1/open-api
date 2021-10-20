package cn.com.belle.bdc.openapi.service.impl;

import cn.com.belle.bdc.openapi.dao.impala.DaogoubaoDao;
import cn.com.belle.bdc.openapi.model.QueryParams;
import cn.com.belle.bdc.openapi.service.DaogoubaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

@Service
public class DaogoubaoServiceImpl implements DaogoubaoService {
    @Autowired
    DaogoubaoDao daogoubaoDao;

    @Override
    @Cacheable(value = "bdc-api-dgb-getStoreSalamtsame", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getStoreSalamtsame(QueryParams queryParams, String cacheKey) {
        System.out.println("非走缓存，实时查询中。。。");
        return daogoubaoDao.getStoreSalamtsame(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getRegionBrandSalamtsame", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getRegionBrandSalamtsame(QueryParams queryParams, String cacheKey) {
        System.out.println("非走缓存，实时查询中。。。");
        return daogoubaoDao.getRegionBrandSalamtsame(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getStoreDoubleorder", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getStoreDoubleorder(QueryParams queryParams, String cacheKey) {
        return daogoubaoDao.getStoreDoubleorder(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getRegionBrandDoubleorder", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getRegionBrandDoubleorder(QueryParams queryParams, String cacheKey) {
        return daogoubaoDao.getRegionBrandDoubleorder(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getStoreOrders", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getStoreOrders(QueryParams queryParams, String cacheKey) {
        return daogoubaoDao.getStoreOrders(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getAssistantOrders", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getAssistantOrders(QueryParams queryParams, String cacheKey) {
        return daogoubaoDao.getAssistantOrders(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getStoreSalamt", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getStoreSalamt(QueryParams queryParams, String cacheKey) {
        return daogoubaoDao.getStoreSalamt(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getAssistantSalamt", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getAssistantSalamt(QueryParams queryParams, String cacheKey) {
        return daogoubaoDao.getAssistantSalamt(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getStoreSalCompletionRate", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getStoreSalCompletionRate(QueryParams queryParams, String cacheKey) {
        return daogoubaoDao.getStoreSalCompletionRate(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getAssistantSalCompletionRate", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getAssistantSalCompletionRate(QueryParams queryParams, String cacheKey) {
        return daogoubaoDao.getAssistantSalCompletionRate(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getStoreSaleDayTarget", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getStoreSaleDayTarget(QueryParams queryParams) {
        return daogoubaoDao.getStoreSaleDayTarget(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getStoreSaleMonthTarget", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getStoreSaleMonthTarget(QueryParams queryParams) {
        return daogoubaoDao.getStoreSaleMonthTarget(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getAssistantSaleDayTarget", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getAssistantSaleDayTarget(QueryParams queryParams) {
        return daogoubaoDao.getAssistantSaleDayTarget(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getStoreSaleCurrentDayTarget", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getStoreSaleCurrentDayTarget(QueryParams queryParams) {
        return daogoubaoDao.getStoreSaleCurrentDayTarget(queryParams);
    }

    @Override
    @Cacheable(value = "bdc-api-dgb-getAssistantSaleCurrentDayTarget", key = "#cacheKey", condition="#cacheKey!=null")
    public List<Map<String, Object>> getAssistantSaleCurrentDayTarget(QueryParams queryParams) {
        return daogoubaoDao.getAssistantSaleCurrentDayTarget(queryParams);
    }
}
