package com.thread.extend.user;

import com.thread.extend.test.A;



public class B extends A{
	@Override
	public void b() {
		// TODO Auto-generated method stub
		System.out.println("Child Method b()");
		c();
	}
	public void c(){};
	
	
	
}
