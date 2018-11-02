package com.classs;

public class StaticTest {

	private static boolean aa;
	private boolean bb;
	private static String cc;
	public static String dd;
	
	public static void main(String[] args) {
		StaticTest st1 = new StaticTest();
		st1.aa = true;
		st1.bb = true;
		
		StaticTest st2 = new StaticTest();
		st2.aa = false;
		st2.bb = false;
		
		System.out.println("st1.aa: "+st1.aa+" st2.aa: "+st2.aa);
		System.out.println("st1.bb: "+st1.bb+" st2.bb: "+st2.bb);
		
		cc = "√–“ªª·";
		N n = new N();
		n.setCc("√––—¡À");
		System.out.println(": "+cc+" n.cc: "+n.getCc());
		
		dd = "StaticTest  dd";
		n.setDd("N dd");
		System.out.println(": "+dd+" n.dd: "+n.getDd());
	}
}

class N {
	private static String cc;
	public static String dd;

	public static String getCc() {
		return cc;
	}

	public static void setCc(String cc) {
		N.cc = cc;
	}

	public static String getDd() {
		return dd;
	}

	public static void setDd(String dd) {
		N.dd = dd;
	}
	
	
}
