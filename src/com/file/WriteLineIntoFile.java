package com.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WriteLineIntoFile {

	public static void main(String[] args) {
		File desfile = new File("e:/123.txt");
//		String str= "��ɷ�羰������";
		String str= "���������䷿�ϳ�";
		write(str,desfile);
		
	}
	
	public static void write(String str,File desfile){
		  
		   RandomAccessFile raf = null ;

		   if(raf==null){
			   try {
				raf = new RandomAccessFile(desfile,"rw");		    			     
			     try {
				     //��ָ���Ƶ��ļ�β
				     long length =  raf.length();
				     System.out.println("length :"+length);
				     FileWriter fw = new FileWriter(desfile);
					 BufferedWriter bw = new BufferedWriter(fw);
					 bw.write(str,2,str.length());
					 bw.newLine();
					 if(bw!=null){
						 bw.close();
					 }
					 if(fw!=null){
						 fw.close();
					 }
					 if(raf!=null){
						 raf.close();
					 }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   		}
		   
				  


		   
     
	}
}
