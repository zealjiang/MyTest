package com.math;

import java.math.BigDecimal;

/**
 * 计算小数部分
 * @author zealjiang
 * @time 2016年5月6日上午10:14:08
 */
public class CalDecimal {

	public static void main(String[] args) {
		CalDecimal c = new CalDecimal();
		c.calDecimal();
		
		//计算一个浮点型数的小数部分
		System.out.println(""+c.getDecimal(5.633f));
		System.out.println("0%18: "+0%18);
	}
	private void calDecimal(){
		float a = 2.223f;
		float b = a - (int)a;
		
		BigDecimal b1 = new BigDecimal(Float.toString(a));
	    BigDecimal b2 = new BigDecimal(Float.toString((int)a));
	    float ss = b1.subtract(b2).floatValue(); 

		System.out.println("CalDecimal "+a+" 的小数部分是："+b);
		System.out.println("CalDecimal "+a+" 的小数部分是："+ss);
	}
	
	/**
	 * 计算一个浮点型数的小数部分
	 * @author zealjiang
	 * @date 2016年5月6日 上午10:41:38
	 * @param floatNum
	 * @return
	 *
	 */
	private float getDecimal(float floatNum){
	
		BigDecimal bFloat = new BigDecimal(Float.toString(floatNum));
	    BigDecimal bInt = new BigDecimal(Float.toString((int)floatNum));
	    float decimal = bFloat.subtract(bInt).floatValue(); 
	    return decimal;

	}
}
