package com.classs;

public class GetSimpleName {

	public static void main(String[] args) {
		GetSimpleName gen = new GetSimpleName();
		gen.getSimpleName(GetSimpleName.class);
	}
	
	private void getSimpleName(Class cls){
		
		String name = cls.getSimpleName();
		System.out.println("name :"+name);
	}
}
