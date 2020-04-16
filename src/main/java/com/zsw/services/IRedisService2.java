package com.zsw.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangshaowei on 2020/4/16.
 */
public interface IRedisService2 {

     void opsForHashPut(String h,String hk,String hv) throws Exception;
     void opsForSet(String key, Set value) throws Exception;
}
