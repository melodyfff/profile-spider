package com.xinchen.profile.config;

import com.xinchen.profile.common.properties.SpiderConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xinchen
 * @version 1.0
 * @date 29/04/2019 10:31
 */
@Configuration
@EnableAsync
@EnableConfigurationProperties(SpiderConfigurationProperties.class)
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
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        // 队列的最大长度
        executor.setQueueCapacity(1300);

        // (1)ThreadPoolExecutor.AbortPolicy策略，是默认的策略,处理程序遭到拒绝将抛出运行时 RejectedExecutionException。
        // (2)ThreadPoolExecutor.CallerRunsPolicy策略 ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃.
        // (3)ThreadPoolExecutor.DiscardPolicy策略，不能执行的任务将被丢弃.
        // (4)ThreadPoolExecutor.DiscardOldestPolicy策略，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）.
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        executor.setThreadNamePrefix("ProfileSpider-");
        executor.initialize();
        return executor;
    }
}
