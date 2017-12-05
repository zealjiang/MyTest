package com.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

		/*
		 * //测试三 String myString = "20111110152704"; String time =
		 * parseTime3(myString); System.out.println("time :"+time);
		 */

		// 测试四
		dateToString("2013-9-1");

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
}
