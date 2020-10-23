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

import com.google.common.collect.Lists;
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

    public static final int requestNum = 9;

    public static final int concurrentNum = 9;

    public static void main(String[] args) {
        List<String> tokens = Lists.newArrayList("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySW5mbyI6eyJnZW5kZXIiOjAsInJlZ2lzdGVyVGltZSI6MTU1MTE2NTA5NiwicG9ydHJhaXRVcmwiOiJodHRwOi8vY291cnNlLnJlcy5qaWF5b3V4dWViYS5jb20vaW1nL3NoYXJlY291cnNlL3RvdXhpYW5nLmpwZyIsInVzZXJBcmVhQ29kZSI6MzMwMTAwLCJzeXN0ZW1TdGF0dXMiOjAsIm1vYmlsZSI6IjEzNjY2OTk5ODg4IiwibmFtZSI6IuiDoeiNo-WNjuWkp-W4heavlCIsImlubmVyVXNlciI6MCwidXBkYXRlVGltZSI6MTU3OTE2NDc4NywidXNlclR5cGUiOjAsInByb3ZpbmNlSWQiOjE5LCJ1c2VySWQiOjQwMDEyMzB9LCJkb21haW5Db2RlIjozLCJzZXNzaW9uIjoiNzVhNGVlYWQtODVhOS00OGUwLTgzM2QtMzRjOTRjNGQ0NjhjIiwiZXhwaXJlIjoxNTk5ODgzMTgyOTg2LCJjcmVhdGUiOjE1OTcyOTExODI5ODYsImRldmljZUNoZWNrIjoxfQ.uUfs0DqzdWtYiHj8Nb3JTfi3TYm5TbiRcrRmqGrgjAk",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySW5mbyI6eyJnZW5kZXIiOjAsInJlZ2lzdGVyVGltZSI6MTUyMjI5MzY0MiwicG9ydHJhaXRVcmwiOiJodHRwOi8vY291cnNlLnJlcy5qaWF5b3V4dWViYS5jb20vaW1nL3NoYXJlY291cnNlL3RvdXhpYW5nLmpwZyIsInVzZXJBcmVhQ29kZSI6MCwic3lzdGVtU3RhdHVzIjowLCJtb2JpbGUiOiIxNTkwMDAwMDAwNiIsIm5hbWUiOiLlk4jlk4jlk4giLCJpbm5lclVzZXIiOjAsInVwZGF0ZVRpbWUiOjE1MjUzMTQ1MzEsInVzZXJUeXBlIjowLCJwcm92aW5jZUlkIjoxOSwidXNlcklkIjoxNDEwNzQwfSwiZG9tYWluQ29kZSI6Mywic2Vzc2lvbiI6ImQ2Njg5YTY0LWM4ODYtNDk3ZS04NjI2LTBmMjI0YWZmYmRjOSIsImV4cGlyZSI6MTU5OTg4MzI0NTQ0NiwiY3JlYXRlIjoxNTk3MjkxMjQ1NDQ2LCJkZXZpY2VDaGVjayI6MX0.r7-EsdgvghWn4DZJ5PbhJsEuF4G_zDkcP1WL6qqQco8",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySW5mbyI6eyJnZW5kZXIiOjAsInJlZ2lzdGVyVGltZSI6MTUyMjMwNDk0NywicG9ydHJhaXRVcmwiOiJodHRwOi8vN3hwejB5LmNvbTEuejAuZ2xiLmNsb3VkZG4uY29tL2ltZy9zaGFyZWNvdXJzZS90b3V4aWFuZy5qcGciLCJ1c2VyQXJlYUNvZGUiOjAsInN5c3RlbVN0YXR1cyI6MCwibW9iaWxlIjoiMTgwMDAwMDAwMDIiLCJuYW1lIjoi5be05bCUIiwiaW5uZXJVc2VyIjowLCJ1cGRhdGVUaW1lIjoxNTIyMzA0OTQ3LCJ1c2VyVHlwZSI6MCwicHJvdmluY2VJZCI6MTgsInVzZXJJZCI6MTQxMDc0MX0sImRvbWFpbkNvZGUiOjMsInNlc3Npb24iOiIzMWIyNmI1ZC0wZDRlLTQxODItYWQ1Mi1mNGM0Mjc3ODRmZmYiLCJleHBpcmUiOjE1OTk4ODMyNjU3NzEsImNyZWF0ZSI6MTU5NzI5MTI2NTc3MSwiZGV2aWNlQ2hlY2siOjF9.J5k0LeLaRdEMgqm3UDqDhLqX11lXo9UnQrjOrtIT818",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySW5mbyI6eyJnZW5kZXIiOjAsInJlZ2lzdGVyVGltZSI6MTUyMjMyMzU3NywicG9ydHJhaXRVcmwiOiJodHRwOi8vN3hwejB5LmNvbTEuejAuZ2xiLmNsb3VkZG4uY29tL2ltZy9zaGFyZWNvdXJzZS90b3V4aWFuZy5qcGciLCJ1c2VyQXJlYUNvZGUiOjMzMDEwMCwic3lzdGVtU3RhdHVzIjowLCJtb2JpbGUiOiIxMzAwMDAwMDAwNiIsIm5hbWUiOiLoh6rmsrvlt54iLCJpbm5lclVzZXIiOjAsInVwZGF0ZVRpbWUiOjE1ODc5NzUzMzUsInVzZXJUeXBlIjowLCJwcm92aW5jZUlkIjoxOSwidXNlcklkIjoxNDEwNzQ3fSwiZG9tYWluQ29kZSI6Mywic2Vzc2lvbiI6ImRhNDg2MzU4LWQxMjMtNGZkZC1iZGExLTk2YTE5MGIwYTFlOCIsImV4cGlyZSI6MTU5OTg4MzI4NTI4NCwiY3JlYXRlIjoxNTk3MjkxMjg1Mjg0LCJkZXZpY2VDaGVjayI6MX0.kvn_iCd5v9trFHPlA6Whngtay85xL41dHfwxf2MrqHI",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySW5mbyI6eyJnZW5kZXIiOjEsInJlZ2lzdGVyVGltZSI6MTUyMjM4MjIzOSwicG9ydHJhaXRVcmwiOiJodHRwOi8vN3hwejB5LmNvbTIuejAuZ2xiLnFpbml1Y2RuLmNvbS90ZW1wX2hlYWRfaWNvbl8yMDE4MzMwMTE1NzU1LnBuZyIsInVzZXJBcmVhQ29kZSI6MCwic3lzdGVtU3RhdHVzIjowLCJtb2JpbGUiOiIxMzExNjE1Mjc5OCIsIm5hbWUiOiLkuIDmi7PotoXkuroiLCJpbm5lclVzZXIiOjAsInVwZGF0ZVRpbWUiOjE1MjIzODkyNjcsInVzZXJUeXBlIjowLCJwcm92aW5jZUlkIjoxOSwidXNlcklkIjoxNDEwNzQ4fSwiZG9tYWluQ29kZSI6Mywic2Vzc2lvbiI6IjFkZTI4MTM1LWFlNzktNGRjNC1iNmUwLTQ4NWY0ZGI0YWIwMCIsImV4cGlyZSI6MTU5OTg4MzMwNDY1MywiY3JlYXRlIjoxNTk3MjkxMzA0NjUzLCJkZXZpY2VDaGVjayI6MX0.GjFNCTubS5GsIb2SVhz_umi0jC9oCnq_FOY0GeKBXTs",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySW5mbyI6eyJnZW5kZXIiOjAsInJlZ2lzdGVyVGltZSI6MTUyMjM5NjE4MywicG9ydHJhaXRVcmwiOiJodHRwOi8vY291cnNlLnJlcy5qaWF5b3V4dWViYS5jb20vaW1nL3NoYXJlY291cnNlL3RvdXhpYW5nLmpwZyIsInVzZXJBcmVhQ29kZSI6MTMwNDAwLCJzeXN0ZW1TdGF0dXMiOjAsIm1vYmlsZSI6IjEzMDAwMDAwMDA4IiwibmFtZSI6IuW3suaEj-S5iSIsImlubmVyVXNlciI6MCwidXBkYXRlVGltZSI6MTU4Njg0NDA2MCwidXNlclR5cGUiOjAsInByb3ZpbmNlSWQiOjE3LCJ1c2VySWQiOjE0MTA3NDl9LCJkb21haW5Db2RlIjozLCJzZXNzaW9uIjoiYTY2NjAwZDktZDZiMC00YTM3LTg5ODktZWE5MmNmYzhjNmE1IiwiZXhwaXJlIjoxNTk5ODgzMzE5NjU3LCJjcmVhdGUiOjE1OTcyOTEzMTk2NTcsImRldmljZUNoZWNrIjoxfQ.pEi8hjIGGPbTE7q3i-rJcXq_uiYNX4lCJGzcN6t-g70",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySW5mbyI6eyJnZW5kZXIiOjEsInJlZ2lzdGVyVGltZSI6MTUyMjYzOTI3NiwicG9ydHJhaXRVcmwiOiJodHRwOi8vY291cnNlLnJlcy5qaWF5b3V4dWViYS5jb20vaW1nL3NoYXJlY291cnNlL3RvdXhpYW5nLmpwZyIsInVzZXJBcmVhQ29kZSI6MCwic3lzdGVtU3RhdHVzIjowLCJtb2JpbGUiOiIxMzAwNDAxMDQwMSIsIm5hbWUiOiLlhbfkvZPkuZ_lsLHku5bprZTlm74iLCJpbm5lclVzZXIiOjAsInVwZGF0ZVRpbWUiOjE1MjI2MzkyNzYsInVzZXJUeXBlIjowLCJwcm92aW5jZUlkIjo0LCJ1c2VySWQiOjE0MTA3NTB9LCJkb21haW5Db2RlIjozLCJzZXNzaW9uIjoiMjFkMDYxZTItMDU5My00ZTM5LTg3N2ItNTQyMzQxMmU0MDMxIiwiZXhwaXJlIjoxNTk5ODgzMzM5MjY1LCJjcmVhdGUiOjE1OTcyOTEzMzkyNjUsImRldmljZUNoZWNrIjoxfQ.K0PcAWSsC7SYqZj94G0yhVKu2dqaPDFWNBPZuEN3mmc",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySW5mbyI6eyJnZW5kZXIiOjAsInJlZ2lzdGVyVGltZSI6MTUyMzE4NzI2MCwicG9ydHJhaXRVcmwiOiJodHRwOi8vY291cnNlLnJlcy5qaWF5b3V4dWViYS5jb20vaW1nL3NoYXJlY291cnNlL3RvdXhpYW5nLmpwZyIsInVzZXJBcmVhQ29kZSI6MCwic3lzdGVtU3RhdHVzIjowLCJtb2JpbGUiOiIxNjIwMDAwMDAyOSIsIm5hbWUiOiLlvJ_lvJ_nmoQiLCJpbm5lclVzZXIiOjAsInVwZGF0ZVRpbWUiOjE1MjMxODcyNjAsInVzZXJUeXBlIjowLCJwcm92aW5jZUlkIjoxLCJ1c2VySWQiOjE0MTA3NTJ9LCJkb21haW5Db2RlIjozLCJzZXNzaW9uIjoiZGI4NTk2YzItNGRiYy00MDE0LWE3ZGEtNDBjNTZlNTJkNDhjIiwiZXhwaXJlIjoxNTk5ODgzMzU2MTg2LCJjcmVhdGUiOjE1OTcyOTEzNTYxODYsImRldmljZUNoZWNrIjoxfQ.rWpV7yCS1Zk4IoPSatEwWs2ZaDSauxyrwiy_7sPrhrM",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySW5mbyI6eyJnZW5kZXIiOjAsInJlZ2lzdGVyVGltZSI6MTUyMzI0NTM2NywicG9ydHJhaXRVcmwiOiJodHRwOi8vY291cnNlLnJlcy5qaWF5b3V4dWViYS5jb20vaW1nL3NoYXJlY291cnNlL3RvdXhpYW5nLmpwZyIsInVzZXJBcmVhQ29kZSI6MCwic3lzdGVtU3RhdHVzIjowLCJtb2JpbGUiOiIxODI1ODI2OTk5OSIsIm5hbWUiOiLlj5HliLDkvaAiLCJpbm5lclVzZXIiOjAsInVwZGF0ZVRpbWUiOjE1MjMyNDUzNjcsInVzZXJUeXBlIjowLCJwcm92aW5jZUlkIjo3LCJ1c2VySWQiOjE0MTA3NTN9LCJkb21haW5Db2RlIjozLCJzZXNzaW9uIjoiMmZhNzhkOGUtMzI3MS00ZTg2LWE3OGUtNWEwNjk1MTAwYWNiIiwiZXhwaXJlIjoxNTk5ODgzMzcxMjA2LCJjcmVhdGUiOjE1OTcyOTEzNzEyMDYsImRldmljZUNoZWNrIjoxfQ.nc1kbJGO95IwaXmX17BEV5UDExf3QF6E09qCCN7Z6uI");

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
                        String result = testRequestUri(tokens.get(finalI));
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
        return restTemplate.postForEntity("http://dev6.jiayouxueba.cn:8080/api/external/team_class/student/enter/6024", request, Object.class).getBody().toString();
    }
}
