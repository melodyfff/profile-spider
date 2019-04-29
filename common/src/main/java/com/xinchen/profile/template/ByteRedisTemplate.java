package com.xinchen.profile.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * 存储字节类型的数据
 * @author xinchen
 * @version 1.0
 * @date 29/04/2019 10:36
 */
@Component
public class ByteRedisTemplate extends RedisTemplate<String, byte[]> {
    public ByteRedisTemplate(){
        this.setKeySerializer(RedisSerializer.string());
        this.setValueSerializer(ByteRedisSerializer.UTF_8);
        this.setHashKeySerializer(RedisSerializer.string());
        this.setHashValueSerializer(ByteRedisSerializer.UTF_8);
    }

    /**
     * 此处使用@Autowired是为了自动注入RedisConnectionFactory connectionFactory
     * @param connectionFactory connectionFactory
     */
    @Autowired
    public ByteRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection,
                                                   boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
