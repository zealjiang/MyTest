package com.thread;


public class myWhile {

	public static void main(String[] args) {
		whileTest();
	}
	
	public static void whileTest(){
		int k=0;
		//���Ѱ�����ɹ�������Ѱ��
		while(true){
			
			if(k==10){
				System.out.println("Ѱ���ɹ���ss");
				break;
			}
			System.out.println("����ѭ��");
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
