package com.array;

import java.util.ArrayList;

public class ArrayToString {
	
	
	public static void main(String[] args) { 
		ArrayToString ats = new ArrayToString();
		String[][] arrays = new String[2][2];
		arrays[0][0] = "���Ҫ";
		arrays[0][1] = "������";
		arrays[1][0] = "���ش�";
		arrays[1][1] = "��׼";
		
		
		ats.testPointChange();
		
		
	}
	
	private void testPointChange(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("ʨ��");
		pointChange(list);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i+": "+list.get(i));
		}
	}
	private void pointChange(ArrayList<String> list){
		list.add("��è");
	}

}
