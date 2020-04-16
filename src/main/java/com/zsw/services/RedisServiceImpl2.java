package com.zsw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * Created by zhangshaowei on 2020/4/16.
 */
@Service
public class RedisServiceImpl2 implements IRedisService2{
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void opsForHashPut(String h,String hk,String hv) throws Exception {
        HashOperations<String,String,String> hashOperations = this.redisTemplate.opsForHash();
        hashOperations.put(h,hk,hv);
    }

    @Override
    public void opsForSet(String key, Set value) throws Exception {
        SetOperations setOperations = this.redisTemplate.opsForSet();
        setOperations.add(key,value);
    }
}
