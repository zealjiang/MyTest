package com.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * @author zj
 * @time 2013-11-19下午4:19:53
 */
public class DateUtils {
	
	public static void main(String[] args) {
		
		System.out.println("本月有" +getCurrentMonthLastDay()+ "天");
		
		DateUtils du = new DateUtils();

		System.out.println("今天日期是：" + du.getNowDate());

		System.out.println("本月有" + du.getDays(du.getYear(), du.getMonth())
				+ "天");

		System.out.println("本月有" + du.getSundays(du.getYear(), du.getMonth())
				+ "个星期天");
	}
	
	
	/**
	 * 
	 * 取得当月天数
	 * 
	 * */

	public static int getCurrentMonthLastDay()

	{

		Calendar a = Calendar.getInstance();

		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		//System.out.println(a.getTime());
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		//System.out.println(a.getTime());
		int maxDate = a.get(Calendar.DATE);
		
		return maxDate;

	}

	/**
	 * 
	 * 得到指定月的天数
	 * 
	 * */

	public static int getMonthLastDay(int year, int month)

	{

		Calendar a = Calendar.getInstance();

		a.set(Calendar.YEAR, year);

		a.set(Calendar.MONTH, month - 1);

		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天

		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天

		int maxDate = a.get(Calendar.DATE);

		return maxDate;

	}

	

	// 返回当前年月日

	String getNowDate() {

		Date date = new Date();

		String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(date);

		return nowDate;

	}

	// 返回当前年份

	int getYear()

	{

		Date date = new Date();

		String year = new SimpleDateFormat("yyyy").format(date);

		return Integer.parseInt(year);

	}

	// 返回当前月份

	int getMonth()

	{

		Date date = new Date();

		String month = new SimpleDateFormat("MM").format(date);

		return Integer.parseInt(month);

	}

	// 判断闰年

	boolean isLeap(int year)

	{

		if (((year % 100 == 0) && year % 400 == 0)
				|| ((year % 100 != 0) && year % 4 == 0))

			return true;

		else

			return false;

	}

	// 返回当月天数

	int getDays(int year, int month)

	{

		int days;

		int FebDay = 28;

		if (isLeap(year))

			FebDay = 29;

		switch (month)

		{

		case 1:

		case 3:

		case 5:

		case 7:

		case 8:

		case 10:

		case 12:

			days = 31;

			break;

		case 4:

		case 6:

		case 9:

		case 11:

			days = 30;

			break;

		case 2:

			days = FebDay;

			break;

		default:

			days = 0;

			break;

		}

		return days;

	}

	// 返回当月星期天数

	int getSundays(int year, int month)

	{

		int sundays = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");

		Calendar setDate = Calendar.getInstance();

		// 从第一天开始

		int day;

		for (day = 1; day <= getDays(year, month); day++)

		{

			setDate.set(Calendar.DATE, day);

			String str = sdf.format(setDate.getTime());

			if (str.equals("星期日"))

			{

				sundays++;

			}

		}

		return sundays;

	}

	@Test
	public void getShortCalendar() {
		String strDate = "2015-05-16";
		String patten = "yyyy-MM-dd";
		String strFullDate = strDate.trim();
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		try {
			Date date = (Date) formatter.parse(strDate);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			System.out.println(getYearMonthDate(calendar.getTime()));
		} catch (ParseException e) {
		}
	}
	
	public static String getStringDate(String strDate) {
		String patten = "yyyy-MM-dd";
		String strFullDate = strDate.trim();
		SimpleDateFormat formatter = new SimpleDateFormat(patten);

		Date date;
		try {
			date = (Date) formatter.parse(strFullDate);
			SimpleDateFormat sf = new SimpleDateFormat(patten);
			String sDate = sf.format(date);
			return sDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "";
	}

	/**
	 * 获取当前时间的时间戳
	 */
	public static String getYearMonthDate(Date date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		String sDate = sf.format(date);

		return sDate;
	}
	
	/**
	 * 
	 * @note 将"yyyy-MM-dd"格式的日期字符串转成对应的Calendar
	 * @author zealjiang
	 * @time 2015-5-16下午5:03:33
	 * @param strDate
	 * @return
	 */
	public Calendar getShortCalendar(String strDate) {
		String patten = "yyyy-MM-dd";
		String strFullDate = strDate.trim();
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		try {
			Date date = (Date) formatter.parse(strFullDate);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			
			return calendar;
		} catch (ParseException e) {
		}
		
		return null;
	}
	
	/**
	 * Date转Calendar
	 * @author zealjiang
	 * @date 2016年4月27日 下午5:39:17
	 * @param date
	 * @return
	 *
	 */
	public Calendar DateToCalendar(Date date){
		Calendar cal=Calendar.getInstance();  
		cal.setTime(date);  
		return cal;
	}
	
	/**
	 * Calendar转Date
	 * @author zealjiang
	 * @date 2016年4月27日 下午5:40:31
	 * @return
	 *
	 */
	public Date CalendarToDate(){
		Calendar cal=Calendar.getInstance();
		Date date=cal.getTime();
		return date;
	}
}
