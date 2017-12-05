package com.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {

	public static void main(String[] args) {
		// ����һ���̳߳أ����ɰ����ڸ����ӳٺ�����������߶��ڵ�ִ�С�
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
		// ����ʵ����Runnable�ӿڶ���Thread����ȻҲʵ����Runnable�ӿ�
		Thread t1 = new MyThread("t1");
		Thread t2 = new MyThread("t2");
		Thread t3 = new MyThread("t3");
		// ���̷߳�����н���ִ��
		pool.execute(t1);
		// ʹ���ӳ�ִ�з��ķ���
		pool.schedule(t2, 1000, TimeUnit.MILLISECONDS);
		pool.schedule(t3, 10, TimeUnit.MILLISECONDS);

		// �ر��̳߳�
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
		System.out.println(Thread.currentThread().getName() + "����ִ�С�����"+taskName);
	}
}
