package com.text;

import java.text.MessageFormat;


public class MyMessageFormat {

	public static void main(String[] args) {
		
		 Object[] arrayOfObject = new Object[2];
		 arrayOfObject[0] = "123456";
		 arrayOfObject[1] = "pwd";
		 String str5 = MessageFormat.format("http://www.bczp.cn/iphone/login.aspx?username={0}&password={1}", 
				 arrayOfObject);
		 System.out.println("str5:"+str5);
	}
}
