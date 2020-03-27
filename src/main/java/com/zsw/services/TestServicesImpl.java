package com.zsw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangshaowei on 2020/3/26.
 */
public class TestServicesImpl implements TestServices {
/*    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String setKey(String key, String value) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key, value);
        return "ok";
    }

    @Override
    public String getValue(String key) {
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        return ops.get(key);
    }*/
}
