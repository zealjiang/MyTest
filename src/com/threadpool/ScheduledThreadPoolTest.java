package com.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {

	public static void main(String[] args) {
		// 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread("t1");
		Thread t2 = new MyThread("t2");
		Thread t3 = new MyThread("t3");
		// 将线程放入池中进行执行
		pool.execute(t1);
		// 使用延迟执行风格的方法
		pool.schedule(t2, 1000, TimeUnit.MILLISECONDS);
		pool.schedule(t3, 10, TimeUnit.MILLISECONDS);

		// 关闭线程池
		pool.shutdown();

	}
}

class MyThread extends Thread {
	public final String taskName;
	public MyThread(String taskName){
		this.taskName = taskName;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "正在执行。。。"+taskName);
	}
}
