package cn.com.belle.bdc.openapi.dao.hive;

import cn.com.belle.bdc.openapi.model.IdTest;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IdTestDao {
    List<IdTest> selectIds();
}
