package com.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MakeFile {
	public static void main(String[] args) {
		
/*		//����1
		File file = new File("e:/f1.txt");
		file.isFile();*/
		
		
/*		//����2
		File dir = new File("e:/��ǩ");
		dir.mkdir();
		File file2 = new File("e:/��ǩ/f1.txt");
		try {
			file2.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
/*		//����3
		File dir3 = new File("e:/��ǩ");
		dir3.mkdir();
		File file3 = new File("e:/��ǩ/f1.txt");
		try {
			new RandomAccessFile(file3,"rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
/*		//����4
		File dir3 = new File("e:/��ǩ");
		dir3.mkdir();
		File file3 = new File("e:/��ǩ/f1.txt");
		try {
			new FileOutputStream(file3);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		File dir = new File("e:/dd/");
		dir.mkdir();
		File dirs = new File("e:/dd/ss/");
		dirs.mkdir();
//		try {
//			file.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
