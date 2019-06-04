package com.xinchen.profile.common.api;

import com.xinchen.profile.common.vo.GitHubUserInfo;

import java.util.concurrent.CompletableFuture;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/29 23:22
 */
public interface GitHubLookUpServiceApi {
    CompletableFuture<GitHubUserInfo> findUser(String url,String user) throws InterruptedException;
}
