package com.xinchen.profile.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinchen.profile.common.api.ProfileInfoServiceApi;
import com.xinchen.profile.common.template.ByteRedisTemplate;
import com.xinchen.profile.common.vo.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author xinchen
 * @version 1.0
 * @date 29/04/2019 15:12
 */
@Service
@Slf4j
public class ProfileInfoService implements ProfileInfoServiceApi {

    private final RestTemplate restTemplate;

    private final RedisTemplate<String, String> redisTemplate;

    private final ByteRedisTemplate byteRedisTemplate;

    private static final String PRE_NAME = "profile:userInfo:";

    public ProfileInfoService(RestTemplate restTemplate, RedisTemplate<String, String> redisTemplate, ByteRedisTemplate byteRedisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
        this.byteRedisTemplate = byteRedisTemplate;
    }


    @Async
    @Override
    public CompletableFuture<ResponseInfo> callResponseInfo(String url, String... args) {
        final ResponseInfo forEntity = JSONObject.parseObject(restTemplate.getForObject(url, String.class, args), ResponseInfo.class);
        log.info("Find User : {}", forEntity);
        return CompletableFuture
                .completedFuture(forEntity)
                .whenComplete((re,ex)-> redisTemplate.opsForValue().set(PRE_NAME + args[1] + ":info", null == re ? null : JSONObject.toJSONString(re)));
    }

    @Override
    public void callResponseImg(boolean local,String url, String... args) {
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, HttpEntity.EMPTY, byte[].class);
        log.info("Find User : {}", args[0]);
        byteRedisTemplate.opsForValue().set(PRE_NAME + args[0] + ":img:" + args[1], responseEntity.getBody());
        if (local){
            if (null!=responseEntity.getBody()&&null!=responseEntity.getHeaders().getContentType()){
                storeLocal(responseEntity,args[0],args[1]);
            }
        }
    }

    @Override
    public List<ResponseInfo> callAllResponseInfo(List<String> keys) {
        return getByKeys(keys);
    }

    @Override
    public void save2Redis(Map<String, String> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    @Override
    public ResponseInfo getByKey(String key) {
        return JSONObject.parseObject(key,ResponseInfo.class);
    }

    @Override
    public List<ResponseInfo> getByKeys(List<String> keys) {
        final List<String> responseInfo = redisTemplate.opsForValue().multiGet(keys);
        return null == responseInfo? null:JSONArray.parseArray(responseInfo.toString(),ResponseInfo.class);
    }


    private void storeLocal(ResponseEntity<byte[]> responseEntity,String no,String size){
        File dir = new File("/tmp/data");
        try {
            if(null!=responseEntity.getHeaders().getContentType()){
                File file = File.createTempFile(no, "." + responseEntity.getHeaders().getContentType().getSubtype(),dir);
                FileOutputStream os = new FileOutputStream(file);
                final byte[] body = byteRedisTemplate.opsForValue().get(PRE_NAME+no+":img:"+size);
                os.write(body);
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
