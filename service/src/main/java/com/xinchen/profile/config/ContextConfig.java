package com.xinchen.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

/**
 * @author xinchen
 * @version 1.0
 * @date 29/04/2019 10:31
 */
@Configuration
public class ContextConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 定义多线程处理
     * 如果用户没有定义Executor bean，Spring会创建一个SimpleAsyncTaskExecutor并使用它
     * @return {@link ThreadPoolTaskExecutor}
     */
    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("ProfileSpider-");
        executor.initialize();
        return executor;
    }
}
