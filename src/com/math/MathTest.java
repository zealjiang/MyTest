package com.math;

import org.junit.Test;

public class MathTest {

	@Test
	public void divide(){
		if(10%3==0){
			System.out.println(10/3);
		}else{
			System.out.println(10/3+1);
		}
		
		System.out.println("a "+255f/144);
		
		System.out.println("float/float "+10.0f%3.0f);
		System.out.println("float/float "+(int)(10.0f/3.0f));
	}
	
	@Test
	public void compare(){
		int width = 1200;
		int result = width *4/3;
		int result2 = (int)(width *(4/3.0));
		
		System.out.println("result :"+result);
		System.out.println("result2 :"+result2);
	}
}
