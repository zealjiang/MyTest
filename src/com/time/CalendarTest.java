package com.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;


public class CalendarTest {

	@Test
	public void test1(){
		System.out.println("Calendar.MONDAY  :"+Calendar.MONDAY);//2
		System.out.println("Calendar.TUESDAY  :"+Calendar.TUESDAY);//3
		System.out.println("Calendar.WEDNESDAY  :"+Calendar.WEDNESDAY);//4
		System.out.println("Calendar.THURSDAY  :"+Calendar.THURSDAY);//5
		System.out.println("Calendar.FRIDAY  :"+Calendar.FRIDAY);//6
		System.out.println("Calendar.SATURDAY  :"+Calendar.SATURDAY);//7
		System.out.println("Calendar.SUNDAY  :"+Calendar.SUNDAY);	//1
	}
	
	@Test
	public void test2(){
		Calendar calStartDate = Calendar.getInstance();
		int i = calStartDate.get(Calendar.DAY_OF_WEEK);	//当天是本周的第几天  （周一是2）
		if(i==1){
			System.out.println("今天是周 日");
		}else			
			System.out.println("今天是周 :"+(i-1));
	}
	
	
	@Test
	public void test2_1(){
		Calendar calStartDate = Calendar.getInstance();
		calStartDate.set(Calendar.DAY_OF_WEEK, 3);
		int i = calStartDate.get(Calendar.DAY_OF_WEEK);	//当天是本周的第几天  （周一是2）
		if(i==1){
			System.out.println("今天是周 日");
		}else			
			System.out.println("今天是周 :"+(i-1));
	}
	
	@Test
	public void test3(){
		Calendar calStartDate = Calendar.getInstance();
		calStartDate.set(Calendar.DATE, 1); // 设置成当月第一天
		int i = calStartDate.get(Calendar.DAY_OF_WEEK);	//一周中的第几天
		if(i==1){
			System.out.println("本月1号是周 日");
		}else			
			System.out.println("本月1号是周 :"+(i-1));
	}
	
	@Test
	public void test3_1(){
		Calendar calStartDate = Calendar.getInstance();
		int i = calStartDate.get(Calendar.MONTH);	//月
		System.out.println("i :"+i);
		System.out.println("当前是"+(i+1)+"月");
	}
	
	
	@Test
	public void test3_2(){
		Calendar calStartDate = Calendar.getInstance();
		int i = calStartDate.get(Calendar.YEAR);
		System.out.println("i :"+i);
		System.out.println("今年是"+i+"年");
	}
	
	@Test
	public void test4(){
		Calendar calStartDate = Calendar.getInstance();
		int day = calStartDate.get(Calendar.DATE);
		
		System.out.println("今天是 :"+day+" 号");
	}
	
	@Test
	public void test5(){
		Calendar calStartDate = Calendar.getInstance();
		calStartDate.add(Calendar.DAY_OF_MONTH, 1);
		int day = calStartDate.get(Calendar.DATE);
		
		System.out.println("明天是 :"+day+" 号");
	}
	
	
	@Test
	public void getDayInMonthOfThisWeekMonday(){
		Calendar calStartDate = Calendar.getInstance();
		
		int iDay = 0;
		iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
		if(iDay < 0){
			iDay = 6;
		}
		calStartDate.add(Calendar.DAY_OF_WEEK, -iDay);
		int day = calStartDate.get(Calendar.DATE);
		
		System.out.println("本周一是 :"+day+" 号");
	}
	
	
	@Test
	public void test6(){
		Calendar calStartDate = Calendar.getInstance();
		String lunar = new CalendarUtil(calStartDate).getDay();		
		System.out.println("今天是农历 :"+lunar);
		
		CalendarUtil calendarUtil = new CalendarUtil(calStartDate);
		lunar = calendarUtil.toString();		
		System.out.println("今天是农历节日 :"+lunar);
	}
	
	@Test
	public void test7(){
		Calendar calStartDate = Calendar.getInstance();	
		int count = calStartDate.getActualMaximum(Calendar.DATE);
		System.out.println("本月共 :"+count+"天");
	}
	
	@Test
	public void test7_2(){
		//2016年2月  28天
		Calendar calStartDate = Calendar.getInstance();	
		calStartDate.set(Calendar.MONTH,1);
		calStartDate.set(Calendar.DAY_OF_MONTH,1);//注意 如果不设置这句话，当今天是30号，设置的当前月是2月时，得出的当前时间就会不对（因为2017年2月是28天，今天是30号，大于28，多了2，得出的月就会进1，天加2）
		int count = calStartDate.getActualMaximum(Calendar.DATE);
		System.out.println("本月共 :"+count+"天");
		printCalendar("",calStartDate);
		
		
		//2016年2月 29天
		calStartDate = Calendar.getInstance();	
		calStartDate.set(Calendar.MONTH,1);
		calStartDate.set(Calendar.DAY_OF_MONTH,1);//注意 如果不设置这句话，当今天是30号，设置的当前月是2月时，得出的当前时间就会不对（因为2017年2月是28天，今天是30号，大于28，多了2，得出的月就会进1，天加2）
		count = calStartDate.getActualMaximum(Calendar.DATE);
		System.out.println("本月共 :"+count+"天");
		printCalendar("",calStartDate);
	}
	
	@Test
	public void test8(){
		Calendar calStartDate = Calendar.getInstance();	
		System.out.println("year :"+calStartDate.get(Calendar.YEAR)
				+"  month :"+calStartDate.get(Calendar.MONTH)
				+"  date :"+calStartDate.get(Calendar.DATE));
		System.out.println("year :"+calStartDate.YEAR);
		System.out.println("month :"+calStartDate.MONTH);
		System.out.println("day :"+calStartDate.DATE);
	}
	
	public static void printCalendar(String prefix,Calendar calendar){
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		System.out.println(prefix+"print year:"+year+" month:"+(month+1)+"  day:"+day);	
	}
	
	@Test
	public void test9(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, 30);
		Calendar cc = getMonthCalendar(496,500,c);
		printCalendar("",cc);
	}
	/**
	 * 
	 * @note 
	 * @author zealjiang
	 * @time 2015-6-11 pm 2:09:32
	 * @param pageNumber the page of slip to 
	 * @param startPage  the page of start
	 * @param startCalendar  the start page calendar
	 * @return the calendar of slip to 
	 */
	public  Calendar getMonthCalendar(int pageNumber,int startPage,Calendar startCalendar) {

		int deltaPage = pageNumber - startPage;
		if(deltaPage==0){
			return startCalendar;
		}
		
		int year = startCalendar.get(Calendar.YEAR);
		int month = startCalendar.get(Calendar.MONTH);
		int date = startCalendar.get(Calendar.DATE);
		
		int deltaYear = Math.abs(deltaPage)/12;
		int deltaMonth = Math.abs(deltaPage)%12;
		
		if(deltaPage>0){
			if(month+deltaMonth>11){
				year += (deltaYear + 1);
				month = month + deltaMonth -11;
			}else{
				year += deltaYear;
				month = month + deltaMonth;
			}
		}else if(deltaPage<0){
			if(month-deltaMonth<0){
				year -= (deltaYear + 1);
				month = month - deltaMonth + 12;
			}else{
				year -= deltaYear;
				month = month - deltaMonth;
			}
		} 		
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		int endMonthDay = getMonthMaxDays(c);
		if(date>endMonthDay){
			c.set(Calendar.DATE, endMonthDay);
		}else{
			c.set(Calendar.DATE, date);
		}
		

		return c;
	}
	
	/**
	 * 
	 * @note 返回本月值
	 * @author zealjiang
	 * @time 2015-5-11上午10:59:02
	 * @return
	 */
	public static int getMonthMaxDays(Calendar c){
		int i = c.getActualMaximum(Calendar.DATE);
		return i;	
	}
	
	
	/**
	 * 
	 * @note get next or pre month calendar
	 * @author zealjiang
	 * @time 2015-6-9下午1:06:59
	 * @param mPageNumber
	 * @param mMonthPrePage
	 * @return
	 */
	public  Calendar getNextOrPreMonthCalendar(int mPageNumber,int monthPrePage,Calendar curCalendar) {


		Calendar calendar = (Calendar) curCalendar.clone();
		

		if (mPageNumber > monthPrePage) {

				calendar = setNextViewMonth(calendar);

		} else if (mPageNumber < monthPrePage) {
			
				calendar = setPrevViewMonth(calendar);

		} else {

		}

		return calendar;
	}
	

	// 上一个月
	private  Calendar setPrevViewMonth(Calendar calendar) {
		int iMonthViewCurrentMonth = calendar.get(Calendar.MONTH);
		int iMonthViewCurrentYear = calendar.get(Calendar.YEAR);
		iMonthViewCurrentMonth--;// 当前选择月--

		// 如果当前月为负数的话显示上一年
		if (iMonthViewCurrentMonth == -1) {
			iMonthViewCurrentMonth = 11;
			iMonthViewCurrentYear--;
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1); // 设置日为当月1日
		calendar.set(Calendar.MONTH, iMonthViewCurrentMonth); // 设置月
		calendar.set(Calendar.YEAR, iMonthViewCurrentYear); // 设置年
		return calendar;
	}

	private  Calendar setNextViewMonth(Calendar calendar) {
		int iMonthViewCurrentMonth = calendar.get(Calendar.MONTH);
		int iMonthViewCurrentYear = calendar.get(Calendar.YEAR);
		iMonthViewCurrentMonth++;
		if (iMonthViewCurrentMonth == 12) {
			iMonthViewCurrentMonth = 0;
			iMonthViewCurrentYear++;
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, iMonthViewCurrentMonth);
		calendar.set(Calendar.YEAR, iMonthViewCurrentYear);
		return calendar;
	}
	
	@Test
	public void getAddDateTest(){
		System.out.println(getAddDate("2015-01-30",1,0));
	}
	
	public String getAddDate(String startDate,int month,int day){
		
		Calendar c = getShortCalendar(startDate);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DATE, day);
		
		int year = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH);
		int d = c.get(Calendar.DATE);
		 
		String t = year+"-"+(m+1)+"-"+d;
		return getStringDate(t);
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
	 * 
	 * @note 将"yyyy-MM-dd"格式的日期字符串转成对应的Calendar
	 * @author zealjiang
	 * @time 2015-5-16下午5:03:33
	 * @param strDate
	 * @return
	 */
	public static Calendar getShortCalendar(String strDate) {
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
	 * 
	 * @note compare two 2015-06-26 format time
	 * @author zealjiang
	 * @time 2015-6-26 pm 1:48:47
	 * @param dateA
	 * @param dateB
	 * @return if dateA biger than dateB,return true,else return false;
	 */
	public static boolean isATimeBigEqualThanBTime(String dateA,String dateB){
		
		dateA = dateA.replace("-", "");
		dateB = dateB.replace("-", "");
		
		if(Integer.valueOf(dateA)>=Integer.valueOf(dateB)){			
			return true;
		}else
			return false;
	}
	
	@Test
	public void TestIsATimeBigEqualThanBTime(){
		boolean b = isATimeBigEqualThanBTime("2015-07-26","2015-06-26");
		System.out.println(b);
	}
	
	
	/**
	 * 
	 * @note 在指定的日期startDate上，增加或减少年，月，日后的日期
	 * @author zealjiang
	 * @time 2015-9-8上午11:22:36
	 * @param startDate  the date of changed before  
	 * @param year  before or after years
	 * @param month before or after years
	 * @param day before or after years
	 * @param isBefore if true then return the date of changed before
	 * @return changed date
	 */
	public String getSpecifiedDate(String startDate,int year,int month,int day,boolean isBefore){
		
		Calendar c = getShortCalendar(startDate);
		if(isBefore){
			c.add(Calendar.YEAR, -year);
			c.add(Calendar.MONTH, -month);
			c.add(Calendar.DATE, -day);
		}else{
			c.add(Calendar.YEAR, year);
			c.add(Calendar.MONTH, month);
			c.add(Calendar.DATE, day);
		}

		
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH);
		int d = c.get(Calendar.DATE);
		 
		String t = y+"-"+(m+1)+"-"+d;
	    String formatDate = getStringDate(t);
		
		return formatDate;
	}
	
	@Test
	public void TestGetSpecifiedDate(){
		System.out.println(getSpecifiedDate("2015-01-30",1,12,6,false));
	}
}
