package com.thread;

public class ThreadRunTest extends Thread {

	byte[] LOCK = new byte[8];
	public static void main(String[] args) {
		
	
		ThreadRunTest tr = new ThreadRunTest();
		tr.start();
		System.out.println("main :"+Thread.currentThread().getName());
		
		try {
			
			
			boolean isAlive = tr.isAlive();
			boolean isInter = tr.interrupted();
			Thread.currentThread().sleep(1000);
			//System.out.println(tr.getName()+" is Alive?"+isAlive+ " is Interrupted ? "+isInter);		
			System.out.println("时间到");
			//tr.resume();
			tr.notifyAll();
			//System.out.println("after run:"+tr.getName()+" is Alive?"+isAlive+ " is Interrupted ? "+isInter);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			return;
		}
		
	}

	@Override
	public void run() {
		while(true){
			synchronized (LOCK){
				System.out.println("线程"+Thread.currentThread().getName()+"开始运行-------");
				task1();
					System.out.println(Thread.currentThread().getName()+" State:"+Thread.currentThread().getState());
	
					try {
						Thread.currentThread().wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//suspend();
	
					//Thread.currentThread().suspend();
					System.out.println(Thread.currentThread().getName()+" wait/ suspend");
					System.out.println(Thread.currentThread().getName()+" State:"+Thread.currentThread().getState());
				task2();
	
	/*			task3();
					Thread.currentThread().suspend();*/
			}
		}
	}
	
	public static void task1() {
		System.out.println("task1 "+Thread.currentThread().getName()+" State:"+Thread.currentThread().getState());
		boolean isAlive = Thread.currentThread().isAlive();
		boolean isInter = Thread.currentThread().interrupted();
		System.out.println("task1 "+Thread.currentThread().getName()+" is Alive?"+isAlive+ " Thread is Interrupted ? "+isInter);
	}
	
	public static void task2(){
		System.out.println("task2");
	}
	
	public static void task3(){
		System.out.println("task3");
	}


}


