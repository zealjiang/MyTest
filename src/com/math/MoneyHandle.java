package com.math;

import java.text.DecimalFormat;

import org.junit.Test;

public class MoneyHandle {

	@Test
	public void handleMoney(){
		double i =  990000.00 ;
		double b;
		if(i<=100000){
			System.out.println("i1: "+i);
			if(i%1.00==0){
				System.out.println("i2: "+(int)i);
			}
		}else{
			b = i/100000d;
			System.out.println("i3: "+b);
			String out = new DecimalFormat("###,###,##0.00").format(b);
			System.out.println("i4: "+out);
		}
	}
	
	
	/**
	 * �����Ǯ�������10��Ԫ������ɵ�λΪ��Ԫ
	 * @author zealjiang
	 * @time 2015-3-31����1:22:42
	 * @param yuanMoney
	 * @return
	 */
	@Test
	public void yuanToWanYuan(){
		String yuanMoney = "100000";
		double yuan = 0;
		try {
			yuan = Double.valueOf(yuanMoney);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			System.out.println(yuanMoney);
		}
		 
		double b;
		if(yuan<100000){
			String out = new DecimalFormat("###,###,##0").format(yuan);
			System.out.println(out+"Ԫ");
		}else if(yuan<100000000){
			b = yuan/10000d;
			String out = new DecimalFormat("###,###,##0").format(b);
			System.out.println(out+"��Ԫ");
		}else{
			b = yuan/100000000d;
			String out = new DecimalFormat("###,###,##0").format(b);
			System.out.println(out+"��Ԫ");
		}

	}
	
	/**
	 * ȥ��С������0
	 * @author zealjiang
	 * @time 2015-4-1����4:46:35
	 */
	@Test
	public void trimPointZero(){
		String a = "13.0000";//"13.0100"
		if(a.contains(".")){
			int j = 0;
			char[] cArray = a.toCharArray();
			for (int i = cArray.length-1; i >=0 ; i--) {
				if(cArray[i]=='0'){
					j++;
					continue;
				}
				if(cArray[i]=='.'){
					j++;
					break;
				}
				break;
			}
			System.out.println(""+a.substring(0, a.length()-j));
		}else{
			System.out.println(""+a);
		}
		
	}
	
	@Test
	public void test(){
		double b = 990000/10000d;
		String out = new DecimalFormat("###,###,##0").format(b);
		System.out.println(out+"��Ԫ");
	}
}
