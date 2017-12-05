package com.thread;


public class Example8_4 {
	
	public static void main(String[] args) {
		MyResourceClass mrc = new MyResourceClass();
		Thread[] aThreadArray = new Thread[20];
		System.out.println("\t刚开始的值是："+mrc.getInfo());
		System.out.println("\t预期的正确结果是："+20*1000*50);
		System.out.println("\t多个线程正在工作，请稍等！");
		for(int i=0;i<20;i++){
			aThreadArray[i] = new Thread(new MyMultiThreadClass(mrc));
			aThreadArray[i].start();
		}
		
		WhileLoop:
			while (true) {
				for(int i=0;i<20;i++){
					if(aThreadArray[i].isAlive())
						continue WhileLoop;				
				};
				break;
			}
		System.out.println("\t最后的结果是："+mrc.getInfo());
	}
}

class MyMultiThreadClass implements Runnable{
	MyResourceClass UseInteger;

	public MyMultiThreadClass(MyResourceClass mrc) {
		// TODO Auto-generated constructor stub
		UseInteger = mrc;
	}
/*	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i,LocalInteger;
		for(i = 0;i<1000;i++){
			LocalInteger = UseInteger.getInfo();
			LocalInteger += 50;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			UseInteger.putInfo(LocalInteger);
		}
	}*/
	
	//下面是线程共享资源的代码
	public void run(){
		for(int i = 0;i<1000;i++){
			UseInteger.sumResource(50);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

class MyResourceClass{
	int IntegerResource;
	public MyResourceClass() {
		// TODO Auto-generated constructor stub
		IntegerResource = 0;
	}
	
	public int getInfo(){
		return IntegerResource;
	}
	
	public void putInfo(int info) {
		IntegerResource = info;
	}
	
	synchronized void sumResource(int q){
		int LocalInteger;
		LocalInteger = getInfo();
		LocalInteger += q;
		putInfo(LocalInteger);
	}
}