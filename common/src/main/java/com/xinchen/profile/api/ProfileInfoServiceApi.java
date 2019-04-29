package com.xinchen.profile.api;

import com.xinchen.profile.vo.ResponseInfo;

import java.util.concurrent.CompletableFuture;

/**
 * @author xinchen
 * @version 1.0
 * @date 29/04/2019 10:53
 */
public interface ProfileInfoServiceApi {
    CompletableFuture<ResponseInfo> callResponseInfo(String url, String... args);
}
