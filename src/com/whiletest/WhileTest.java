package com.whiletest;

import org.junit.Test;

public class WhileTest {
	
	static int k=100;
	public static void main(String[] args) {
		
/*		//≤‚ ‘“ª
		while(k>3){
			System.out.println(k);
			k--;
		}*/
		
/*		//≤‚ ‘∂˛
		myBreak(k);*/
		
		//≤‚ ‘»˝
		myBreakIf(k);
	}
	
	public static void myBreak(int k){
		
		while(true){
			
			while(true){
				
				System.out.println("k :"+k);
				break;
/*				if(k==0)
					break;
				k--;*/
			}
			System.out.println("one");
			break;
		}
		
		
	}
	
	public static void myBreakIf(int k){
		
		while(true){
			
			while(true){
				if(k==0){
					System.out.println("k :"+k);
					break;
				}
				System.out.println("k :"+k);
				k--;
/*				if(k==0)
					break;
				k--;*/
			}
			System.out.println("one");
			break;
		}	
	}
	
	@Test
	public  void continues(){
		int i = 0;
		while(true){
				i++;
				if(i==10){
					i=0;
					continue;
				}
					
				System.out.println("========="+i);

			}
	}
}
