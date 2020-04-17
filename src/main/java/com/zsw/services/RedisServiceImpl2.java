package com.zsw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
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
    public String opsForHashGet(String h,String hk) throws Exception {
        HashOperations<String,String,String> hashOperations = this.redisTemplate.opsForHash();
        return hashOperations.get(h,hk);
    }

    @Override
    public void opsForSetAdd(String key, Set value) throws Exception {
        SetOperations setOperations = this.redisTemplate.opsForSet();
        setOperations.add(key,value);
    }

    @Override
    public Boolean opsForSetIsMember(String key, String value) throws Exception {
        SetOperations setOperations = this.redisTemplate.opsForSet();
        return setOperations.isMember(key,value);
    }
}
