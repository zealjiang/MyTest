package com.string;

public class StringArray {

	public static void main(String[] args) {
		
/*		//测试一
		String[] str1 = new String[]{"pic_ryxx"};
		String str1_name = str1[0];
		System.out.println(" ..."+str1_name);*/
		
		//测试二
		String test = "2011001,东方,03,000211,7da462e5b5a501,";
		split(test);
		
	}
	
	public static void split(String str){
		String[] one_array = new String[5];
		one_array = str.split(",");
		System.out.println(one_array[0]+"  "+one_array[1]+"  "+one_array[2]+"  "+one_array[3]+"  "+one_array[4]);
	}
}
