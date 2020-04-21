package com.github.java;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther:Eric https://github.com/huronghua
 * @Date:2018/7/4 23 55
 */
public class Lambda {

	public static void main(String[] args) {

		Gson gson = new Gson();
		Map<String,Object> map = Collections.synchronizedMap(new HashMap<>());
		map.put("1","huronghua");
		map.put("2","huronghua");
		map.put("3","huronghua");

		List<String> list = map.keySet().stream().collect(Collectors.toList());

		System.out.println(gson.toJson(list));

	}
}
