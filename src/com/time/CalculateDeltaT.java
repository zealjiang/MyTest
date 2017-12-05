package com.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ��������ʱ���
 * 
 * @author zealjiang
 * @time 2016��4��22������5:06:50
 */
public class CalculateDeltaT {

	public static void main(String[] args) {
		CalculateDeltaT cdt = new CalculateDeltaT();
		cdt.method1();
		cdt.method2();
		cdt.method3();
		cdt.toSec();
	}

	public void method1() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse("2016-04-22 13:31:40");
			// Date d2 = df.parse("2016-01-02 11:30:24");
			Date d2 = df.parse("2016-04-22 13:33:24");
			long diff = d2.getTime() - d1.getTime();
			long days = diff / (1000 * 60 * 60 * 24);
			System.out.println("d1.getTime(): " + d1.getTime());
			System.out.println("diff: " + diff);

		} catch (Exception e) {
		}
	}

	public void method2() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date now = df.parse("2016-04-22 13:33:24");
			java.util.Date date = df.parse("2016-04-22 13:31:40");
			long l = now.getTime() - date.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			System.out.println("" + day + "��" + hour + "Сʱ" + min + "��" + s + "��");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void method3() {
		try {
			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date begin = dfs.parse("2004-01-02 11:30:24");
			java.util.Date end = dfs.parse("2004-03-26 13:31:40");
			long between = (end.getTime() - begin.getTime()) / 1000;// ����1000��Ϊ��ת������
			long day1 = between / (24 * 3600);
			long hour1 = between % (24 * 3600) / 3600;
			long minute1 = between % 3600 / 60;
			long second1 = between % 60;
			System.out.println("" + day1 + "��" + hour1 + "Сʱ" + minute1 + "��" + second1 + "��");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����ʱ���Ϊ������
	 * @author zealjiang
	 * @date 2016��5��11�� ����2:32:56
	 *
	 */
	public void toSec() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse("2016-04-22 13:31:40");
			// Date d2 = df.parse("2016-01-02 11:30:24");
			Date d2 = df.parse("2016-04-22 13:33:24");
			long diff = d2.getTime() - d1.getTime();
			long days = diff / (1000 * 60 * 60 * 24);
			System.out.println("diff_sec : " + diff/1000);

		} catch (Exception e) {
		}
	}
	
	/**
	 * ��������ʱ���Ϊ������
	 * yyyy-MM-dd HH:mm:ss �����ʽ���ַ���ʱ��
	 * @author zealjiang
	 * @date 2016��5��11�� ����2:38:40
	 * @param startT ��ʼʱ��
	 * @param endT ����ʱ��
	 * @return ����ʱ���������룬ʱ���ʽ����ȷ����-1
	 */
	public static long deltaTime(String startT,String endT) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date ds = df.parse(startT);
			Date de = df.parse(endT);
			long diff = de.getTime() - ds.getTime();
			System.out.println("diff_sec : " + diff/1000);
			return diff/1000;
		} catch (Exception e) {
			return -1;
		}
	}
}
