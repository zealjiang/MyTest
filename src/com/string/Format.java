package com.string;

public class Format {

	public static void main(String[] args) {
		Object[] arrayOfObject = new Object[9];
		arrayOfObject[0] = "张三";
		arrayOfObject[1] = "rw";
		arrayOfObject[2] = "20136";
		arrayOfObject[3] = "2011-08-190";
		arrayOfObject[4] = "xx";
		arrayOfObject[5] = "张三1";
		arrayOfObject[6] = "张三2";
		arrayOfObject[7] = "张三3";
		arrayOfObject[8] = "张三4";
	
		String str5 = String.format("名称: %s\n权限: %s\n大小: %s\n时间戳: %td %tb %tY %tT\n所有者: %s\n用户组: %s", arrayOfObject);
		System.out.println(str5+"  ..");
		
		System.out.println(Integer.toHexString(8175855));
	}
}
