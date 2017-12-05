package com.random;

public class MakeRandom {

	public static void main(String[] args) {
		//generateRandom();
		generateRandom3();
	}
	
	/**
	 * 随机产生10个0到1000的整数
	 */
	public static void generateRandom(){
		
		for(int i=0;i<10;i++){
			int var = (int) (Math.random()*1000);
			System.out.println(var);
		}
	}
	
	/**
	 * 随机产生10个100到999的整数（三位数）
	 */
	public static void generateRandom3(){
		
		for(int i=0;i<10;i++){
			int var = 100+(int) (Math.random()*899);
			System.out.println(var);
		}
	}
}
