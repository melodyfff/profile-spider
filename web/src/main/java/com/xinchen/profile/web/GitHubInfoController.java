package com.xinchen.profile.web;

import com.xinchen.profile.common.api.CommonApi;
import com.xinchen.profile.common.api.GitHubLookUpServiceApi;
import com.xinchen.profile.common.vo.GitHubUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/29 23:19
 */
@RestController
@RequestMapping("/api/v1/github")
@Slf4j
public class GitHubInfoController {

    private GitHubLookUpServiceApi gitHubLookUpServiceApi;

    public GitHubInfoController(GitHubLookUpServiceApi gitHubLookUpServiceApi) {
        this.gitHubLookUpServiceApi = gitHubLookUpServiceApi;
    }

    @GetMapping("/{user}")
    public GitHubUserInfo queryUser(@PathVariable("user") String user) throws InterruptedException, ExecutionException {
        final CompletableFuture<GitHubUserInfo> info = gitHubLookUpServiceApi.findUser(CommonApi.GITHUB_USER_API.getUrl(), user);
        return info.get();
    }

    /**
     * 查询实例 http://localhost:8080/api/v1/github/list/melodyfff,PivotalSoftware,CloudFoundry,Spring-Projects
     *
     * @param users ','分隔的用户名字符串
     * @return List<Object>
     * @throws InterruptedException InterruptedException
     * @throws ExecutionException ExecutionException
     */
    @GetMapping("/list/{users}")
    public List<Object> queryUsers(@PathVariable("users") String users) throws InterruptedException, ExecutionException {
        final List<String> userList = Arrays.asList(users.split(","));
        final List<CompletableFuture> queueList = new ArrayList<>();
        CompletableFuture[] queue = new CompletableFuture[userList.size()];

        userList.forEach((x)->{
            try {
                queueList.add(gitHubLookUpServiceApi.findUser(CommonApi.GITHUB_USER_API.getUrl(), x));
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        });

        CompletableFuture.allOf(queueList.toArray(queue)).join();



        return queueList.stream().map((x) -> {
            try {
                return x.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
                return null;
            }
        }).collect(Collectors.toList());
    }
}
