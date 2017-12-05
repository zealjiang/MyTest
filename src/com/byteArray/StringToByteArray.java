package com.byteArray;

import java.util.ArrayList;

public class StringToByteArray {

	public byte[] readStringToByteArray(){
		
		String s = "60 80 e1 e1 e1 e2 e2 e2 e2 80 e2 e2 e2 e2 e3 e5 e5 80 e5 e5 e3 e3 e3 e4 e4 80 e4 e4 e4 e5 e5 e5 e6 80 ea eb eb eb e5 e5 e5 80 e7 ea eb eb ea e2 e2 80 e2 e2 e5 e7 e7 e7 e3 80 e3 e3 e3 e6 e6 e6 e6 80 e2 e2 e2 e4 e5 e5 e5 80 e5 8b";
		
		ArrayList<Byte> list = new ArrayList<Byte>();
		String[] sa = s.split(" ");
		byte[] bArray = new byte[sa.length];
		for (int i = 0; i < sa.length; i++) {
			byte b = Integer.valueOf(sa[i], 16).byteValue();
			list.add(b);
			bArray[i] = b;
		}
				
		return bArray;	
	}
}
