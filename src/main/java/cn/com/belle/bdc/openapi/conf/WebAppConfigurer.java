package cn.com.belle.bdc.openapi.conf;


import cn.com.belle.bdc.openapi.conf.interceptor.SignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器过滤
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Bean
    public SignInterceptor signInterceptor() {
        return new SignInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 这里可以配置拦截器启用的 path 的顺序，在有多个拦截器存在时，任一拦截器返回 false 都会使后续的请求方法不再执行
        registry.addInterceptor(signInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/bdc-open-api/druid")
                .order(1);



    }
}
