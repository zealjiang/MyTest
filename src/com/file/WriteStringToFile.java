package com.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteStringToFile {

	public static void main(String[] args) {
		
		//String read = ReadFileToString.readFile(new File("e:/hhh.SQL"),null);
		
		String read = "zealjiang��ȡ���μ�¼�ļ�16�еĵ�һ��ʱ��������һ��ʱ�������";
		tableCodeFile("d:/123.txt",read);
		System.out.println(Integer.toHexString(Integer.valueOf("16")));
	}
	
	public static void tableCodeFile(String fileName,String str){
		
		//�ж�"/data/data/com.bksx.dxsczdcjdw/files/log.txt"�ļ��Ƿ���ڣ��������ھʹ��������ھ�׷��
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		}	
		
		 //������д�뵽logGlobal.txt�ļ���
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
