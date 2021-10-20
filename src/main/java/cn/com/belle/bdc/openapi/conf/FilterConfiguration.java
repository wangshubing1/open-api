package cn.com.belle.bdc.openapi.conf;

import cn.com.belle.bdc.openapi.conf.filter.SignFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SignFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("exclusions","/bdc-open-api/druid/*");
        registration.setName("SignFilter");
        // 设置过滤器被调用的顺序
        registration.setOrder(1);

        return registration;
    }
}
