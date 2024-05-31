package com.site.springboot.core.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaolong
 * @date 2024/5/31
 */
@Configuration
@EnableFeignClients
@Slf4j
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                log.info("feign 请求前添加请求头");
                requestTemplate.header("Authorization", "Bearer your-token");
            }
        };
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    // @Bean
    // public Retryer retryer(){
    //     // 初始重试间隔时间 （默认按照1.5倍递增）
    //     // 最大重试间隔时间
    //     // 最大重试次数
    //     return new Retryer.Default(100,1000,5);
    // }

//    @Bean
//    public Request.Options options(){
//        // 链接服务端超时时间ms
//        // 接受服务端响应时间ms
//        // 是否支持重定向
//        return new Request.Options(
//                1000,
//                TimeUnit.MICROSECONDS,
//                1000,
//                TimeUnit.MICROSECONDS,
//                true);
//    }
}
