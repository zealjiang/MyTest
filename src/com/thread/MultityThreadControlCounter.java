package com.thread;

/**
 * 实际运算结果每次可能都不一样，本机的结果为：运行结果:Counter.count=995，
 * 可以看出，在多线程的环境下，Counter.count并没有期望结果是1000
 * @author zealjiang
 * @time 2014年11月19日 下午2:10:19
 *
 */
public class MultityThreadControlCounter {

	 public static int count = 0;
	 
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
	        	new Thread(new Runnable() {
	                @Override
	                public void run() {
	                	MultityThreadControlCounter.inc();
	                }
	            },""+i).start();
	        	
	        	
	        }
	 
	        //这里每次运行的值都有可能不同,可能为1000
	        System.out.println("运行结果:Counter.count=" + MultityThreadControlCounter.count);
	    }
}
