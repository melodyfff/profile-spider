package com.xinchen.profile.web;

import com.xinchen.profile.common.api.ProfileInfoServiceApi;
import com.xinchen.profile.common.vo.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;


/**
 * @author xinchen
 * @version 1.0
 * @date 09/05/2019 10:56
 */
@RestController
@RequestMapping("/api/v1/profile")
@Slf4j
public class ProfileInfoController {

    private final ProfileInfoServiceApi profileInfoService;

    @Value("${spider.syn.info.url}")
    private String url;

    @Value("${spider.syn.info.param}")
    private String param;

    @Value("${spider.syn.info.seed}")
    private String seed;

    @Value("${spider.syn.image.url}")
    private String imageUrl;

    private static final String PRE_NAME = "profile:userInfo:";

    public ProfileInfoController(ProfileInfoServiceApi profileInfoService) {
        this.profileInfoService = profileInfoService;
    }


    @RequestMapping("/query/{id}")
    public ResponseInfo queryOne(@PathVariable("id") String id) {
        Objects.requireNonNull(id);
        return profileInfoService.getByKey(id);
    }

    @RequestMapping("query/all")
    public List<ResponseInfo> queryAll(){
        List<String> keys = new ArrayList<>();
        for (int i =0;i<1200;i++){
            keys.add(String.format(PRE_NAME+seed+":info", i));
        }
        return profileInfoService.callAllResponseInfo(keys);
    }


    @RequestMapping("/syn/info")
    public List<ResponseInfo> synInfo() {
        long start = System.currentTimeMillis();
        List<String> keys = new ArrayList<>();
        List<CompletableFuture<ResponseInfo>> tasks = new ArrayList<>();
        try {

            for (int i =0;i<1200;i++){
                String no = String.format(seed, i);
                keys.add(String.format(PRE_NAME+seed+":info", i));
                tasks.add(profileInfoService.callResponseInfo(url, param.replaceAll("HELLO", no), no));
            }
            log.info("任务开始装填");
            CompletableFuture[] queue = new CompletableFuture[tasks.size()];

            CompletableFuture.allOf(tasks.toArray(queue)).join();
            log.info("任务全部执行完毕");

            log.info("Time Cost : {} ms",System.currentTimeMillis()-start);
            return profileInfoService.callAllResponseInfo(keys);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("同步失败 , ERROR :{}", e);
            return null;
        }
    }

    @RequestMapping("/syn/img")
    public void synImg(){
        try {
            for (int i =0;i<1200;i++){
                String no = String.format(seed, i);
                profileInfoService.callResponseImg(false,imageUrl+no+".jpg",no,"normal");
                profileInfoService.callResponseImg(false,imageUrl+no+"-md.jpg",no,"middle");
                profileInfoService.callResponseImg(false,imageUrl+no+"-sm.jpg",no,"small");
            }
        } catch (Exception e){
            log.error("同步失败: {}",e);
        }
    }

}
