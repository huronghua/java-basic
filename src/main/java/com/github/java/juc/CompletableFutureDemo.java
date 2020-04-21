package com.github.java.juc;

import org.apache.commons.lang.time.DateUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Auther:Eric https://github.com/huronghua
 * @Date:2018/8/28 23 48
 */
public class CompletableFutureDemo {

	public static void main(String[] args) throws InterruptedException {

		CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
			System.out.println("开始耗时的计算"+ System.currentTimeMillis());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("耗时计算结束" + System.currentTimeMillis());
			return 100;
		});


		completableFuture.whenComplete((Integer result, Throwable e) ->
		{
			System.out.println("执行结束的回调结果");
		});
		/*阻塞进程到拿到回调函数的结果*/
		Thread.sleep(4000);
/*
		new CountDownLatch(1).await();
*/
	}
}
