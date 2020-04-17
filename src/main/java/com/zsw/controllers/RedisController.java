package com.zsw.controllers;

import com.zsw.entitys.user.UserPermission;
import com.zsw.services.IRedisService2;
import com.zsw.utils.CacheStaticURLUtil;
import com.zsw.utils.RedisStaticString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Set;

/**
 * Created by zhangshaowei on 2020/4/16.
 */
@Controller
@RequestMapping(CacheStaticURLUtil.redisController)
public class RedisController {
    @Autowired
    IRedisService2 redisService2;

    @RequestMapping(value=CacheStaticURLUtil.redisController_initPermission,
            method= RequestMethod.POST)
    @ResponseBody
    public Integer initPermission(@RequestBody Map<Integer,Set<String>> userPermissions) throws Exception {
        for(Map.Entry<Integer,Set<String>> entry : userPermissions.entrySet()){
            this.redisService2.opsForSetAdd(
                    RedisStaticString.USERPERMISSION + ":" + entry.getKey(),
                    entry.getValue()
            );
        }
        return 1;
    }

    @RequestMapping(value=CacheStaticURLUtil.redisController_setToken,
            method= RequestMethod.POST)
    @ResponseBody
    public Integer setToken(@RequestBody Map<String,String> tokenMap) throws Exception {
        this.redisService2.opsForHashPut(RedisStaticString.USERTOKEN,tokenMap.get("userId"),tokenMap.get("token"));
        return 1;
    }

    @RequestMapping(value=CacheStaticURLUtil.redisController_getToken,
            method= RequestMethod.POST)
    @ResponseBody
    public String getToken(@RequestBody String userId) throws Exception {
        return this.redisService2.opsForHashGet(RedisStaticString.USERTOKEN,userId);
    }

    @RequestMapping(value=CacheStaticURLUtil.redisController_checkPermission,
            method= RequestMethod.POST)
    @ResponseBody
    public Boolean checkPermission(@RequestBody UserPermission userPermission) throws Exception {
        return this.redisService2.opsForSetIsMember(
                RedisStaticString.USERPERMISSION + ":" + userPermission.getUserId(),
                userPermission.getPermissionCode());
    }

}
