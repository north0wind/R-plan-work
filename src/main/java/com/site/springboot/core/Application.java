package com.site.springboot.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("com.site.springboot.core.dao")
@SpringBootApplication
@EnableTransactionManagement
@EnableDiscoveryClient
@LoadBalancerClients(value = {
        @LoadBalancerClient(name = "comments-provider")
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
