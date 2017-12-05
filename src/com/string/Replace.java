package com.string;

import org.junit.Test;

public class Replace {

	public static void main(String[] args) {
		
		//test 1
		String str = "1,2,3,4";
		String strs = getDeli(str);
		System.out.println("strs :"+strs);
		
		//test 2
		String date = "2015-06-26";
		afterReplaced(date);
		System.out.println("date :"+date);
	}
	
	/**
	 * ½«,ºÅÌæ»»³É;ºÅ
	 * @param str
	 * @return  
	 */
	public static String getDeli(String str){
		
		str = str.replace(",", ";");
		return str;
	}
	
	@Test
	public void testAfterReplaced(){
		String date = "2015-06-26";
		date = date.replace("-", "");
		System.out.println("date :"+date);
	}
	
	public static void afterReplaced(String date){

		date = date.replace("-", "");
		System.out.println("date :"+date);
	}
}
