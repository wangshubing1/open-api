package cn.com.belle.bdc.openapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableCaching
@EnableScheduling
public class OpenapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenapiApplication.class, args);
    }

}
