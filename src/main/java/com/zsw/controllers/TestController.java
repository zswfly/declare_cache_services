package com.zsw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangshaowei on 2020/3/25.
 */
@Controller
@RequestMapping("/declare_test")
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/setKey")
    @ResponseBody
    public String setKey(String key, String value) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key, value);
        return "ok";
    }

    @RequestMapping("/getKey")
    @ResponseBody
    public String getValue(String key) {
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        return ops.get(key);
    }

}
