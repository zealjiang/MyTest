package com.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ʱ�����
 * @author zealjiang
 * @time 2016��4��27������7:17:03
 */
public class TimeAdding {

	public static void main(String[] args) {
		TimeAdding ta = new TimeAdding();
		ta.addSec(null, 70);
		ta.addStringDateSec(ParseTime.dateToString(new Date()), 20);
	}
	
	/**
	 * ָ�������ڼ���sec����������
	 * @author zealjiang
	 * @date 2016��4��27�� ����6:23:46
	 * @param date
	 * @param sec
	 * @return
	 *
	 */
	public Date addSec(Date date,int sec){
		if(date==null){
			date = new Date();
		}
		// format������������ָ����ʱ���ʽ��ʽ��ʱ���
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // ����ĸ�ʽ�����Լ�����
		// format()������������ʽ��ʱ��ķ���
		String times = from.format(date);
		//ԭʼʱ��
		System.out.println("ԭʼʱ�䣺"+times);
		Calendar cal = DateToCalendar(date);
		cal.add(Calendar.SECOND, sec);//����ǰʱ���sec����ʱ��
		Date dateNew = CalendarToDate(cal);
		times=from.format(dateNew);
		System.out.println("���֮���ʱ�䣺"+times);
		return dateNew;
	}
	
	/**
	 * DateתCalendar
	 * @author zealjiang
	 * @date 2016��4��27�� ����5:39:17
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
	 * CalendarתDate
	 * @author zealjiang
	 * @date 2016��4��27�� ����5:40:31
	 * @return
	 *
	 */
	private Date CalendarToDate(Calendar cal){
		Date date=cal.getTime();
		return date;
	}
	
	/**
	 * ���ؽ�String���͵�ʱ�����sec�����µ�String��ʽ��ʱ��
	 * @author zealjiang
	 * @date 2016��4��28�� ����9:32:43
	 * @param sdate string���͵�ʱ���ʽ yyyy-MM-dd HH:mm:ss ��ʽ
	 * @param sec ��
	 * @return ���ؽ�String���͵�ʱ�����sec�����µ�String��ʽ��ʱ��
	 *
	 */
	public String addStringDateSec(String sdate,int sec){
		Date date = ParseTime.parseStringTime2Date(sdate);
		// format������������ָ����ʱ���ʽ��ʽ��ʱ���
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // ����ĸ�ʽ�����Լ�����
		// format()������������ʽ��ʱ��ķ���
		String times = from.format(date);
		//ԭʼʱ��
		System.out.println("ԭʼʱ�䣺"+times);
		Calendar cal = DateToCalendar(date);
		cal.add(Calendar.SECOND, sec);//����ǰʱ���sec����ʱ��
		Date dateNew = CalendarToDate(cal);
		times=from.format(dateNew);
		System.out.println("���֮���ʱ�䣺"+times);
		return times;
	}
}
