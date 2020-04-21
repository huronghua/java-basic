package com.github.java.leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Description:有效的括号(处理对称问题)
 * @Auther:Eric https://github.com/huronghua
 * @Date:2019-07-01 22 34
 */
public class StringIsValid_20 {

	/**
	 * 在表示问题的递归结构时，栈数据结构可以派上用场。我们无法真正地从内到外处理这个问题，因为我们对整体结构一无所知。
	 * 但是，栈可以帮助我们递归地处理这种情况，即从外部到内部。
	 * 栈顶总是保存程序处理最内部的一个递归节点的现场情况
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {

		HashMap<Character,Character> dic = new HashMap<>();
		dic.put(')','(');
		dic.put('}','{');
		dic.put(']','[');
		Stack<Character> stack = new Stack<>();
		for(int i=0;i<s.length();i++){
			if(dic.containsKey(s.charAt(i))){
				if(stack.isEmpty()){
					return false;
				}
				Character c = stack.pop();
				//必须是一个成对的闭括号
				if(c!=dic.get(s.charAt(i))){
					return false;
				}
			}else {
				stack.push(s.charAt(i));
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		StringIsValid_20 stringIsValid_6 = new StringIsValid_20();
		System.out.println(stringIsValid_6.isValid("()"));
	}
}
