package com.random;

public class MakeRandom {

	public static void main(String[] args) {
		//generateRandom();
		generateRandom3();
	}
	
	/**
	 * �������10��0��1000������
	 */
	public static void generateRandom(){
		
		for(int i=0;i<10;i++){
			int var = (int) (Math.random()*1000);
			System.out.println(var);
		}
	}
	
	/**
	 * �������10��100��999����������λ����
	 */
	public static void generateRandom3(){
		
		for(int i=0;i<10;i++){
			int var = 100+(int) (Math.random()*899);
			System.out.println(var);
		}
	}
}
