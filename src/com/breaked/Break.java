package com.breaked;

public class Break {
	public static void main(String[] args) {
		int k=5;
		loop1:
		while(true){
			System.out.println(".....loop1");
			while(true){
				System.out.println("..."+k);
				k--;
				if(k==1)
					break;
				if(k==0)
					break loop1;
			}
		}
		System.out.println("....out");
	}
}
