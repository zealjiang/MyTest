package com.math;

import org.junit.Test;

import com.byteArray.intToByteArray;

public class CaculateFullNum {

	public static void main(String[] args) {
		int s = 0;
		for(int i = 2;i<1000;i++){	
			s=0;
			for(int j=2;j<i/2;j++){

				if(i%j==0){
					if(j<(i/j)){
						s =s + j +(i/j);
					}else if(j==(i/j)){
						s =s + j;
					}else{
						break;
					}
					 
					 //System.out.println("i��"+i+"  j-->"+j);
					 //System.out.println("s��"+s);
					 if(s==i){
						 System.out.println("��ȫ����"+i);				 
					 }
						 
					
				}
				
			}
		}
	}
	
	@Test
	public void round(){
		System.out.println(Math.round(1.6));
	}
}
