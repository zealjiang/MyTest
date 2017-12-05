package com.thread;


public class MyThreadWait extends Thread{

    boolean pleaseWait = false;

    static MyThreadWait thread;
    


	public static void main(String[] args) throws InterruptedException {
		
		thread = new MyThreadWait();
		thread.setName("myThread");
	    thread.start();
	    
	    while (true) {
	        // Do work
	    	System.out.println(Thread.currentThread().getName()+".");
	        // Pause the thread
	        synchronized (thread) {
	            thread.pleaseWait = true;
	            System.out.println(Thread.currentThread().getName()+" true");
	        }
	    
	        // Do work
	        System.out.println(Thread.currentThread().getName()+"..");
	        // Resume the thread
	        synchronized (thread) {
	            thread.pleaseWait = false;
	            System.out.println(" false");
	            Thread.sleep(2000);
	            thread.notify();
	        }
	        System.out.println(Thread.currentThread().getName()+"...");
	        break;
	        // Do work
	    }
	}
    // This method is called when the thread runs
    public void run() {
        while (true) {
            // Do work
        	System.out.println(Thread.currentThread().getName()+" run .");
            // Check if should wait
            synchronized (this) {
                while (pleaseWait) {
                    try {
                    	System.out.println(Thread.currentThread().getName()+" wait ");
                    	System.out.println("this:"+this.getName());
                        this.wait();
                    } catch (Exception e) {
                    	
                    }
                }
            }

            // Do work
            System.out.println(Thread.currentThread().getName()+" run ..");
            break;
        }
    }
}
