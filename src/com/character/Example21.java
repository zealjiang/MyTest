package com.character;

import org.junit.Test;


public class Example21 {

	public static void main(String[] args) {
		char chinaword='��',japanword = '��';
		int p1 =36328,p2=12358;
		System.out.println("����\'��\'��unicode���е�˳��λ�ã�"+(int)chinaword);
		System.out.println("����\'��\'��unicode���е�˳��λ�ã�"+(int)japanword);
		System.out.println("unicode���е�36328���ַ��ǣ�"+(char)p1);
		System.out.println("unicode���е�12358���ַ��ǣ�"+(char)p2);
		
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
 