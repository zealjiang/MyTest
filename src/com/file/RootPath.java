package com.file;

import java.io.File;

public class RootPath {

	public static void main(String[] args) {
		String path= "d:/";
		File file = new File(path);
		File[] files = file.listFiles();
		for(int i=0;i<files.length;i++){
			System.out.println(files[i].getPath());
			System.out.println(">>"+new File(files[i].getPath()).getName());
		}
		
		File[] root = File.listRoots();
		for(int i=0;i<root.length;i++){
			System.out.println(root[i].getPath());
		}
		
		System.out.println(Integer.MAX_VALUE);
	}
}
