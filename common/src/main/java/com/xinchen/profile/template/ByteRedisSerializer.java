package com.xinchen.profile.template;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 自定义 byte类型序列化
 * @author xinchen
 * @version 1.0
 * @date 29/04/2019 10:37
 */
public class ByteRedisSerializer implements RedisSerializer<byte[]> {
    private final Charset charset;

    public static final ByteRedisSerializer UTF_8;

    public ByteRedisSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public ByteRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public byte[] serialize(@Nullable byte[] bytes) throws SerializationException {
        return bytes;
    }

    @Override
    public byte[] deserialize(@Nullable byte[] bytes) throws SerializationException {
        return bytes;
    }

    static {
        UTF_8 = new ByteRedisSerializer(StandardCharsets.UTF_8);
    }
}
