package com.wyz.coffee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/2 23:32
 **/
@SpringBootApplication
//@EnableFeignClients
//@EnableCircuitBreaker
@EnableEurekaClient
@MapperScan("com.wyz.coffee.dao")
@ComponentScan("com.wyz.coffee.**")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
