package com.superthis;

public class Example3 {

	public static void main(String[] args) {
		Daughter girl = new Daughter();
		System.out.println(girl.dance());
	}
}

class Mother{
	String dance(){return "�һ�����";}
}

class Daughter extends Mother{
	String dance(){return "����С�赸��Ա";}
}