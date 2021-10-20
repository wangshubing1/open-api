package cn.com.belle.bdc.openapi.dao.impala;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ImpalaCacheDao {
    public  List<Map<String, Object>> impalaQuery(@Param("params")Map<String, Object> params);
}
