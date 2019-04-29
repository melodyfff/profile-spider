package com.xinchen.profile.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinchen.profile.api.ProfileInfoServiceApi;
import com.xinchen.profile.vo.ResponseInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author xinchen
 * @version 1.0
 * @date 29/04/2019 15:49
 */
@RestController
@RequestMapping("/api/v1")
public class HelloController {


    private ProfileInfoServiceApi profileInfoServiceApi;

    public HelloController(ProfileInfoServiceApi profileInfoServiceApi) {
        this.profileInfoServiceApi = profileInfoServiceApi;
    }

    @GetMapping("/greeting")
    public String hello(){
        return "hello.";
    }

    @GetMapping("/query/{id}")
    public ResponseInfo queryById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {


        return profileInfoServiceApi.callResponseInfo("").get();
    }


}
