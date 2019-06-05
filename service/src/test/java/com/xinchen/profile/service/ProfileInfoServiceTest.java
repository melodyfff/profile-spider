package com.xinchen.profile.service;

import com.xinchen.profile.ServiceApplicationTests;
import com.xinchen.profile.common.api.ProfileInfoServiceApi;
import com.xinchen.profile.common.properties.SpiderConfigurationProperties;
import com.xinchen.profile.common.vo.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author xinchen
 * @version 1.0
 * @date 04/06/2019 10:01
 */
@Slf4j
public class ProfileInfoServiceTest extends ServiceApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ProfileInfoServiceApi profileInfoService;

    @Autowired
    private SpiderConfigurationProperties spiderConfigurationProperties;

    @Value("${spider.syn.info.url}")
    private String url;
    @Value("${spider.syn.info.param}")
    private String param;
    @Value("${spider.syn.info.seed}")
    private String seed;

    @Value("${spider.syn.image.url}")
    private String imageUrl;


    private List<String> keys = new ArrayList<>();

    @Before
    public void makeKeys(){
        keys.clear();
        for (int i =0;i<1200;i++){
            keys.add(String.format(PRE_NAME+seed+":info", i));
        }
    }

    private static final String PRE_NAME = "profile:userInfo:";


    @Test
    public void callResponseInfoSyn() {
        long startTIme = System.currentTimeMillis();
        List<CompletableFuture<ResponseInfo>> tasks = new ArrayList<>();
        try {

            for (int i = 0; i < 1200; i++) {
                String no = String.format(seed, i);
                tasks.add(profileInfoService.callResponseInfo(url, param.replaceAll("HELLO", no), no));
            }


            log.info("任务开始装填");
            CompletableFuture[] queue = new CompletableFuture[tasks.size()];

            CompletableFuture.allOf(tasks.toArray(queue)).join();
            log.info("任务全部执行完毕");

            // cost 41074 ms
            log.info(">>>>>>> Cost Time : {}", System.currentTimeMillis() - startTIme);

            log.info(profileInfoService.callAllResponseInfo(keys).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void callResponseInfo() {
        long startTIme = System.currentTimeMillis();
        List<ResponseInfo> list = new ArrayList<>();
        try {

            for (int i = 0; i < 1200; i++) {
                String no = String.format(seed, i);
                list.add(profileInfoService.callResponseInfo(url, param.replaceAll("HELLO", no), no).get(10, TimeUnit.SECONDS));
            }
            // cost 171520 ms
            log.info(">>>>>>> Cost Time : {}", System.currentTimeMillis() - startTIme);
            System.out.println(list);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            log.error("同步失败 , ERROR :{}", e);
        }

    }


    @Test
    public void callResponseImg() {
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

    @Test
    public void callAllResponseInfo() {
        System.out.println(profileInfoService.callAllResponseInfo(keys));
    }
}