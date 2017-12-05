package com.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class SimpleDateFormatTest {

	public static void main(String[] args) {
		
		//time();
		IsTrueTime();
		
	}
	
	public static void time(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String djrq = sdf.format(new Date());
		System.out.println(djrq);
	}
	
	public static void IsTrueTime(){
		String time = "19860804000000";
		String time2 = "1986年3月2日";
		SimpleDateFormat test = new SimpleDateFormat();
		
		
/*		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd000000");
	 
		Date boo;
		try {
			boo = sdf.(time);
			System.out.println(boo.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	
	/**
	 * 打印当前时间 年-月-日 时:分:秒 毫秒
	 */
	@Test
    public void getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS");
        String sTime = simpleDateFormat.format(new Date());
        System.out.println("当前时间： "+sTime);
        
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        sTime = simpleDateFormat.format(new Date());
        System.out.println("当前时间： "+sTime);
    }
	
	@Test
	public void formatTest(){
		String sTime = "2015-3-16";
		

        String[] dateArray = sTime.split("-");
        String year = dateArray[0];
        String month = dateArray[1];
        String day = dateArray[2];
        System.out.println( year+"-"+month+"-"+day);
		
		
		String[] a = sTime.split("-");
		String yyyy = a[0];
		String mm = a[1];
		String dd = a[2];
		System.out.println(yyyy+" "+mm+" "+dd);
		
		

        
	}
}
