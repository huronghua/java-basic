/*
 * File Name:InterfaceTest is created on 2020/4/17 11:28 by eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java;

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

    public static final int requestNum = 4;

    public static final int concurrentNum = 3;

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
            Runtime.getRuntime().availableProcessors() * 2, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
            new ThreadFactoryBuilder().setNameFormat("interfaceTestExecutor-[%d]").build(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

        CountDownLatch countDownLatch = new CountDownLatch(requestNum);

        Semaphore semaphore = new Semaphore(concurrentNum);

        try {
            for (int i = 0; i < requestNum; i++) {
                executorService.execute(() -> {

                    try {
                        semaphore.acquire();
                        String result = testRequestUri();
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

    private static String testRequestUri() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("token",
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySW5mbyI6eyJnZW5kZXIiOjAsInJlZ2lzdGVyVGltZSI6MTU1MTE2NDE4MSwiaWRDYXJkIjoiMzIwNjIxMTk5MzExMTYyNDIyIiwibmlja05hbWUiOiLog6HojaPljY7nnJ_luIXogIHluIgiLCJwb3J0cmFpdFVybCI6Imh0dHA6Ly9wdC1pbWcuamlheW91eHVlYmEuY29tL2FuZF9lYTgwOTkzM2U5MGQzNWRjYWE0NjMyZGI5ODM4YjQ2MV9wb3J0cmFpdF82djI4NyIsIm1vYmlsZSI6IjEzNjY2OTk5ODg4IiwidXBkYXRlVGltZSI6MTU4NTMwMzAzNiwicHJvdmluY2VJZCI6MSwidXNlcklkIjo1MTkyMCwiYm9vbGVhbkZsYWciOjQsImNvdW50cnlJZCI6Njg1LCJzeXN0ZW1TdGF0dXMiOjAsIm5hbWUiOiLog6HojaPljY7nnJ_kuJEiLCJ1c2VyVHlwZSI6MX0sImRvbWFpbkNvZGUiOjEsInNlc3Npb24iOiJkZWMyY2EwZC0zZmI5LTQ4MDgtOWQ2Yi1jMjljYjBhZmY3NGUiLCJleHBpcmUiOjE1ODk2OTQ4OTY1NzksImNyZWF0ZSI6MTU4NzEwMjg5NjU3OSwiZGV2aWNlQ2hlY2siOjF9.xrROl6ptlyfE8kbO2lZPz3uM-rzsvG7kUVN1-rQEhH8");

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("burst_biz_id", "12711");
        map.add("burst_biz_type", "3");
        map.add("from_course_type", "4");
        map.add("from_course_id", "5807");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity("http://dev5.jiayouxueba.cn:8080/api/external/burst_order/scholar/order", request, Object.class).getBody().toString();
    }
}
