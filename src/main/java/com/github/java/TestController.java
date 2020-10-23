package com.github.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther:Eric https://github.com/huronghua
 * @Date:2018/4/22 16 59
 */
@RestController
@Scope(value = "prototype")
public class TestController {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    @GetMapping("/redis")
    public Long redisTest(){
        return stringRedisTemplate.opsForValue().increment("key_incr", 2);
    }

}
