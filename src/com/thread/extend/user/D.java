package com.thread.extend.user;


public class D extends Thread{
	public static void main(String[] args) {
		D d = new D();

		
		d.start();
	}
	
	public void  run() {
		System.out.println("run");
	}
}
