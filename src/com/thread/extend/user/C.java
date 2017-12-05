package com.thread.extend.user;

import com.thread.extend.test.Aa;


public class C extends B{

	public void c(){
		System.out.println("Child C Method!");
		
	};
	
	public static void main(String[] args) {
		C c = new C();
		c.a();
	}
	
	@Override
	public void c(Aa a){
		System.out.println("C--"+a.getTask());
	}
	
}
