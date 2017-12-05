package com.whiletest;

public class SkipOutWhile {

	public static void main(String[] args) {
		SkipOutWhile sow = new SkipOutWhile();
		sow.skipOutWhile1();
		sow.skipOutWhile2();
	}
	
	private void skipOutWhile1(){
		int j=0;
		boolean boo = true;
		while(boo){
			for (int i = 0; i < 20; i++) {
				if(i%2==0){
					j++;
				}
				if(i==15){					
					boo = false;
					break;
				}
			}
		}
		System.out.println("skipOutWhile1 j: "+j);
	}
	
	private int skipOutWhile2(){
		int j=0;
		boolean boo = true;
		while(boo){
			for (int i = 0; i < 20; i++) {
				if(i%2==0){
					j++;
				}
				if(i==15){	
					boo =false;
					System.out.println("skipOutWhile2 j: "+j);
					return j;
				}
			}
		}
		System.out.println("skipOutWhile2 j: "+j);
		return j;
	}
}
