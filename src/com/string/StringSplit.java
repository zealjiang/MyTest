package com.string;

import org.junit.Test;

public class StringSplit {

	public static void main(String[] args) {
		
/*		//测试一
		String test = "张三l|410603198811151537|19661208000000|1|01|北京海淀|12|370000000000|110000000000|01|10|10|0|0|0|01|A0000|null|北京北控三兴信息技术|110101000000|null|0|5000000                                           |null|3|5|3|null|01|null|02|2011年1月1日|01|000211|0|0|1|null|null|10|";		
		mySplit(test);*/
		
/*		//测试二
		String str = " ,1";
		split2(str);*/
		
/*		//测试三
		String data = "bc2472e3119601,130481000000,20,22,0,01,13,03,北京胡同,2011年01月11日,2011年02月15日,02,123456,北控三兴,03,10,J0000," +
				"A00001013,2000000,0,110102000000,1, 153  , 153  ,|,怀特,21140319871211803X,2,06,19881111000000,河北省邯郸市,0," +
				"bbfa5c33b17700,1401042,20111111120930,0115001012,,bc2472e3119600,1";
		
		split3(data);*/
		
		//测试四
		splitBySymbol("url&url2&url3","&");
		
		
	}
	public static void mySplit(String str){
		
		String[] test = str.split("\\|");
		System.out.println(test.length);
		for(int i=0;i<test.length;i++){
			System.out.println(test[i]);
		}
	}
	
	public static void split2(String str){
		
		String[] array = str.split(",");
		System.out.println(array[0]+"@       @"+array[1]);
	}
	
	public static void split3(String data){
		String[] metadata = data.split("\\|");
		String[] array = metadata[1].split(",");;
		String[] one_array = metadata[0].split(",");
		
		array[5] = array[5].substring(0,array[5].length()-6);
		
		System.out.println("array[5]"+array[5]);
		System.out.println("array.length:"+array.length+"  one_array.length :"+one_array.length);
		
	}
	
	@Test
	public void test(){
		String str = "sdcard/1/2/3/4/1.txt";
		String[] strs = str.split("/");
		for(int i=0;i<strs.length;i++){
			System.out.println(strs[i]);
		}
	}
	
	@Test
	public void split(){
		String str = "sdcard/1/2/3/4/1.txt";
		int num = str.lastIndexOf("/");
		String sub = str.substring(0, num);
		System.out.println(sub);
		System.out.println(num);
		System.out.println(str.substring(num+1,str.length()));
	}
	
	public static void splitBySymbol(String str,String symbol){
		String[] s = str.split(symbol);
		for(int i=0;i<s.length;i++){
			System.out.println("-->"+s[i]);
		}
	}
	
	@Test
	public void splitComma(){
		String newBusiness = "",newBusiness_gp = "",newBusiness_jc = "";
		newBusiness = newBusiness_gp+","+newBusiness_jc;
		//去头尾逗号
		if(newBusiness.startsWith(","))
			newBusiness = newBusiness.substring(1);
		else if(newBusiness.endsWith(","))
			newBusiness = newBusiness.substring(0,newBusiness.length()-1);
		System.out.println("newBusiness-->"+newBusiness);
	}
	
	@Test
	public void sliptEmpty(){
		String slonlat = "";
   	 	String[] slonlatArray = slonlat.split(",");
   	 	System.out.println(slonlatArray);
	}
	
	@Test
	public void sliptDot(){
		String slonlat = "186,";
   	 	String[] slonlatArray = slonlat.split(",");
   	 	System.out.println(slonlatArray);
	}
}
