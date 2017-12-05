package com.character;

public class IsWhiteSpace {

	public static void main(String[] args) {
		char a =' ';
		if(Character.isWhitespace(a)){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		
		String t = "abc";
		System.out.println(t.length());
	}
}
