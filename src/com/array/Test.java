package com.array;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		double d = 63d;
		double m = 256/(d/8);
		System.out.println("m: "+m);
		
		byte mCommand = 0x17;
		if(mCommand==0x17){
			System.out.println(mCommand+"=="+0x17);
		}
	}
	
	public void testPacketQueue(){
		PacketQueue mPacketQueue = PacketQueue.getInstance();
		mPacketQueue.printQueue2();
	}
	

}
