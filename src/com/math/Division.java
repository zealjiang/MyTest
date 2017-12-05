package com.math;

import java.text.DecimalFormat;

import org.junit.Test;

public class Division {

	@Test
	public void test1(){
		
		double heightRate = 801.0012/800d;
		System.out.println(heightRate);
		
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.println(df.format(heightRate));
	}
	
	
	/**
	 * 单位转换
	 * @param size  单位为bit的大小
	 * @return 返回kb或Mb为单位的值
	 */
	@Test
	public void uniteConversion(){
		int size = 1;
		int m = size/(1024*1024);
		String unite = "";
		double formatSize;
		if(m<1){
			//size小于1M
			formatSize = size/1024d;
			unite = "kb";
			System.out.println("formatSize:"+formatSize);
		}else{
			//size大于1M
			formatSize = size/(1024*1024d);
			unite = "Mb";
			System.out.println("formatSize:"+formatSize);
		}

		DecimalFormat df = new DecimalFormat("#0.0");
		String value = df.format(formatSize);
		double remainder = Double.valueOf(value)%1;
		if(remainder==0){
			System.out.println((int)formatSize+unite);
		}else{
			System.out.println(df.format(formatSize)+unite);
		}

	}

}
