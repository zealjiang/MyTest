package com.random;

import java.util.Random;

public class RandomSetSeed {

	public static void main(String[] args) {
		RandomSetSeed rss = new RandomSetSeed();
		//rss.setSeed();
		
		//���Զ�
		for(int i=0;i<100;i++){
			rss.getRandom(6);
		}
		
	}
	
	private void setSeed(){
		long ss = (new Random(System.currentTimeMillis())).nextLong();
		System.out.println("ss :"+ss);
		long ss2 = (new Random()).nextLong();
		System.out.println("ss2 :"+ss2);
		int ss3 = (new Random()).nextInt();
		System.out.println("ss3 :"+ss3);
	}
	
	/**
	 * ��ȡһ���綨��0��big���������
	 * @param big
	 * @return
	 */
	private int getRandom(int big){
	
		Random r = new Random();
		System.out.println("r:"+r.nextInt(big));
		return r.nextInt(big);

	}
}
