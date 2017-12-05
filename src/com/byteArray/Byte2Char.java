package com.byteArray;

public class Byte2Char {

	public static void main(String[] args) {
		Byte2Char b2c = new Byte2Char();
		b2c.byteToChar();
		
		b2c.byteArrayToString();
	}
	
	public void byteToChar(){
	
		byte b= (byte) 0xF1;		
		char c=(char)b;		
		System.out.println("Ascii码"+c);
	}
	
	
	public void byteArrayToString(){
		byte[] byteArray = new byte[3];
		
	//  十进制                     = 十六进制
		byteArray[0] = (byte)0x97;
		byteArray[1] = (byte)0x43;
		byteArray[2] = (byte)0x5A;
		
		System.out.println("byteArray[0]: "+byteArray[0]);
		System.out.println(Integer.toHexString(byteArray[0]));
		
		int v = byteArray[0] & 0xFF;
		String hv = Integer.toHexString(v);
		System.out.println("byteArray[0]: "+hv);
		
		StringBuilder stringBuilder = new StringBuilder("");
		for (int i = 0; i < byteArray.length; i++) {
			stringBuilder.append((char)byteArray[i]);
		}
		
		System.out.println(stringBuilder.toString());
		
	}
}
