package com.thread;

import java.applet.Applet;
public class Example8_5 extends Applet{

	static SalesLady salesLady = new SalesLady(12, 0, 0);
	
	public void start(){
		int moneies[] = {10,10,5,10,5,10,5,5,10,5,10,5,5,10,5};
		Thread[] aThreadArray = new Thread[20];
		System.out.println("���ڿ�ʼ��Ʊ");
		for(int i=0;i<moneies.length;i++){
			aThreadArray[i] = new Thread(new CustomerClass(i+1, moneies[i]));
			aThreadArray[i].start();
		}
		WhileLoop:
			while (true) {
				for(int i=0;i<moneies.length;i++){
					if(aThreadArray[i].isAlive())
						continue WhileLoop;				
				}
				break;				
			}
		System.out.println("��Ʊ������");
	}
}

class SalesLady{
	int memontoes,five,ten;
	public synchronized String ruleForSale(int num,int money){
		//System.out.println(""+num+"�Ź˿����ڽ��ף�");
		String s = null;
		if(memontoes == 0)
			return "�Բ��������꣡";
		if(money == 5){
			memontoes--;
			five++;
			s = "����һ������Ʒ,���Ǯ���á�";
		}else if(money == 10){
			while (five < 1) {
				try{
					System.out.println(""+num+"�Ź˿���10ԪǮ��Ʊ�������ȴ���");
					wait();
				}catch (Exception e) {
					// TODO: handle exception
				}			
			}
			memontoes--;
			five -=1;
			ten++;
			s = "����һ������Ʒ�������ʮԪ��������Ԫ��";
		}
		
		notify();
		//System.out.println(""+num+"�Ź˿ͽ��׽�����");
		return s;
	}
	
	public SalesLady(int m,int f,int t) {
		// TODO Auto-generated constructor stub
		memontoes = m;
		five = f;
		ten = t;
	}
}

class CustomerClass implements Runnable{

	int num,money;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("����"+num+"�Ź˿�");
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("����"+num+"�Ź˿ͣ���"+money+"Ԫ������Ʒ���ۻ�Ա˵��"+
				Example8_5.salesLady.ruleForSale(num, money));
	}
	
	public CustomerClass(int n,int m) {
		// TODO Auto-generated constructor stub
		num = n;
		money = m;
	}
	
}