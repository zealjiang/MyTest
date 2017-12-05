package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

	public static void main(String[] args) {
		 File desfile = new File("e:/random.txt");
	}
	
	public static void addToFile(String str,File des){
				  
		   RandomAccessFile raf = null ;

		   try {
			if(raf==null){
				   raf = new RandomAccessFile(des,"rw");
			}

		     //将指针移到文件尾
		     long length =  raf.length();
		     raf.seek(length);
		     raf.write((str+"\n").getBytes());
		     
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
