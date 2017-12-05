package com.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class InputOutput {

	public static void main(String[] args) throws IOException {

		   File srcfile1 = new File("K:/tonghuadaquan/res/raw/story1.db");
		   File srcfile2 = new File("K:/tonghuadaquan/res/raw/story2.db");
		   File srcfile3 = new File("K:/tonghuadaquan/res/raw/story3.db");
		   File srcfile4 = new File("K:/tonghuadaquan/res/raw/story4.db");
		   FileAdd(srcfile1);
		   FileAdd(srcfile2);
		   FileAdd(srcfile3);
		   FileAdd(srcfile4);
	}
	
	public static void FileAdd( File srcfile) throws IOException{
		   File desfile = new File("e:/story.db");
		   RandomAccessFile raf = null ;
		   FileInputStream in = null;
		   FileOutputStream localFileOutputStream = null;

		   if(raf==null){
			   raf = new RandomAccessFile(desfile,"rw");
	   		}
		   try {
			  int n ; 
		     in = new FileInputStream(srcfile);
		     byte[] arrayOfByte = new byte[1024];
		     //将指针移到文件尾
		     long length =  raf.length();
		     raf.seek(length);
		     
		     while (-1 != (n = in.read(arrayOfByte))) {
		    	 raf.write(arrayOfByte, 0, n);
             }
         } finally {
         	try {
                 if (localFileOutputStream != null) {
                	 localFileOutputStream.close();
                 }
             } catch (IOException ioe) {
                 
             }
         }
	}
}
