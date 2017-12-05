package com.decimalformat;

import java.text.DecimalFormat;

public class Decimalformat {

	public static void main(String[] args) {
		
		float x1 = 555.362f;
		float x2 = 645724/(1024*1024.0f);
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.println("ss"+df.format(x2));
		System.out.println("===============");
		
		//四舍五入
		double i=2, j=2.1, k=2.5, m=2.9;
		System.out.println("舍掉小数取整:Math.floor(2)=" + (int)Math.rint(i));
		System.out.println("舍掉小数取整:Math.floor(2.1)=" + (int)Math.rint(j));
		System.out.println("舍掉小数取整:Math.floor(2.5)=" + (int)Math.rint(k));
		System.out.println("舍掉小数取整:Math.floor(2.9)=" + (int)Math.rint(m));
		
		System.out.println((int)Math.rint(Float.valueOf("2.67mil Fe".substring(0,4))*25.4f)+"");
	}
}
