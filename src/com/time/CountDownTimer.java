package com.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CountDownTimer {

	private Thread t;
	public static void main(String[] args) {
		
		CountDownTimer c = new CountDownTimer();
		
//		countDownTimer2(2*1000);
//		countDownTimer2(5*1000);
		countDownTimer2(7*1000);
//		countDownTimer2(10*1000);
//		countDowntimer(5*60);
	}
	//����һ
	/**
	 * time_gap��ʾ����ʱ�೤ʱ�䣬��λ����(ʱ����������1Сʱ)
	 * @param time_gap
	 */
	public static void countDowntimer(final int time_gap){
		
		new Thread(){
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int endSecond=-1,endMinute=-1;
				String str_endSecond="",str_endMinute="";
				String startTime,concurrentTime,endTime;
				
				
				System.out.println("��ʱ��ʼ");
				startTime = new SimpleDateFormat("mm:ss").format(new Date().getTime());
				
				System.out.println(startTime);
				int minute  = time_gap/60;
				int second  = time_gap%60;
				System.out.println(minute+"  "+second);
				String[] time = startTime.split(":");
//				System.out.println(time[0]+"      "+time[1]+"   "+Integer.valueOf(time[0])+"   "+Integer.valueOf(time[1]));
//				System.out.println(Integer.valueOf(time[1])+second);
				//��ǰʱ�������ϼ��ʱ�����
				int temp_second = Integer.valueOf(time[1])+second;
				//��ǰʱ��ķּ��ϼ��ʱ��ķ�
				int temp_minute = Integer.valueOf(time[0])+minute;
				
				//�����ǰʱ�������ϼ��ʱ��������60
				if(temp_second>=60){
					endSecond = temp_second-60;
					temp_minute = temp_minute + 1;
				}else{
					endSecond = temp_second;
				}
//				System.out.println("endSecond  "+endSecond);
				if(temp_minute>=60){
					endMinute = temp_minute - 60;
				}else{
					endMinute = temp_minute;
				}
				if(endSecond<10){
					str_endSecond = "0"+String.valueOf(endSecond);
				}else{
					str_endSecond = String.valueOf(endSecond);
				}
				if(endMinute<10){
					str_endMinute = "0"+String.valueOf(endMinute);
				}else{
					str_endMinute = String.valueOf(endMinute);
				}
//				System.out.println("str_endSecond  "+str_endSecond+"  str_endMinute  "+str_endMinute);
				endTime = str_endMinute+":"+str_endSecond;
				System.out.println("endTime :"+endTime);
				
				//��ȡϵͳ��ǰʱ��
				String concurrentTime_old = new SimpleDateFormat("mm:ss").format(new Date().getTime());
				concurrentTime  = concurrentTime_old;
				String concurrentTime1;
				long time1 = System.currentTimeMillis();
				while(!(endTime.equals(concurrentTime))){
					long time2 = System.currentTimeMillis();
					if((time2-time1)>=1000){
						System.out.println(concurrentTime + "  "+endTime);
						time1 = time2;
					}
					
					concurrentTime = new SimpleDateFormat("mm:ss").format(new Date().getTime());
					
							
				}
				System.out.println("ʱ�䵽");
			}
			
		}.start();
	}
	
	//��������
	/**
	 * @param timer ��ʾ����ʱ��ʱ�䣬��λ�Ǻ���
	 */
	public static  void countDownTimer2(final long timer){
		
		
		new Thread(new Runnable(){
			@Override
			public void run() {
					// TODO Auto-generated method stub
					long concurrentTime = 0;
					long time1 = System.currentTimeMillis();
					long startTime = System.currentTimeMillis();
					long endTime   = startTime+timer;
					System.out.println("��ʼ��ʱ");
					while(concurrentTime<=endTime){
						long time2 = System.currentTimeMillis();
						if((time2-time1)>=1000){
//							System.out.println(concurrentTime);
							time1 = time2;
						}
						concurrentTime = System.currentTimeMillis();
					}
					System.out.println("ʱ�䵽");
				}
			
		}).start();
		
	}
}
