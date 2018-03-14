package com.classs;

public class StaticTest {

	private static boolean aa;
	private boolean bb;
	
	public static void main(String[] args) {
		StaticTest st1 = new StaticTest();
		st1.aa = true;
		st1.bb = true;
		
		StaticTest st2 = new StaticTest();
		st2.aa = false;
		st2.bb = false;
		
		System.out.println("st1.aa: "+st1.aa+" st2.aa: "+st2.aa);
		System.out.println("st1.bb: "+st1.bb+" st2.bb: "+st2.bb);
	}
}
