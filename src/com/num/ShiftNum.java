package com.num;


public class ShiftNum {

	public static void main(String[] args) {
		shifLeft(16, 3);
		
		
	}
	
	public static int shifLeft(int num,int shift){
		System.out.println("source num:"+num);
		System.out.println("shift  num:"+ (num << shift) + "");
		return num << shift;
	} 
}
