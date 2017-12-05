package com.string;

import org.junit.Test;

public class MyContains {

	@Test
	public void myContains(){
		
		String s = "我的位置坐标：lat:"+39.963175+";lon:"+116.400244;
		
		if(s.contains("lat")){
			int i = s.indexOf("lat");
			String j = s.substring(i);
			System.out.println(j);
			String[] array = j.split(";");
			System.out.println(array[0].substring(4));
			System.out.println(array[1].substring(4));
		}
	}
}
