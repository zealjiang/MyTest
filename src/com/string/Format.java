package com.string;

public class Format {

	public static void main(String[] args) {
		Object[] arrayOfObject = new Object[9];
		arrayOfObject[0] = "����";
		arrayOfObject[1] = "rw";
		arrayOfObject[2] = "20136";
		arrayOfObject[3] = "2011-08-190";
		arrayOfObject[4] = "xx";
		arrayOfObject[5] = "����1";
		arrayOfObject[6] = "����2";
		arrayOfObject[7] = "����3";
		arrayOfObject[8] = "����4";
	
		String str5 = String.format("����: %s\nȨ��: %s\n��С: %s\nʱ���: %td %tb %tY %tT\n������: %s\n�û���: %s", arrayOfObject);
		System.out.println(str5+"  ..");
		
		System.out.println(Integer.toHexString(8175855));
	}
}
