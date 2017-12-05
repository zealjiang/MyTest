package com.file.split;

public class SizeFormatException extends Exception{
	
	public SizeFormatException(){
		System.out.println("size 只能以M或K结尾");
	}
}
