package com.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class ParseTime {

	public static void main(String[] args) {

		/*
		 * //测试一 String myString = "2011年12月29日"; String time =
		 * parseTime(myString); System.out.println("time :"+time);
		 */

		/*
		 * //测试二 String str_time = "20111110152704"; String time =
		 * parseTime2(str_time); System.out.println("time :"+time);
		 */

		 //测试三 
/*		 String myString = "20111110152704"; 
		 String time =parseTime3(myString);
		 System.out.println("time :"+time);*/
		 
/*		 parseTime4();
		 
		 String toDay = getToday();
		 System.out.println("time :"+toDay);*/
		 
		// 测试四
		//dateToString("2013-9-1");
		 
		 
		 //dateToString();

	}

	public static String parseTime(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String r_data = "";
		try {
			Date d = sdf.parse(date);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			r_data = sdf1.format(d);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r_data;
	}

	public static String parseTime2(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String r_data = "";
		try {
			Date d = sdf.parse(date);
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM月dd日HH:mm");
			r_data = sdf1.format(d);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r_data;
	}

	public static String parseTime3(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String r_data = "";
		try {
			Date d = sdf.parse(date);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");
			r_data = sdf1.format(d);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r_data;
	}
	
	@Test
	public void dateToMillisec() {
		String sDate = "3050年01月01日";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String r_data = "";
		try {
			Date d = sdf.parse(sDate);
			long time = d.getTime();
			System.out.println("time :"+time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void millisecToDate() {

		String r_data = "";
		try {
			long time = System.currentTimeMillis();
			System.out.println("time :"+time);
			time = 1521475200000l;
			time = 968930610743l;
			time = 1860666930666l;
			time = 1522857600000l;
			Date date = new Date(time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			r_data = sdf.format(date);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("time :"+r_data);
	}
	
	public static String getToday() {

		String r_data = "";
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			r_data = sdf.format(date);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r_data;
	}


	/**
	 * Date转String
	 * 
	 * @param count
	 * @return
	 */
	public static String dateToString(String date) {
		String time = null;
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			Date str = formatDate.parse(date);
			// System.out.println("str-->"+str);

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			time = format.format(str);
			System.out.println("time-->" + time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 将String类型的时间转成Date类型
	 * @author zealjiang
	 * @date 2016年4月27日 下午6:36:03
	 * @param date
	 * @return
	 *
	 */
	public static Date parseStringTime2Date(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 将Date转成String的时间格式
	 * @author zealjiang
	 * @date 2016年4月27日 下午6:41:31
	 * @param date
	 * @return 返回yyyy-MM-dd HH:mm:ss的String时间格式
	 *
	 */
	public static String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sDate = sdf.format(date);
		return sDate;
	}
	
	/**
	 * 日期转System.currentTimeMillis
	 * @return
	 */
	@Test
	public void dateToString(){
		Date date = new Date();
		long time = date.getTime();
		System.out.println("time-->" + time);
		System.out.println("time-->" + System.currentTimeMillis());
		
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date2018 = formatDate.parse("2018-03-20");
			System.out.println("date2018-->" + date2018.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
