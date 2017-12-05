package com.thread;

/**
 * ʵ��������ÿ�ο��ܶ���һ���������Ľ��Ϊ�����н��:Counter.count=995��
 * ���Կ������ڶ��̵߳Ļ����£�Counter.count��û�����������1000
 * @author zealjiang
 * @time 2014��11��19�� ����2:10:19
 *
 */
public class MultityThreadControlCounter {

	 public static int count = 0;
	 
	    public static void inc() {
	 
	        //�����ӳ�1���룬ʹ�ý������
	        try {
	            Thread.sleep(1);
	        } catch (InterruptedException e) {
	        }
	        System.out.println(Thread.currentThread().getName()+"  count: "+count);
	        count++;
	    }
	 
	    public static void main(String[] args) {
	 
	        //ͬʱ����1000���̣߳�ȥ����i++���㣬����ʵ�ʽ��
	 
	        for (int i = 0; i < 1000; i++) {
	        	new Thread(new Runnable() {
	                @Override
	                public void run() {
	                	MultityThreadControlCounter.inc();
	                }
	            },""+i).start();
	        	
	        	
	        }
	 
	        //����ÿ�����е�ֵ���п��ܲ�ͬ,����Ϊ1000
	        System.out.println("���н��:Counter.count=" + MultityThreadControlCounter.count);
	    }
}
