/**
 * 2013-12-4
 * ÉÏÎç11:31:37
 */
package com.string;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

/**
 * @author Administrator
 * @time 2013-12-4ÉÏÎç11:31:37
 */
public class ReplaceMethod {

	@Test
	public void myReplace() throws UnsupportedEncodingException{
			String s = "http://PoiService.jsp?requestMethod=getPoiNameByKeyword&keyword=KEYWORD&city=CITY&customer=2&encode=GBK&key=df83fb=02&outGb=02";
			String result = s.replace("KEYWORD", ")");
			System.out.println("result--->"+result);
	}
	
	@Test
	public void multiReplace() throws UnsupportedEncodingException{
		String s = "http://PoiService.jsp?;http://PoiService.jsp?;http://PoiService.jsp?";
		String result = s.replace("http://PoiService.jsp", "http://130.10.2.1:8089");
		System.out.println("result--->"+result);
}
}
