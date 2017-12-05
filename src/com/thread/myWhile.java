package com.thread;


public class myWhile {

	public static void main(String[] args) {
		whileTest();
	}
	
	public static void whileTest(){
		int k=0;
		//如果寻卡不成功，重新寻卡
		while(true){
			
			if(k==10){
				System.out.println("寻卡成功了ss");
				break;
			}
			System.out.println("我在循环");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			k++;
		}
	}
}
