package com.github.java.spring5;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Auther:Eric https://github.com/huronghua
 * @Date:2019-04-21 11 22
 */
public class JDKProxyTest {


	@Test
	public void test(){
		UserService cglibProxy = JDKProxy.getCglibProxy(new UserServiceImpl());
		cglibProxy.getUserInfo();

		UserService jdkProxy = JDKProxy.getJDKProxy(new UserServiceImpl());
		jdkProxy.getUserInfo();
	}

}