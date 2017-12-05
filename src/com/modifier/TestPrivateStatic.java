package com.modifier;

public class TestPrivateStatic {

	public static void main(String[] args) {
		PrivateStaticTest p = new PrivateStaticTest();
		p.setN(10);
		System.out.println(p.getN());
		p = null;
		System.out.println(new PrivateStaticTest().getN());
	}
}
