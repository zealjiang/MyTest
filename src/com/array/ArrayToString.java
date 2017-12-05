package com.array;

import java.util.ArrayList;

public class ArrayToString {
	
	
	public static void main(String[] args) { 
		ArrayToString ats = new ArrayToString();
		String[][] arrays = new String[2][2];
		arrays[0][0] = "大棒要";
		arrays[0][1] = "奇想在";
		arrays[1][0] = "基地村";
		arrays[1][1] = "标准";
		
		
		ats.testPointChange();
		
		
	}
	
	private void testPointChange(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("狮子");
		pointChange(list);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i+": "+list.get(i));
		}
	}
	private void pointChange(ArrayList<String> list){
		list.add("熊猫");
	}

}
