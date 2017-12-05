package com.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间相加
 * @author zealjiang
 * @time 2016年4月27日下午7:17:03
 */
public class TimeAdding {

	public static void main(String[] args) {
		TimeAdding ta = new TimeAdding();
		ta.addSec(null, 70);
		ta.addStringDateSec(ParseTime.dateToString(new Date()), 20);
	}
	
	/**
	 * 指定的日期加上sec秒后的新日期
	 * @author zealjiang
	 * @date 2016年4月27日 下午6:23:46
	 * @param date
	 * @param sec
	 * @return
	 *
	 */
	public Date addSec(Date date,int sec){
		if(date==null){
			date = new Date();
		}
		// format对象是用来以指定的时间格式格式化时间的
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 这里的格式可以自己设置
		// format()方法是用来格式化时间的方法
		String times = from.format(date);
		//原始时间
		System.out.println("原始时间："+times);
		Calendar cal = DateToCalendar(date);
		cal.add(Calendar.SECOND, sec);//给当前时间加sec秒后的时间
		Date dateNew = CalendarToDate(cal);
		times=from.format(dateNew);
		System.out.println("相加之后的时间："+times);
		return dateNew;
	}
	
	/**
	 * Date转Calendar
	 * @author zealjiang
	 * @date 2016年4月27日 下午5:39:17
	 * @param date
	 * @return
	 *
	 */
	private Calendar DateToCalendar(Date date){
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
	private Date CalendarToDate(Calendar cal){
		Date date=cal.getTime();
		return date;
	}
	
	/**
	 * 返回将String类型的时间加上sec秒后的新的String格式的时间
	 * @author zealjiang
	 * @date 2016年4月28日 上午9:32:43
	 * @param sdate string类型的时间格式 yyyy-MM-dd HH:mm:ss 格式
	 * @param sec 秒
	 * @return 返回将String类型的时间加上sec秒后的新的String格式的时间
	 *
	 */
	public String addStringDateSec(String sdate,int sec){
		Date date = ParseTime.parseStringTime2Date(sdate);
		// format对象是用来以指定的时间格式格式化时间的
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 这里的格式可以自己设置
		// format()方法是用来格式化时间的方法
		String times = from.format(date);
		//原始时间
		System.out.println("原始时间："+times);
		Calendar cal = DateToCalendar(date);
		cal.add(Calendar.SECOND, sec);//给当前时间加sec秒后的时间
		Date dateNew = CalendarToDate(cal);
		times=from.format(dateNew);
		System.out.println("相加之后的时间："+times);
		return times;
	}
}
