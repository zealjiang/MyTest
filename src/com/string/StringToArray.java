package com.string;

public class StringToArray {

	public static void main(String[] args) {
		String str = "���˱��,����";
		String[][] haha = getArray(str,1,2);
		System.out.println(haha[0][0]+"   "+haha[0][1]);
	}
	
	/**
	 * 
	 * @param str	��ת���ɶ�ά���������Դ���ַ���(��ͬ�������м���','���ŷָ�)
	 * @param one	��ά����ĵ�һά����
	 * @param two	��ά����ĵڶ�ά����
	 * @return	��strת���ɵĶ�ά����
	 */
	public static String[][] getArray(String str,int one,int two){
		
		String[][] two_dimension = new String[one][two];
		String[] newstr = str.split(",");
		System.out.println(newstr[0]+"   "+newstr[1]+"  "+newstr.length+"   "+two_dimension.length);
		//���˱��
		two_dimension[0][0] = newstr[0]; 
		//����
		two_dimension[0][1] = newstr[1]; 
		
		return two_dimension;	
	} 
}
