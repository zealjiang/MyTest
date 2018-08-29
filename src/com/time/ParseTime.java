package com.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class ParseTime {

	public static void main(String[] args) {

		/*
		 * //����һ String myString = "2011��12��29��"; String time =
		 * parseTime(myString); System.out.println("time :"+time);
		 */

		/*
		 * //���Զ� String str_time = "20111110152704"; String time =
		 * parseTime2(str_time); System.out.println("time :"+time);
		 */

		 //������ 
/*		 String myString = "20111110152704"; 
		 String time =parseTime3(myString);
		 System.out.println("time :"+time);*/
		 
/*		 parseTime4();
		 
		 String toDay = getToday();
		 System.out.println("time :"+toDay);*/
		 
		// ������
		//dateToString("2013-9-1");
		 
		 
		 //dateToString();

	}

	public static String parseTime(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
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
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM��dd��HH:mm");
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
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy��MM��dd��");
			r_data = sdf1.format(d);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r_data;
	}
	
	@Test
	public void dateToMillisec() {
		String sDate = "3050��01��01��";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
			r_data = sdf.format(date);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r_data;
	}


	/**
	 * DateתString
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
	 * ��String���͵�ʱ��ת��Date����
	 * @author zealjiang
	 * @date 2016��4��27�� ����6:36:03
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
	 * ��Dateת��String��ʱ���ʽ
	 * @author zealjiang
	 * @date 2016��4��27�� ����6:41:31
	 * @param date
	 * @return ����yyyy-MM-dd HH:mm:ss��Stringʱ���ʽ
	 *
	 */
	public static String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sDate = sdf.format(date);
		return sDate;
	}
	
	/**
	 * ����תSystem.currentTimeMillis
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
