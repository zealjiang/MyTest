package com.character;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class CodeChange {

	@Test
	public void unicodeToUtf8(){
		String s = "\u2025";//\u2026
		try {
			String utf8 = new String(s.getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("s--->"+s);
	}
}
