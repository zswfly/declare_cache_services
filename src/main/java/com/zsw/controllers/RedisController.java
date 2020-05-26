package com.zsw.controllers;

import com.zsw.controller.BaseController;
import com.zsw.entitys.user.UserPermission;
import com.zsw.services.IRedisService2;
import com.zsw.utils.CacheStaticURLUtil;
import com.zsw.utils.RedisStaticString;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RedisController extends BaseController {
    @Autowired
    IRedisService2 redisService2;

    private static final Logger LOG = LoggerFactory.getLogger(RedisController.class);



    @RequestMapping(value=CacheStaticURLUtil.redisController_initPermission,
            method= RequestMethod.POST)
    @ResponseBody
    public Integer initPermission(@RequestBody Map<Integer,Set<String>> userPermissions) throws Exception {
        for(Map.Entry<Integer,Set<String>> entry : userPermissions.entrySet()){
            this.redisService2.opsForSetAdd(
                    RedisStaticString.USER_PERMISSION + ":" + entry.getKey(),
                    entry.getValue()
            );
        }
        return 1;
    }

    @RequestMapping(value=CacheStaticURLUtil.redisController_setToken,
            method= RequestMethod.POST)
    @ResponseBody
    public Integer setToken(@RequestBody Map<String,String> tokenMap) throws Exception {
        this.redisService2.opsForHashPut(RedisStaticString.USER_TOKEN,tokenMap.get("userId"),tokenMap.get("token"));
        return 1;
    }

    @RequestMapping(value=CacheStaticURLUtil.redisController_getToken,
            method= RequestMethod.POST)
    @ResponseBody
    public String getToken(@RequestBody String userId) throws Exception {
        return this.redisService2.opsForHashGet(RedisStaticString.USER_TOKEN,userId);
    }

    @RequestMapping(value=CacheStaticURLUtil.redisController_checkPermission,
            method= RequestMethod.POST)
    @ResponseBody
    public Boolean checkPermission(@RequestBody UserPermission userPermission) throws Exception {
        return this.redisService2.opsForSetIsMember(
                RedisStaticString.USER_PERMISSION + ":" + userPermission.getUserId(),
                userPermission.getPermissionCode());
    }


    @RequestMapping(value=CacheStaticURLUtil.redisController_setVerifyCode,
            method= RequestMethod.POST)
    @ResponseBody
    public void setVerifyCode(@RequestBody Map<String,String> param ) throws Exception {
        this.redisService2.opsForHashPut(
                RedisStaticString.PHONE_VERIFYCODE + ":" + param.get("type")
                ,param.get("phone")
                ,param.get("verifyCode"));
    }

    @RequestMapping(value=CacheStaticURLUtil.redisController_checkVerifyCode,
            method= RequestMethod.POST)
    @ResponseBody
    public Boolean checkVerifyCode(@RequestBody Map<String,String> param ) throws Exception {
        return StringUtils.isNotBlank(param.get("verifyCode"))
                && param.get("verifyCode").equals(
                        this.redisService2.opsForHashGet(
                                RedisStaticString.PHONE_VERIFYCODE + ":" + param.get("type")
                                ,param.get("phone")
                        )
        );
    }

    @RequestMapping(value=CacheStaticURLUtil.redisController_putUserToken,
            method= RequestMethod.POST)
    @ResponseBody
    public void putUserToken(@RequestBody Map<String,String> param ) throws Exception {
        this.redisService2.opsForHashPut(
                RedisStaticString.USER_REMEMBERTOKEN
                ,param.get("userId")
                ,param.get("rememberToken"));
    }


    @RequestMapping(value=CacheStaticURLUtil.redisController_checkUserToken,
            method= RequestMethod.POST)
    @ResponseBody
    public Boolean checkUserToken(@RequestBody Map<String,String> param ) throws Exception {
        return StringUtils.isNotBlank(param.get("rememberToken"))
                && param.get("rememberToken").equals(
                this.redisService2.opsForHashGet(
                        RedisStaticString.USER_REMEMBERTOKEN
                        ,param.get("userId")
                )
        );
    }


    @Override
    public Logger getLOG(){
        return this.LOG;
    }
}
