package com.string;

public class StringToArray {

	public static void main(String[] args) {
		String str = "个人编号,姓名";
		String[][] haha = getArray(str,1,2);
		System.out.println(haha[0][0]+"   "+haha[0][1]);
	}
	
	/**
	 * 
	 * @param str	查转换成二维数组的数据源即字符串(不同的内容中间用','逗号分隔)
	 * @param one	二维数组的第一维长度
	 * @param two	二维数组的第二维长度
	 * @return	由str转换成的二维数组
	 */
	public static String[][] getArray(String str,int one,int two){
		
		String[][] two_dimension = new String[one][two];
		String[] newstr = str.split(",");
		System.out.println(newstr[0]+"   "+newstr[1]+"  "+newstr.length+"   "+two_dimension.length);
		//个人编号
		two_dimension[0][0] = newstr[0]; 
		//姓名
		two_dimension[0][1] = newstr[1]; 
		
		return two_dimension;	
	} 
}
