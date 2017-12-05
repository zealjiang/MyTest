package com.thread.extend.test;

public class A {
	public void  a(){
		System.out.println("Father Method a()");
		b();
		Aa a = new Aa();
		a.setTask("'b()'±»µ÷ÓÃ");
		c(a);
	}
	
	public void b(){};
	
	public void c(Aa a){
		
	}
	

}


