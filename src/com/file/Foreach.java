package com.file;

import java.io.File;

public class Foreach {

	public static void main(String[] args) {
		String[] fruit = {"ƻ��","�㽶","Ѽ��"};
		int i= 0;
		for (String fileCheck : fruit) {
			if(fileCheck.equals("ƻ��1")){
				System.out.println("��ʱ����"+fileCheck);
				i++;
			}else if(fileCheck.equals("�㽶1")){
				System.out.println("��ʱ����"+fileCheck);
				i++;
			}
		}
		System.out.println(i);
	}
}
