package com.github.java.spring5;

/**
 * @Description:
 * @Auther:Eric https://github.com/huronghua
 * @Date:2019-04-21 10 01
 */
public class UserServiceImpl implements UserService {

	@Override
	public void getUserInfo() {
		System.out.println("获取用户信息");
	}
}
