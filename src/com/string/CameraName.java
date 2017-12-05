package com.string;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CameraName {

	
	public static void main(String[] args) {
/*		String c1 = "camera_19800113112455";
		String c2 = "camera_19800113113744";
		String c3 = "camera_19800113113810";
		String c4 = "camera_19800113114230";
		String c5 = "camera_19800113114951";
		String c6 = "camera_19800115082258";
		String c7 = "scamera_19800115082258";
		
		String[] names = new String[]{c1,c2,c3,c4,c5,c6,c7};*/
		

		
		File file  = new File("C:/Documents and Settings/Administrator/桌面/Sdcard下的照片");
		String[] flist = file.list();
		
		System.out.println("总共有"+flist.length);
		for(int i=0;i<flist.length;i++){
			System.out.println(i+"  "+flist[i]+"  matches_name:"+matches_name(flist[i]));
		}
		
	}
	
	
	public static boolean matches_name(String name){
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("camera_\\d{14}+.jpg");
		m = p.matcher(name);
		b = m.matches();
		
		return b;
	} 
}
