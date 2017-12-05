package com.file;

import java.io.File;

public class Foreach {

	public static void main(String[] args) {
		String[] fruit = {"苹果","香蕉","鸭梨"};
		int i= 0;
		for (String fileCheck : fruit) {
			if(fileCheck.equals("苹果1")){
				System.out.println("临时数据"+fileCheck);
				i++;
			}else if(fileCheck.equals("香蕉1")){
				System.out.println("临时数据"+fileCheck);
				i++;
			}
		}
		System.out.println(i);
	}
}
