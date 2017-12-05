package com.string;

import java.io.File;

import org.junit.Test;


public class StringIndex {

	public static void main(String[] args) {
		String str = "/ÈýÐÇi9001/";
		int k = str.indexOf("001");
		System.out.println("k-->"+k);
		int m = str.indexOf("/");
		System.out.println("m-->"+m);
		int n = str.lastIndexOf("/");
		System.out.println("n-->"+n);
	}
	

	public boolean isPicFile(File fs){
		File f = new File("d:/www/a.jpg");
		//if(!f.exists())return false;
		int i = f.getName().lastIndexOf(".");
		if(i ==-1)return false;
		String suffix = f.getName().substring(i+1);
		System.out.println("suffix :"+suffix);
		if(suffix.toLowerCase().contains("jpg")||
				suffix.toLowerCase().contains("jpeg")||
				suffix.toLowerCase().contains("png")||
				suffix.toLowerCase().contains("gif")||
				suffix.toLowerCase().contains("bmp")){
			return true;
		}
		return false;
	}
	
	@Test
	public void isPicFile(){
		File f = new File("d:/www/a.bmp");
		//if(!f.exists())return false;
		int i = f.getName().lastIndexOf(".");
		if(i ==-1)return;
		String suffix = f.getName().substring(i+1);
		System.out.println("suffix :"+suffix);
		if(suffix.toLowerCase().contains("jpg")||
				suffix.toLowerCase().contains("jpeg")||
				suffix.toLowerCase().contains("png")||
				suffix.toLowerCase().contains("gif")||
				suffix.toLowerCase().contains("bmp")){
			System.out.println("true");
		}
	}
}
