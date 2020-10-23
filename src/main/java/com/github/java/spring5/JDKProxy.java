package com.github.java.spring5;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Auther:Eric https://github.com/huronghua
 * @Date:2019-04-21 10 00
 */
public class JDKProxy {


	public static UserService getJDKProxy(UserService userService){

		// 传入的原对象必须实现接口，因为参数需要getInterfaces()
		UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(),
				new InvocationHandler() {
					/**
					 *
					 * @param proxy
					 * @param method 原对象的方法引用
					 * @param args
					 * @return
					 * @throws Throwable
					 */
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

						System.out.println("前置增强");
						Object invoke = method.invoke(userService, args);
						System.out.println("后置增强");
						return invoke;
					}
				});

		return userServiceProxy;

	}


	public static  UserService getCglibProxy(UserService userService){
		Enhancer enhancer = new Enhancer();

		enhancer.setSuperclass(userService.getClass());

		enhancer.setCallback(new MethodInterceptor() {
			/**
			 *
			 * @param o
			 * @param method
			 * @param args
			 * @param methodProxy 代理之后的对象的方法引用
			 * @return
			 * @throws Throwable
			 */
			@Override
			public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				System.out.println("前置增强");
				Object invoke = methodProxy.invokeSuper(o, args);
				System.out.println("后置增强");
				return invoke;
			}
		});

		UserService service = (UserService) enhancer.create();
		return service;
	}
}
