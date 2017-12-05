package com.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * @author zj
 * @time 2013-11-19����4:19:53
 */
public class DateUtils {
	
	public static void main(String[] args) {
		
		System.out.println("������" +getCurrentMonthLastDay()+ "��");
		
		DateUtils du = new DateUtils();

		System.out.println("���������ǣ�" + du.getNowDate());

		System.out.println("������" + du.getDays(du.getYear(), du.getMonth())
				+ "��");

		System.out.println("������" + du.getSundays(du.getYear(), du.getMonth())
				+ "��������");
	}
	
	
	/**
	 * 
	 * ȡ�õ�������
	 * 
	 * */

	public static int getCurrentMonthLastDay()

	{

		Calendar a = Calendar.getInstance();

		a.set(Calendar.DATE, 1);// ����������Ϊ���µ�һ��
		//System.out.println(a.getTime());
		a.roll(Calendar.DATE, -1);// ���ڻع�һ�죬Ҳ�������һ��
		//System.out.println(a.getTime());
		int maxDate = a.get(Calendar.DATE);
		
		return maxDate;

	}

	/**
	 * 
	 * �õ�ָ���µ�����
	 * 
	 * */

	public static int getMonthLastDay(int year, int month)

	{

		Calendar a = Calendar.getInstance();

		a.set(Calendar.YEAR, year);

		a.set(Calendar.MONTH, month - 1);

		a.set(Calendar.DATE, 1);// ����������Ϊ���µ�һ��

		a.roll(Calendar.DATE, -1);// ���ڻع�һ�죬Ҳ�������һ��

		int maxDate = a.get(Calendar.DATE);

		return maxDate;

	}

	

	// ���ص�ǰ������

	String getNowDate() {

		Date date = new Date();

		String nowDate = new SimpleDateFormat("yyyy��MM��dd��").format(date);

		return nowDate;

	}

	// ���ص�ǰ���

	int getYear()

	{

		Date date = new Date();

		String year = new SimpleDateFormat("yyyy").format(date);

		return Integer.parseInt(year);

	}

	// ���ص�ǰ�·�

	int getMonth()

	{

		Date date = new Date();

		String month = new SimpleDateFormat("MM").format(date);

		return Integer.parseInt(month);

	}

	// �ж�����

	boolean isLeap(int year)

	{

		if (((year % 100 == 0) && year % 400 == 0)
				|| ((year % 100 != 0) && year % 4 == 0))

			return true;

		else

			return false;

	}

	// ���ص�������

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

	// ���ص�����������

	int getSundays(int year, int month)

	{

		int sundays = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");

		Calendar setDate = Calendar.getInstance();

		// �ӵ�һ�쿪ʼ

		int day;

		for (day = 1; day <= getDays(year, month); day++)

		{

			setDate.set(Calendar.DATE, day);

			String str = sdf.format(setDate.getTime());

			if (str.equals("������"))

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
	 * ��ȡ��ǰʱ���ʱ���
	 */
	public static String getYearMonthDate(Date date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy��MM��dd��");
		String sDate = sf.format(date);

		return sDate;
	}
	
	/**
	 * 
	 * @note ��"yyyy-MM-dd"��ʽ�������ַ���ת�ɶ�Ӧ��Calendar
	 * @author zealjiang
	 * @time 2015-5-16����5:03:33
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
	 * DateתCalendar
	 * @author zealjiang
	 * @date 2016��4��27�� ����5:39:17
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
	 * CalendarתDate
	 * @author zealjiang
	 * @date 2016��4��27�� ����5:40:31
	 * @return
	 *
	 */
	public Date CalendarToDate(){
		Calendar cal=Calendar.getInstance();
		Date date=cal.getTime();
		return date;
	}
}
