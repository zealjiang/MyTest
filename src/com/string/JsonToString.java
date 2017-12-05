package com.string;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JsonToString {

	public static void main(String[] args) {
		File read_file = new File("d:/1.txt");
		String s = readFile(read_file,null).trim();
		//System.out.println("s :"+s);
		s = jsonString(s);
		System.out.println(s);
		
		
		
	}
	
	public static String readFile(File file,String charater){
		if(charater == null || charater.equals(""))
			charater = "UTF-8";
		String out_str="";
		StringBuilder sb = new StringBuilder();
		
		try {
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int n;
			try {
				while((n = in.read(buffer))!=-1){
					out_str = new String(buffer,0,n,charater);
					sb.append(out_str);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String s_temp = sb.toString();
		//将文件内容的第一个?号去掉
		s_temp = sb.toString().substring(1);

		return s_temp;
	}
	
	
	public static String jsonString(String json){
		StringBuilder sb = new StringBuilder();
		sb.append('\"');
		String newjson = json.replace("\\", "\\\\").replace("\"", "\\\"").replace("\r\n", "");
		//System.out.println("newjson :"+newjson);
		sb.append(newjson);
		sb.append('\"');
		return sb.toString();
	}
}
