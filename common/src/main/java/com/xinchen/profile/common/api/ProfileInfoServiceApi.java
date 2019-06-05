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
    /**
     * 异步执行请求
     *
     * @param url  请求的地址
     * @param args 请求的参数  args0 param查询参数  args1 no
     * @return {@link CompletableFuture<ResponseInfo>}
     */
    CompletableFuture<ResponseInfo> callResponseInfo(String url, String... args);

    /**
     * 同步图片
     * @param local 是否保存到本地 默认路径 /tmp/data
     * @param url url
     * @param args args0 no , args1 size
     */
    void callResponseImg(boolean local,String url, String... args);

    List<ResponseInfo> callAllResponseInfo(List<String> keys);

    void save2Redis(Map<String, String> map);

    ResponseInfo getByKey(String key);

    /**
     * 根据key 查询
     * @param keys keys
     * @return List<ResponseInfo>
     */
    List<ResponseInfo> getByKeys(List<String> keys);
}
