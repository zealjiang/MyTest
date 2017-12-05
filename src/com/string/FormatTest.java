package com.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatTest {

	public static void main(String[] args) {
		String str = "2011-05-66 15:19";
		String str2 = "2011051s6 2119";
		String str3 =" 22:18";
//		String strf = str.format("%1$tY %tm %td",str);
		//¼òµ¥Æ¥Åä2011-05-06 05:26
		boolean boo = str.matches("^[0-9]{4}[-][0-9]{2}[-][0-9]{2}[\\s](0[0-9]{1}|1[0-9]{1}|2[0-3]{1})[:](0[0-9]{1}|1[0-9]{1}|2[0-3]{1})$");
		System.out.println(boo);
		//¼òµ¥Æ¥Åä2 20110605 0236
		String RegEx = "^[0-9]{8}[\\s][0-9]{4}$";
		String RegEx2 = "[-]";
		Pattern p = Pattern.compile(RegEx2);
		Matcher m = p.matcher(str);
		boolean boo2 = str2.matches(RegEx);
		System.out.println(">>"+boo2);
		System.out.println("..>"+m.find(0));
		System.out.println(str2.substring(0,4)+"-"+str2.substring(4,6)+"-"+str2.substring(6,8)
				+""+str2.substring(8,11)+":"+str2.substring(11,13));
	}
}
