package com.github.java.leetcode;


import java.util.HashMap;

/**
 * @Description: 无重复字符的最长子串
 * @Auther:Eric https://github.com/huronghua
 * @Date:2019-06-30 21 27
 */
public class LengthOfLongestSubstring_3 {
	/**
	 * 1.滑动窗口法
	 * map以字符作为key,位置索引作为value
	 * 每次循环都往map中更新字符串位置，并且有重复的字符就更新该字符的位置索引
	 * 如果遇到重复的字符，则不重复字符串的起始位置left就变成map中该字符的value索引的位置，
	 * 并且最大的子串长度原本最长的子串和当前新的不重复子串长度（当前位置i-起始位置left+1）的较大值，直至遍历结束
	 */


	public int lengthOfLongestSubstring(String s) {
		if(s.length()==0){
			return 0;
		}
		int max = 0;
		int left = 0;
		HashMap<Character,Integer> map = new HashMap<>();
		for(int i=0;i<s.length();i++){
			if(map.containsKey(s.charAt(i))){
				left = Math.max(left,map.get(s.charAt(i))+1);
			}
			map.put(s.charAt(i),i);
			max = Math.max(max,i-left+1);
		}
		return max;
	}

	//2.下面演示暴力算法
	public int baoli(String s) {
		String result = "";
		if(s.length()>0){
			for (int i = 0; i < s.length(); i++) {
				for (int k = i; k < s.length(); k++) {
					String newStr = s.substring(i, k+1);
					if (hasDuplicateChar(newStr)) {
						break;
					} else {
						if(result.length()<newStr.length()){
							result = newStr;
						}
					}
				}
			}
		}
		return result.length();
	}

	public boolean hasDuplicateChar(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j)) {
					count++;
					return true;
				}
			}

		}
		return count > 0;
	}


	public static void main(String[] args) {
		LengthOfLongestSubstring_3 length = new LengthOfLongestSubstring_3();
		System.out.println(length.lengthOfLongestSubstring("wad"));
	}
}
