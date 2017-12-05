package com.file;

import java.io.File;

public class MakeDirectory {

	public static void main(String[] args) {
		//String path = "e:/abcd";
		//makeDir(path);
		createDir("E:/abcd/a/b/c/d");
	}
	
	public static void makeDir(String path){
		
		File file = new File(path);
		
		if(!file.exists()){
			file.mkdir();
		}else{
			
		}
	}
	
	/**
	 * ´´½¨Ä¿Â¼
	 */
	public static void createDir(String path){
		if(!new File(path).exists()){
			new File(path).mkdirs();
		}
	}
}
