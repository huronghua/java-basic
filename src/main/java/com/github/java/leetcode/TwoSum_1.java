package com.github.java.leetcode;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther:Eric https://github.com/huronghua
 * @Date:2019-06-30 09 56
 */
public class TwoSum_1 {
	public int[] twoSum(int[] nums, int target) {
		for(int i = 0;i<nums.length;i++){
			for(int j = i + 1;j <nums.length;j++){
				if(nums[i]+nums[j]==target){
					return new int[]{i,j};
				}
			}
		}
		return null;
	}

	public int[] twoSumMap(int[] nums, int target){
		Map<Integer, Integer> map = new HashMap<>(8);
		for(int i = 0; i<nums.length;i++){
			int value = target - nums[i];
			//这里一定要用索引对应的值作为key
			if(map.containsKey(value)){
				return new int[]{i,map.get(value)};
			}
			map.put(nums[i],i);
		}
		throw new IllegalArgumentException("找不到符合条件数");
	}

	public static void main(String[] args) {
		TwoSum_1 twoSum1 = new TwoSum_1();
		System.out.println(JSONObject.toJSONString(twoSum1.twoSum(new int[]{2,7,11,15},22)));
		System.out.println(JSONObject.toJSONString(twoSum1.twoSumMap(new int[]{2,7,11,15},22)));
	}



}
