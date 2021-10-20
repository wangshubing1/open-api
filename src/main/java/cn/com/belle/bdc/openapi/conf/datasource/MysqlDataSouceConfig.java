package cn.com.belle.bdc.openapi.conf.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "cn.com.belle.bdc.openapi.dao.mysql", sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
public class MysqlDataSouceConfig {
    // 声明bean
    @Bean(name = "mysqlDataSource")
    // 指明读取的配置
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    // 设置为主数据源
    @Primary
    /*
     * 声明数据源配置
     */
    public DataSource mysqlDataSource() {
        return new DruidDataSource();
//        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    /**
     * 使用声明的数据源，创建sqlSession工厂
     */
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        /*
         * 当mybatis采用映射配置文件的方式时，指明该数据源需要是扫描的xml文件路径
         */
        bean.setDataSource(dataSource);
        bean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources(
         "classpath:mapper/mysql/*.xml"));

        return bean.getObject();
    }

    @Bean(name = "mysqlTransactionManager")
    @Primary
    /**
     * 声明数据源有自己的事务管理
     */
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    @Primary
    /**
     * 声明SqlSessionTemplate由指定的SqlSession工厂创建
     */
    public SqlSessionTemplate mysqlSqlSessionTemplate(
            @Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
