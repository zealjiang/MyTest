package com.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteStringToFile {

	public static void main(String[] args) {
		
		//String read = ReadFileToString.readFile(new File("e:/hhh.SQL"),null);
		
		String read = "zealjiang读取波形记录文件16中的第一个时间戳和最后一个时间戳及其";
		tableCodeFile("d:/123.txt",read);
		System.out.println(Integer.toHexString(Integer.valueOf("16")));
	}
	
	public static void tableCodeFile(String fileName,String str){
		
		//判断"/data/data/com.bksx.dxsczdcjdw/files/log.txt"文件是否存在，若不存在就创建，存在就追加
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		}	
		
		 //将内容写入到logGlobal.txt文件中
		byte[] buffer = new byte[1024];
		byte[] src = str.getBytes();
		int total_length = str.getBytes().length;
		int concurrent_length = total_length;
		int start = 0;
		
		if(concurrent_length>0){
			while(concurrent_length-buffer.length>0){
				
				try {
					fos.write(src,start,buffer.length);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				start += buffer.length;
		
				concurrent_length = total_length-start;
			}	

			try {
				fos.write(src,start,concurrent_length);
				if(fos!=null){
					fos.close();
				}				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
}
