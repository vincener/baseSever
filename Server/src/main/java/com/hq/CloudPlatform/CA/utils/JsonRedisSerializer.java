package com.hq.CloudPlatform.CA.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by Administrator on 7/24/2017.
 */
public class JsonRedisSerializer implements RedisSerializer<Object> {

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return (null == o ? null : JSON.toJSONBytes(o, SerializerFeature.UseSingleQuotes));
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return (null == bytes ? null : JSON.parse(bytes));
    }
}
