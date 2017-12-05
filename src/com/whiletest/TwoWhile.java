package com.whiletest;


public class TwoWhile {

	public static void main(String[] args) {
		whilebreak();
	}
	public static void whilebreak(){
		int i = 0;
		while(true){
			// TODO Auto-generated method stub
			
			while(true){						
				if(i==10)break;
				System.out.println("i:"+i);
				i++;
			}
			System.out.println("out while");
			break;
		}
		
	}
}
