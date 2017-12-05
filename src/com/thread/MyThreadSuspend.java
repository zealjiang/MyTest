package com.thread;



public class MyThreadSuspend extends Thread{

	public boolean pleaseWait = false;
    public static int TASK = 0;

    public static void main(String[] args) {
    	MyThreadSuspend mt = new MyThreadSuspend();
    	mt.start();
    	for (int i = 0; i < 10; i++) {
			System.out.println("i "+i);
		}
	}


    // This method is called when the thread runs
    public void run() {
    	
/*	        while (true) {
		            // Do work
		        	System.out.println(Thread.currentThread().getName()+" run .");
		            // Check if should wait
		        	synchronized (this) {
		                    try {
		                    	switch(TASK){
		                    	case 1:
		                    		task1();
		                    		break;
		                    	case 2:
		                    		task2();
		                    		break;
		                    	case 3:
		                    		task3();
		                    		break;
		                    	
		                    	}
		                    	    	
		                    	System.out.println(Thread.currentThread().getName()+" suspend ");
		                    	
		                        Thread.currentThread().suspend();
		                        System.out.println("this:"+this.getName());
		                    } catch (Exception e) {
		                    	System.out.println("catch :"+e);
		                    }         
		        	}
	        }*/
    }
    
	private  void task1(){
		System.out.println("task1");

	}
    
	private  void task2(){
		System.out.println("task2");

	}
	
	private  void task3(){
		System.out.println("task3");

	}
}
