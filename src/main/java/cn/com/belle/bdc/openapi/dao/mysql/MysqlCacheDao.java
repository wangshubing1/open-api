package cn.com.belle.bdc.openapi.dao.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MysqlCacheDao {

    public List<Map<String, Object>> mysqlQuery(@Param("params")Map<String, Object> params);
}

