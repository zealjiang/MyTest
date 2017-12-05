package com.charlength;

import org.junit.Test;

public class CharLength {

	public static void main(String[] args) {
		charlength();
		 stringlength();
	}
	
	public static void charlength(){
		char a = 'a';
		
		System.out.println("a.length :"+a);
	}
	
	public static void stringlength(){
		String aa = "ass";
		System.out.println("aa.length :"+aa.getBytes().length);
	}
	
	@Test
	public  void charToNum(){
		String aa = "012";
		System.out.println("aa.length :"+aa.charAt(1)+"  "+((aa.charAt(1)-'0'>=0&&aa.charAt(1)-48<10)?0:-1));
		System.out.println(Integer.valueOf('1'-'0'));
		System.out.println(aa.charAt(1)-'0');
		System.out.println(aa.charAt(1)-0);
	}
}
