/**
 * 2013-10-15
 * 下午3:36:21
 */
package com.string;

import org.junit.Test;

/**
 * @author Administrator
 * @time 2013-10-15下午3:36:21
 */
public class StringStartWith {

	public static void main(String[] args) {
		
	}
	
	@Test
	public void mStartWith(){
		String str = "http_aaa.jpg";
		if(str.startsWith("http"))
			System.out.println("http");
		else
			System.out.println("no http");
	}
	
	@Test
	public void mStartWith3(){
		String str = "1900-01-01T00:00:00";
		if(str.startsWith("1900-01-01"))
			System.out.println("1900-01-01");
		else
			System.out.println("no 1900-01-01");
	}
	
	@Test
	public void mStartWith2(){
		String str = "http://tour.ishowchina.com/rest/order/submit?name=李志江&phone=18618269575&icno=130481198608046935&key=1E3B4B16AE194ECD8A9657413BD7E847";
		int i = str.indexOf("?");
		String a = str.substring(i+1);
		System.out.println(a);
		
		String[] p = a.split("&");
		for (int j = 0; j < p.length; j++) {
			System.out.println(p[j]);
			
			String[] m = p[j].split("=");
			
			System.out.println(m[0]+"   "+m[1]);
		}
		
		
	}
}
