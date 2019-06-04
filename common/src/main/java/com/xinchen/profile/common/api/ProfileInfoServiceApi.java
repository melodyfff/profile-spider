package com.xinchen.profile.common.api;

import com.xinchen.profile.common.vo.ResponseInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author xinchen
 * @version 1.0
 * @date 29/04/2019 10:53
 */
public interface ProfileInfoServiceApi {
    CompletableFuture<ResponseInfo> callResponseInfo(String url, String... args);

    void callResponseImg(String url, String... args);

    List<ResponseInfo> callAllResponseInfo();

    void save2Redis(Map<String, String> map);
}
