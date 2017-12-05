package com.character;

public class TypyConvert {
	public static void main(String[] args) {
		
		int c = 2200;
		long d = 8000;
		float f;
		double g =123456789.123456789;
		c = (int) d;
		f = (float) g;//导致精度的损失
		
		System.out.println("c:" +c);
		System.out.println("d:" +d);
		System.out.println("f:" +f);
		System.out.println("g:" +g);
		
		System.out.println(Integer.valueOf('0'));
	}
}
