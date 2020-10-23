package com.github.java;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/*
 * File Name:InterfaceTestTest is created on 2020/10/1915:12 by Eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
@SpringBootTest(classes = JavaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class InterfaceTestTest {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;;

    @Test
    public void testRedis() {
        System.out.println(stringRedisTemplate.opsForValue().increment("key_incr", 2));
    }

    @Test
    public void dynamic() {
        System.out.println(new StringBuilder().toString().equals(""));
    }

    int f(int n) {
        if (n <= 1)
            return n;
        // 先创建一个数组来保存历史数据
        int[] dp = new int[n + 1];
        // 给出初始值
        dp[0] = 0;
        dp[1] = 1;
        // 通过关系式来计算出 dp[n]
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        // 把最终结果返回
        return dp[n];
    }
}