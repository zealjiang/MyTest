package com.array;

public class SecondArray {

	private static String[][] secondArray;
	public static void main(String[] args) {
		secondArray = new String [][]{{"12","��","����"},{"13","Ů","����"},{"14","��","����"},{"16","Ů","����"},{"18","��","ҪҪ"}};
		System.out.println("length :"+secondArray.length);
		
		System.out.println("secondArray[0].length  :"+secondArray[0].length+"   secondArray.length :  "+secondArray.length);
		String[][] dataArray = new String[secondArray[0].length][secondArray.length];
		for (int i = 0; i < secondArray[0].length; i++) {
			for (int j = 0; j < secondArray.length; j++) {
				dataArray[i][j] = secondArray[j][i];
//				System.out.println("secondArray"+"["+j+"]"+"["+i+"]     :"  +secondArray[j][i]);
				
				System.out.println("dataArray"+"["+i+"]"+"["+j+"]     :"  +dataArray[i][j]);
			}
		}
	}
	

}
