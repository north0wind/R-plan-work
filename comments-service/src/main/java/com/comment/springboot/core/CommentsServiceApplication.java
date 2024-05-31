package com.comment.springboot.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiaolong
 * @date 2024/5/31
 */
@MapperScan("com.comment.springboot.core.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class CommentsServiceApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(CommentsServiceApplication.class, args);
    }
}
