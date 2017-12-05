package com.breaked;

public class Continue {
	public static void main(String[] args) {
		int k=5;

		while(true){
		if(k>0)	
			System.out.println(k);
			while(k>0){
				System.out.println(".ss."+k);
				k--;
				continue;			
			}

		if(k==0)
			break;
		}
		System.out.println("break out");
		
//		int k=3;
//		while(k>0){
//		if(k<2)
//			System.out.println("..."+k);
//		System.out.println("========================");
//		System.out.println("..."+k);
//		k--;
//		}
	}
}
