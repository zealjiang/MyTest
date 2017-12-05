package com.string;

import org.junit.Test;

public class SubString {
	
	public static void main(String[] args) {
/*		String str = "select id limit ?,?";
		
		int i = str.lastIndexOf("limit");
		System.out.println("..."+i);
		String str2 = str.substring(0, i);
		System.out.println("....."+str2);*/
		
		
		//测试二
		String src = "130481000000";
		String newt = src.substring(0, 3);
		
		String newstr = subString(src,0,3);
			
		//测试三
		String news = src.substring(3);
		
		System.out.println("newstr  "+newstr+"  "+newt+"  "+news);
		
/*		//测试三
		String str1 = "11111,,v是个,21140319871211803X,140524000000,1,01,2011年11月7日000000,30,13,12,辽宁省葫芦岛,0,11,,1,7d79e378286dd6,1401002,20111110201120,0115001001,2011年1月15日,,99,2011年01月01日,一些,,1,06,C0000,A00001011,1000000,110101000000,0,0,bc2472f1786d00,bc2472f1786d01,02,大概,01,1|";
		String str2 = "11,sssd,v是个,21140319871211803X,140524000000,1,01,2011年11月7日000000,30,13,12,辽宁省葫芦岛,0,11,,1,7d79e378286dd6,1401002,20111110201120,0115001001,2011年1月15日,,99,2011年01月01日,一些,,1,06,C0000,A00001011,1000000,110101000000,0,0,bc2472f1786d00,bc2472f1786d01,02,大概,01,1|";
		
		//System.out.println(str1.indexOf(",",str1.indexOf(",")+1));
		//System.out.println(str1.indexOf(",",1));
		String ss = str1.substring(str1.indexOf(",",0));
		//System.out.println(ss);
		String sss[] = ss.split("\\|");
		System.out.println(sss[0].split(",")[1]);
		
		System.out.println(str2.substring(str2.indexOf(",",0)));
		//String ss = subString2(str1,1);
		//System.out.println(ss);
*/	}
	
	public static String subString(String src,int start,int end){
		
		String sub;
		sub = src.substring(start, end);
		return sub;
	}
	
	
	public static String subString2(String str,int k){
		
		str.indexOf(",");
		String[] strs = str.split(",");
		if(strs[k].equals("")){
			strs[k] = " ";
		}
		String new_str = strs[0]+strs[1];
		//new_str = new_str+str.indexOf(0,",");

		return new_str;
	}
	
	@Test
	public void subStringFromStart(){
		String str = "1234567";
		str = str.substring(0, str.length()-1);
		System.out.println("str-->"+str);
		
		System.out.println("123456.substring(1)-->"+"123456".substring(1));
	}
	
	@Test
	public void deleteF(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("男号回来回"+"\n");
		stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
		System.out.println("stringBuilder-->"+stringBuilder.toString());
		System.out.println("-----------------");
	}
	
	@Test
	public void subSequence(){
		String s = "123456";
		//String selection = ""
		System.out.println(s.length());
		String a = (String)s.toString().subSequence(0,s.length() - 1);
		
		System.out.println(a);
		System.out.println(s.length() - 1);
	}
}
