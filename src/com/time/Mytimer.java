package com.time;

import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

public class Mytimer {
	public static void main(String[] args) {
/*		Timestamp time = new java.sql.Timestamp(new java.util.Date().getTime());
		System.out.println(String.valueOf(time.getHours())+":"+String.valueOf(time.getMinutes()+":"+String.valueOf(time.getSeconds())));
		System.out.println(System.currentTimeMillis());
		System.out.println(time.getTime()+"  "+time.getDate()+"  "+time.getDay()+"  "+time.getYear());
		Time time2 = new java.sql.Time(new java.util.Date().getTime());
		System.out.println(time2.getHours()+"  "+time2.toString());*/
		System.out.println(">>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
		System.out.println(">>"+new SimpleDateFormat("ss").format(new Date().getTime()));
/*		 String[] formats = new String[] {
				   "yyyy-MM-dd",
				   "yyyy-MM-dd HH:mm",
				   "yyyy-MM-dd HH:mmZ",
				   "yyyy-MM-dd HH:mm:ss.SSSZ",
				   "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
				 };
		 
				 for (String format : formats) {
				   SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
				   System.err.format("%30s %s\n", format, sdf.format(new Date().getTime()));
				   sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				   System.err.format("%30s %s\n", format, sdf.format(new Date().getTime()));
				 }*/
	}
	
	@Test
	public void calculateTimeDelta(){
		Date curDate = new Date(System.currentTimeMillis());
		long tStart = System.currentTimeMillis();
		//PROCESSING
		try {
			Thread.sleep(123);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date endDate = new Date(System.currentTimeMillis());
		long diff = endDate.getTime() - curDate.getTime();
		System.out.println("diff :"+diff);
		
		long tEnd = System.currentTimeMillis();
		diff = tEnd - tStart;
		System.out.println("diff :"+diff);
	}
}
