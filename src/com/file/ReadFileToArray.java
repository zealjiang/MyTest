package com.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ReadFileToArray {

	public static void main(String[] args) {
		
		String fileName = "e:/update.sql";
		String encoding = "gbk";
		String[] test = readFileFromUpdataFile(fileName,encoding);
		
		for(int k=0;k<test.length;k++){
			System.out.println(test[k]);
		}
	}
	
	/**
	 * 
	 * @param fileName  �ļ�����
	 * @param encoding  ��ȡ�ļ�ʹ�õı����ʽ
	 * @return ����һ���ַ�������
	 */
	public static String[] readFileFromUpdataFile(String fileName,String encoding){
		
		String out_str;
		StringBuilder sb = new StringBuilder();
		
		try {				
			FileInputStream in = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			int n;
			try {
				while((n = in.read(buffer))!=-1){
					out_str = new String(buffer,0,n,encoding);
					sb.append(out_str);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
			
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		
		//ȥ�������г��ֵĻ��з�
		String str = sb.toString().replace("\r\n", "");
		
		//�������е�";"�ţ������ݴ�װ������
		String[] sql=str.split(";");

		//ȥ��"/*xxxx*/"�ⲿ������
		String[] array = subString(sql);
		
		return array;

	}
	
	/**
	 * 
	 * @param str[]  ������str�е� "/*xxxx* /" �ⲿ������ȥ��
	 */
	public static String[] subString(String[] str){
//		DBUtil.LogInf("******************************************** ");
		String[] newArray = new String[str.length];
		for(int k=0;k<str.length;k++){
			int end   = str[k].trim().lastIndexOf("*/");	
			//end==-1 ��ʾû���ҵ�"*/"
			if(end!=-1){
				newArray[k] = str[k].substring(end+2);
			}else{
				newArray[k] = str[k];
			}
/*			DBUtil.LogInf("end   :"+end);
			DBUtil.LogInf("src   :"+str[k]);
			DBUtil.LogInf("des   :"+newArray[k]);*/
		}	
		return newArray;
	}
}
