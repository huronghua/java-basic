package com.github.java.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description:
 * @Auther:Eric https://github.com/huronghua
 * @Date:2018/8/28 23 06
 */
public class FutureDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		Future<String> result = executorService.submit(() ->{
			Thread.sleep(10000);
			return "huronghua";
		});


		while (!result.isDone()){
			System.out.println(result.isDone());
		}

		/*这一步会阻塞直到拿到结果才会执行下面的打印*/
		String rs = result.get();


		System.out.println(rs);
	}
}
