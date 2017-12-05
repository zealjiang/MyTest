package com.string;

import org.junit.Test;

public class IsChineseCharacter {

	@Test
	public void checkIsChineseCharacter(){
		
		boolean b1 = isLetter('a');
		
		boolean b2 = isLetter('°²');
		
		System.out.println("b1 :"+b1+"  b2: "+b2);
		System.out.println("°² :"+(int)'°²');
	}
	
	
    public static boolean isLetter(int codePoint) {
        if (('A' <= codePoint && codePoint <= 'Z') || ('a' <= codePoint && codePoint <= 'z')) {
            return true;
        }
        return false;
    }
}
