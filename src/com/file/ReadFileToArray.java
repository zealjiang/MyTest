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
	 * @param fileName  文件名称
	 * @param encoding  读取文件使用的编码格式
	 * @return 返回一个字符串数组
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
		
		//去掉数组中出现的换行符
		String str = sb.toString().replace("\r\n", "");
		
		//按内容中的";"号，将内容打装成数组
		String[] sql=str.split(";");

		//去掉"/*xxxx*/"这部分内容
		String[] array = subString(sql);
		
		return array;

	}
	
	/**
	 * 
	 * @param str[]  将数组str中的 "/*xxxx* /" 这部分内容去除
	 */
	public static String[] subString(String[] str){
//		DBUtil.LogInf("******************************************** ");
		String[] newArray = new String[str.length];
		for(int k=0;k<str.length;k++){
			int end   = str[k].trim().lastIndexOf("*/");	
			//end==-1 表示没有找到"*/"
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
