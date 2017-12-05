package com.file;

import java.io.File;

import org.junit.Test;

public class CheckFileExist {

	
	@Test
	public void hasThisFile(){
		File apkFile = new File("d:", "jiuzhaigou2.1.4.txt");
		if (apkFile.exists()) {
			System.out.println("exist");
		}else{
			System.out.println("not exist");
		}
	}
}
