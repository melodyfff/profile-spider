package com.xinchen.profile.service;

import com.xinchen.profile.api.GitHubLookUpServiceApi;
import com.xinchen.profile.vo.GitHubUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/29 23:22
 */
@Service
@Slf4j
public class GitHubLookUpService implements GitHubLookUpServiceApi {

    private final RestTemplate restTemplate;

    public GitHubLookUpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    @Override
    public CompletableFuture<GitHubUserInfo> findUser(String url, String user) throws InterruptedException {
        String target = url + user;
        log.info("Looking up: [{}] , user: [{}]", target, user);
        final GitHubUserInfo info = restTemplate.getForObject(target, GitHubUserInfo.class);
        Thread.sleep(3000L);
        return CompletableFuture.completedFuture(info);
    }
}
