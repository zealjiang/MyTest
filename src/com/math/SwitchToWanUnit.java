package com.math;

import java.text.DecimalFormat;

import org.junit.Test;

public class SwitchToWanUnit {

	@Test
	public void decimalFormat2() {
		String value = "301526658.42";
		double fValue = Double.valueOf(value);
		
		if(fValue>=100000000){//1��
			//System.out.println(Math.round(fValue/10000d));
			
			DecimalFormat df = new DecimalFormat("#.00");   
	        System.out.println(df.format(Double.parseDouble(fValue/10000d+""))); 
		}else if(fValue<=-100000000){//1��
			
			DecimalFormat df = new DecimalFormat("#.00");   
			System.out.println(df.format(Double.parseDouble(fValue/10000d+""))+"��"); 
		}
		
 
	}
	
	
	@Test
	public void UnitToW() {
		String value = "-301526668.42";
		double fValue = Double.valueOf(value);
		
		if(fValue<=-100000000){//1��
			//System.out.println(Math.round(fValue/10000d));
			
			DecimalFormat df = new DecimalFormat("#.00");   
	        System.out.println(df.format(Double.parseDouble(fValue/10000d+""))); 
		}
		
 
	}
	
	@Test
	public void checkW(){
		String moneyJ = "1334234.23��";
		if(moneyJ.contains("��")){
			moneyJ = moneyJ.replace("��", "");
			double dmoney = Double.parseDouble(moneyJ)*10000;
			DecimalFormat df = new DecimalFormat("#.00");   
	        moneyJ = df.format(dmoney); 
		}
		System.out.println(moneyJ);
	}
}
