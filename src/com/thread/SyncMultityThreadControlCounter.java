package com.thread;

/**
 * 
 * ��join �ؼ��ִﵽԤ�ڽ��
 * @author zealjiang
 * @time 2014��11��19�� ����2:10:19
 *
 */
public class SyncMultityThreadControlCounter {

	 public static int count = 0; 
	 static String str = new String();
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
	 
	        //����ÿ�����е�ֵ���п��ܲ�ͬ,����Ϊ1000
	        System.out.println("���н��:Counter.count=" + SyncMultityThreadControlCounter.count);
	    }
}
