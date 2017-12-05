package com.string;

import java.io.UnsupportedEncodingException;

public class Encode {

	public static void main(String[] args) {
		
		
		System.out.println("Java\u5e73\u53f0\u5b9e\u73b0\u56fe\u7247\u7b49\u6bd4\u4f8b\u7f29\u7565\u56fe\u751f\u6210");
		
		Encode encode = new Encode();
		String s =encode.chineseToUnicode("Java平台实现图片等比例缩略图生成");
		System.out.println(s);
	}
	
	/**
     * 中文转unicode
     * @param  str
     * @return unicode
     */
	public String  chineseToUnicode(String str){
        String result = "";
        for (int i = 0; i < str.length(); i++)
        {
            int chr1 = (char) str.charAt(i);
            if(isLetter(chr1)==true){
            	result  += (char)chr1;
            }else{
            	result  += "\\u" + Integer.toHexString(chr1);            
            }
        }
        return result;
    }
	
    public static boolean isLetter(int codePoint) {
        if (('A' <= codePoint && codePoint <= 'Z') || ('a' <= codePoint && codePoint <= 'z')) {
            return true;
        }
        return false;
    }
}
