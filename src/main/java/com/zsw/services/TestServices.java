package com.zsw.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhangshaowei on 2020/3/26.
 */
//@FeignClient( value = "redis-services" )
public interface TestServices {
  /*  @GetMapping("/declare_test/getValue")
    String getValue( @RequestParam("key") String key );

    @GetMapping("/declare_test/setKey")
    String setKey(@RequestParam("key")String key, @RequestParam("value")String value) ;*/
}
