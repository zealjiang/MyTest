package com.time;

public class GetSystemTime {
	public static void main(String[] args) {
		String t = ((Long)System.currentTimeMillis()).toString();
		System.out.println(t);
		Character c = t.charAt(t.length()-1);
		System.out.println(c);
		int p = Integer.valueOf(c.toString());
		System.out.println(p);
		int radix = 9/2;
		System.out.println(p/radix);
	}
}
