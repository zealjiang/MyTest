package com.string;

import java.util.StringTokenizer;

public class MyStringTokenizer {

	public static void main(String[] args) {
		String s1 = " public static void, main";
		String s2 = " StringTokenizer pas2? = new StringTokenizer";
		StringTokenizer pas1 = new StringTokenizer(s1," ,");
		StringTokenizer pas2 = new StringTokenizer(s2," ?");
		int n1 = pas1.countTokens(),n2 = pas2.countTokens();
		System.out.println("s1有单词："+n1+"个，全部单词如下：");
		while(pas1.hasMoreTokens()){
			String s = pas1.nextToken();
			System.out.println(s);
		}
		System.out.println("s2有单词："+n2+"个，全部单词如下：");
		while(pas2.hasMoreTokens()){
			String s = pas2.nextToken();
			System.out.println(s);
		}
	}
}
