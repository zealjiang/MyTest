package com.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ��ȡ�ļ�����ʱ�������޸�ʱ��
 */
public class ReadFileTime {

	public static void main(String[] args) {
		getCreateTime();
		getModifiedTime_1();
		getModifiedTime_2();		
	}

	/**
	 * ��ȡ�ļ�����ʱ��
	 */
	public static void getCreateTime(){
		String filePath = "d:\\2.txt";
		String strTime = null;
		try {
			Process p = Runtime.getRuntime().exec("cmd /C dir " 		
					+ filePath
					+ "/tc" );
			InputStream is = p.getInputStream(); 
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));           
	        String line;
	        while((line = br.readLine()) != null){
	    		if(line.endsWith(".txt")){
	    			strTime = line.substring(0,17);
	            	break;
	    		}       	                
	         } 
		} catch (IOException e) {
			e.printStackTrace();
		} 		
		System.out.println("����ʱ��	" + strTime);	
		//���������ʱ��	2009-08-17  10:21
	}
	/**
	 * ��ȡ�ļ��޸�ʱ��ķ���1
	 */	
	@SuppressWarnings("deprecation")
	public static void getModifiedTime_1(){
		File f = new File("d:\\2.txt");  		  
		Calendar cal = Calendar.getInstance();
		long time = f.lastModified();
		cal.setTimeInMillis(time);  
		//�˴�toLocalString()�����ǲ��Ƽ��ģ������Կ����
		System.out.println("�޸�ʱ��[1]	" + cal.getTime().toLocaleString()); 
		//������޸�ʱ��[1]	2009-8-17 10:32:38
	}
	
	/**
	 * ��ȡ�޸�ʱ��ķ���2
	 */
	public static void getModifiedTime_2(){
		File f = new File("d:\\2.txt");  		  
		Calendar cal = Calendar.getInstance();
		long time = f.lastModified();
		System.out.println("time: "+time);
		Date date = new Date(f.lastModified());//�ļ�����޸�ʱ�� 
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		cal.setTimeInMillis(time);  
		System.out.println("�޸�ʱ��[2]	" + formatter.format(cal.getTime()));	
		//������޸�ʱ��[2]	2009-08-17 10:32:38
		
		
		System.out.println(formatter.format(date));
	}
}
