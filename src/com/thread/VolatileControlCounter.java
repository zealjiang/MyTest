package com.thread;

/**
 * ���н������û������������1000
 * @author zealjiang
 * @time 2014��11��19�� ����4:41:10
 *
 */
public class VolatileControlCounter {

	public volatile static int count = 0;
	 
    public static void inc() {
 
        //�����ӳ�1���룬ʹ�ý������
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
 
        count++;
    }
 
    public static void main(String[] args) {
 
        //ͬʱ����1000���̣߳�ȥ����i++���㣬����ʵ�ʽ��
 
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                	VolatileControlCounter.inc();
                }
            }).start();
        }
 
        //����ÿ�����е�ֵ���п��ܲ�ͬ,����Ϊ1000
        System.out.println("���н��:Counter.count=" + VolatileControlCounter.count);
    }
}
