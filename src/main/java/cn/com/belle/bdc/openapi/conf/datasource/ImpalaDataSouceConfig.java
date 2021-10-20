package cn.com.belle.bdc.openapi.conf.datasource;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "cn.com.belle.bdc.openapi.dao.impala", sqlSessionTemplateRef = "impalaSqlSessionTemplate")
public class ImpalaDataSouceConfig {
    @Bean(name = "impalaJdbcDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.impala")
    public DataSource hiveDataSource(){
        return new DruidDataSource();
//        return DataSourceBuilder.create().build();
    }


    @Bean(name = "impalaSqlSessionFactory")
    /**
     * 使用声明的数据源，创建sqlSession工厂
     */
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("impalaJdbcDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        /*
         * 当mybatis采用映射配置文件的方式时，指明该数据源需要是扫描的xml文件路径
         */
        bean.setDataSource(dataSource);
        bean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources(
                "classpath:mapper/impala/*.xml"));

        return bean.getObject();
    }

    @Bean(name = "impalaSqlSessionTemplate")
    @Primary
    /**
     * 声明SqlSessionTemplate由指定的SqlSession工厂创建
     */
    public SqlSessionTemplate mysqlSqlSessionTemplate(
            @Qualifier("impalaSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
