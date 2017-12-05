package com.thread;

import java.applet.Applet;
public class Example8_5 extends Applet{

	static SalesLady salesLady = new SalesLady(12, 0, 0);
	
	public void start(){
		int moneies[] = {10,10,5,10,5,10,5,5,10,5,10,5,5,10,5};
		Thread[] aThreadArray = new Thread[20];
		System.out.println("现在开始购票");
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
		System.out.println("购票结束！");
	}
}

class SalesLady{
	int memontoes,five,ten;
	public synchronized String ruleForSale(int num,int money){
		//System.out.println(""+num+"号顾客正在交易！");
		String s = null;
		if(memontoes == 0)
			return "对不起，已售完！";
		if(money == 5){
			memontoes--;
			five++;
			s = "给你一个纪念品,你的钱正好。";
		}else if(money == 10){
			while (five < 1) {
				try{
					System.out.println(""+num+"号顾客用10元钱购票，发生等待！");
					wait();
				}catch (Exception e) {
					// TODO: handle exception
				}			
			}
			memontoes--;
			five -=1;
			ten++;
			s = "给你一个纪念品，你给了十元，找你五元。";
		}
		
		notify();
		//System.out.println(""+num+"号顾客交易结束！");
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
		System.out.println("我是"+num+"号顾客");
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("我是"+num+"号顾客，用"+money+"元购纪念品，售货员说："+
				Example8_5.salesLady.ruleForSale(num, money));
	}
	
	public CustomerClass(int n,int m) {
		// TODO Auto-generated constructor stub
		num = n;
		money = m;
	}
	
}