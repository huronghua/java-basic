package com.github.java;


/**
 * @Description:
 * @Auther:Eric https://github.com/huronghua
 * @Date:2018/6/5 23 41
 */
public class Test {

	public static Integer add(Integer x){
		return x+=1;
	}

	public static void main(String[] args) {
		Child child = new Child();
		child.setName();
		System.out.println(child.getName());

	}



	public static class Parent{
		private String name = "wadwa";

		public String getName(){
			return this.name;
		}
	}

	public static class Child extends Parent{
		public void setName(){
			super.name = "huronghua";
		}
	}




}
