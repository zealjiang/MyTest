package com.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 计算两个时间差
 * 
 * @author zealjiang
 * @time 2016年4月22日下午5:06:50
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
			System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void method3() {
		try {
			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date begin = dfs.parse("2004-01-02 11:30:24");
			java.util.Date end = dfs.parse("2004-03-26 13:31:40");
			long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
			long day1 = between / (24 * 3600);
			long hour1 = between % (24 * 3600) / 3600;
			long minute1 = between % 3600 / 60;
			long second1 = between % 60;
			System.out.println("" + day1 + "天" + hour1 + "小时" + minute1 + "分" + second1 + "秒");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 两个时间差为多少秒
	 * @author zealjiang
	 * @date 2016年5月11日 下午2:32:56
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
	 * 计算两个时间差为多少秒
	 * yyyy-MM-dd HH:mm:ss 这个格式的字符串时间
	 * @author zealjiang
	 * @date 2016年5月11日 下午2:38:40
	 * @param startT 开始时间
	 * @param endT 结束时间
	 * @return 两个时间相差多少秒，时间格式不正确返回-1
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
