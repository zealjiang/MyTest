package com.string;

import org.junit.Test;

public class Formater {

	public static void main(String[] args) {
		
		String str = "�汾��һ��,��Ҫ����,[�°汾��]";
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
		String value = 6 + "����";
		String updateAtValue = String.format("�ϴθ�����%1$sǰ", value);
		System.out.println("---updateAtValue---"+updateAtValue);
	}
}
