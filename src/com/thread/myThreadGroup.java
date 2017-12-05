package com.thread;

public class myThreadGroup {
	
	
	public static void main(String[] args) {
		
		makeThreadGroup();
	}
	
	public static void makeThreadGroup(){
		//做一个线程组，名字是"myTest1"
		ThreadGroup tg = new ThreadGroup("myTest1");
		
		//做三个线程放到这个线程组中,名字分别是t1,t2,t3
		Thread t1 = new Thread(tg,"t1");
		Thread t2 = new Thread(tg,new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<10;i++){
					System.out.println("i  :"+i);
				}
			}
			
		},"t2");
		Thread t3 = new Thread(tg,"t3");
		t2.run();
		System.out.println("tg.activeCount()"+tg.activeCount());
		System.out.println("tg.activeGroupCount()"+tg.activeGroupCount());
		tg.list();
		System.out.println("tg.activeCount()"+tg.activeCount());
		System.out.println("tg.activeGroupCount()"+tg.activeGroupCount());
		
	
		
		
	}

}


