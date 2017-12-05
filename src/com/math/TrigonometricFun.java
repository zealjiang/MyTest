package com.math;

import org.junit.Test;

public class TrigonometricFun {

	/**
	 * Math.sqrt()开平方根
	 * Math.atan2(double1, double2)表示tan(double2/double1)所对角的弧度值
	 * @author zealjiang
	 * @time 2015-4-10下午4:38:44
	 */
	
	@Test
	public void mAnt2(){
		double arc1 = Math.atan2(1, 1);
		System.out.println(Math.atan2(1, 1));
		System.out.println(arc1/Math.PI*180);
		
		double arc2 = Math.atan2(1, Math.sqrt(3));
		System.out.println(arc2);
		System.out.println(arc2/Math.PI*180);
	}
}
