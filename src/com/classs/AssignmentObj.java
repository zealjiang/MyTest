package com.classs;

import org.junit.Test;

public class AssignmentObj {

	public static Aa a;
	private Aa b;
	
	@Test
	public void main() {
		Aa a = new Aa();
		a.setStatus(1);
		a.setName("a");
		
		b = a;
		System.out.println("b.status: "+b.getStatus());
	}
}

class Aa{
	private int status;
	private String name;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

