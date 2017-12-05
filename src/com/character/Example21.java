package com.character;

import org.junit.Test;


public class Example21 {

	public static void main(String[] args) {
		char chinaword='你',japanword = 'ゃ';
		int p1 =36328,p2=12358;
		System.out.println("汉字\'你\'在unicode表中的顺序位置："+(int)chinaword);
		System.out.println("日语\'ゃ\'在unicode表中的顺序位置："+(int)japanword);
		System.out.println("unicode表中第36328的字符是："+(char)p1);
		System.out.println("unicode表中第12358的字符是："+(char)p2);
		
		int x= (int)23.89;
		long y = (long)34.98;
		System.out.println("x:"+x+"  y:"+y);
	}
	
	@Test
	public void minus(){
		int i = '9'-'0';
		System.out.println("i:"+i);
	}
}
 