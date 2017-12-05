package com.breaked;

public class IfBreak {

	public static void main(String[] args) {
		int k=5;
		
		while(true){
		if(k>0)	
		loop:
			while(true){
				System.out.println(".ss.");
				if(k>0){
//				System.out.println("1");	
//				System.out.println("2");
					System.out.println(".."+k);
				k--;
				continue;
//				System.out.println("2");
//				break loop;
				}
				break;
			}
		
		System.out.println("..s");
		k--;
		System.out.println(k);
		if(k<0){		
		return;
		}
		}
		
	}
}
