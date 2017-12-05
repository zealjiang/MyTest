package com.thread;

/**
 * 
 * 用join 关键字达到预期结果
 * @author zealjiang
 * @time 2014年11月19日 下午2:10:19
 *
 */
public class SyncMultityThreadControlCounter {

	 public static int count = 0; 
	 static String str = new String();
	    public static void inc() {
	 	 

	        //这里延迟1毫秒，使得结果明显
	        try {
	            Thread.sleep(1);
	        } catch (InterruptedException e) {
	        }
	        System.out.println(Thread.currentThread().getName()+"  count: "+count);
        	count++;

	        
	    }
	 
	    public static void main(String[] args) {
	 
	        //同时启动1000个线程，去进行i++计算，看看实际结果
	 
	        for (int i = 0; i < 1000; i++) {
	            Thread t = new Thread(new Runnable() {
	                @Override
	                public void run() {
	                	SyncMultityThreadControlCounter.inc();
	                }
	            },""+i);
	            
	            t.start();
	            
	            try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	 
	        //这里每次运行的值都有可能不同,可能为1000
	        System.out.println("运行结果:Counter.count=" + SyncMultityThreadControlCounter.count);
	    }
}
