package com.string;

import org.junit.Test;

public class Formater {

	public static void main(String[] args) {
		
		String str = "版本不一致,需要更新,[新版本号]";
		String data = getData(str);
		System.out.println("data..."+data);
	}
	
	public static String getData(String str){
		
		int k = str.lastIndexOf("[");
		String data = str.substring(k+1, str.length()-1);
		return data;
	}
	
	@Test
	public void timeFormatter(){
		String value = 6 + "分钟";
		String updateAtValue = String.format("上次更新于%1$s前", value);
		System.out.println("---updateAtValue---"+updateAtValue);
	}
}
