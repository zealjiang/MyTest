package com.string;

public class StringArrayFor {


	public static void main(String[] args) {
		String[] ss = madeStringArray2(55,new String[]{"男","女","孙行者","行者孙"});
		for(int i = 0;i<ss.length;i++){
			System.out.println(ss[i]);
		}
		
	}
	
	public static String[] madeStringArray2(int length,String[] str){
		String[] strs = new String[length];
		int j = 0;
		for(int i=0;i<length;i++){

				if(j<str.length-1){
//					System.out.print(str[j]+"  ");
					strs[i] = str[j];
					j++;
					
				}else if(j==(str.length-1)){
//					System.out.print(str[j]+"   ");
					strs[i] = str[j];
					j=0;
					
				}

		}
		return strs;
	}
	
}
