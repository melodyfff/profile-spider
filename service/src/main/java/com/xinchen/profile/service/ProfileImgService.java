package com.xinchen.profile.service;

import com.xinchen.profile.api.ProfileInfoServiceApi;
import com.xinchen.profile.vo.ResponseInfo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * @author xinchen
 * @version 1.0
 * @date 29/04/2019 15:12
 */
@Service
public class ProfileImgService implements ProfileInfoServiceApi {

    private final RestTemplate restTemplate;

    public ProfileImgService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * 异步执行请求
     * @param url 请求的地址
     * @param args 请求的参数
     * @return {@link CompletableFuture<ResponseInfo>}
     */
    @Async
    @Override
    public CompletableFuture<ResponseInfo> callResponseInfo(String url, String... args) {
        final ResponseInfo forEntity = restTemplate.getForObject(url, ResponseInfo.class);
        return CompletableFuture.completedFuture(forEntity);
    }
}
