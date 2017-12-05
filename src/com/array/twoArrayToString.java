package com.array;

public class twoArrayToString {

	public static void main(String[] args) {
		
		
		String[][] array = new String[2][3];
		
		array[0][0] = "aa";
		array[0][1] = "bb";
		array[0][2] = "cc";
		array[1][0] = "dd";
		array[1][1] = "ee";
		array[1][2] = "ff";
		
		String p = twoArrayToString2(array);
		System.out.println("P:  "+p);
	}
	
	
	public static String twoArrayToString2(String[][] array){
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				sb.append(array[i][j]+",");
			}
			sb.append("|");
		}
		//去掉最后的",|"
		String new_str = sb.substring(0,sb.length()-2);
		return new_str;
	} 
}
