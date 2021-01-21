/*
 * File Name:InterfaceTest is created on 2020/4/17 11:28 by eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eric
 * @Description:
 * @date: 2020/4/17 11:28
 * @since JDK 1.8
 */
@Slf4j
public class InterfaceTest {

    public static final int requestNum = 200;

    public static final int concurrentNum = 200;

    public static void main(String[] args) {
        List<String> tokens = Lists.newArrayList(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySW5mbyI6eyJnZW5kZXIiOjAsInJlZ2lzdGVyVGltZSI6MTU1MTE2NTA5NiwicG9ydHJhaXRVcmwiOiJodHRwOi8vY291cnNlLnJlcy5qaWF5b3V4dWViYS5jb20vaW1nL3NoYXJlY291cnNlL3RvdXhpYW5nLmpwZyIsInVzZXJBcmVhQ29kZSI6MzMwMTAwLCJzeXN0ZW1TdGF0dXMiOjAsIm1vYmlsZSI6IjEzNjY2OTk5ODg4IiwibmFtZSI6IuiDoeiNo-WNjuWkp-W4heavlCIsImlubmVyVXNlciI6MCwidXBkYXRlVGltZSI6MTU3OTE2NDc4NywidXNlclR5cGUiOjAsInByb3ZpbmNlSWQiOjE5LCJ1c2VySWQiOjQwMDEyMzB9LCJkb21haW5Db2RlIjozLCJzZXNzaW9uIjoiNzVhNGVlYWQtODVhOS00OGUwLTgzM2QtMzRjOTRjNGQ0NjhjIiwiZXhwaXJlIjoxNTk5ODgzMTgyOTg2LCJjcmVhdGUiOjE1OTcyOTExODI5ODYsImRldmljZUNoZWNrIjoxfQ.uUfs0DqzdWtYiHj8Nb3JTfi3TYm5TbiRcrRmqGrgjAk");

        ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
            Runtime.getRuntime().availableProcessors() * 2, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
            new ThreadFactoryBuilder().setNameFormat("interfaceTestExecutor-[%d]").build(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

        CountDownLatch countDownLatch = new CountDownLatch(requestNum);

        Semaphore semaphore = new Semaphore(concurrentNum);

        try {
            for (int i = 0; i < requestNum; i++) {
                int finalI = i;
                executorService.execute(() -> {

                    try {
                        semaphore.acquire();
                        String result = testRequestUri(null);
                        log.info("result:{}", result);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        log.error("exception", e);
                    }
                    countDownLatch.countDown();
                });
            }

            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("exception", e);
        }
        executorService.shutdown();
    }

    private static String testRequestUri(String token) {


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("token",
                token);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
//        map.add("pay_channel", "alipay_scan");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity("http://localhost:8080/limit_test", String.class).getBody().toString();
    }
}
