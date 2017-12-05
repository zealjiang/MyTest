package com.string;

import org.junit.Test;

public class split {

	public static void main(String[] args) {
		

		//test1();
		test2();
		//test3();
		//test4();
	}

	
	public static void test1(){
		
		
		String ss = "bc24730509f401,130402000000,30,22,1,13,11,02,干部,2011年12月17日,2009年11月17日,99,,三星,01,06,C0000,A00001011,1000000,1,110101000000,1,   3  ,   3 2,|,孙中山,130481198608046935,1,01,,台湾,0,bc2193a1677a00,1401051,20111117145157,0115001028,,bc24730509f400,1";
		
		
		// 将前台传来的数据封装成一维数组
		String[] metadata = ss.split("\\|");
		String[] array = metadata[1].split(",");
		String[] one_array = metadata[0].split(",");
		
		System.out.println("metadata[1] "+metadata[1]);
		
		for(int i=0;i<array.length;i++){
			System.out.println("array["+i+"] "+array[i]);
		}
		System.out.println("array[5] "+array[5]);
		System.out.println("array.length:"+array.length+"  one_array.length :"+one_array.length);
		
		//array[5] = array[5].substring(0,array[5].length()-6);
		
	}
	
	public static void test2(){
		String[] ss = {"20112.apk","20115552.txt"};
	
		for(int i=0;i<ss.length;i++){
			String lastname = ss[i].substring(ss[i].length()-3, ss[i].length());
			if(lastname.equals("apk")){
				System.out.println(ss[i]);
				
			}
		}
	
	}
	
	public static void test3(){
		String ss = "";
		// 将前台传来的数据封装成一维数组
		String[] metadata = ss.split("\\|");
		
		
		for(int i=0;i<metadata.length;i++){
			String[] array = metadata[i].split(",");
			for(int j=0;j<array.length;j++){
				System.out.println("array["+j+"] "+array[j]);
			}
			
		}	
	}
	
	public static void test4(){
		String ss = "11,务工,12,务农,13,经商,02,学习培训,04,婚嫁,05,随迁,09,治病疗养,10,投靠亲友,14,工作调动,99,其他" +
				"|01,自购房屋,02,租赁房屋,03,寄宿,04,借住,05,单位宿舍,06,建筑工地,08,工作场所,99,其他" +
				"|1,配偶,2,子女,3,父母,4,祖父母/外祖父母,5,岳父母/公婆,6,孙子/孙女/外孙子/外孙女,7,媳婿,8,兄弟姐妹,9,其他" +
				"|12,非农业,22,农业,99,无户口|";
		String[] sss = ss.split("\\|");
		System.out.println("test 4 :"+sss[sss.length-3]);
	}
	
	@Test
	public  void test5(){
		String ss = ",务工,12,务农,13,经商";
		String[] sss = ss.split(",");
		System.out.println("test 4 :"+sss[sss.length-1]);
	}
	
	
	@Test
	public  void test6(){
		String ss = "com.leador";
		String[] sss = ss.split("\\.");
		System.out.println("test 5 :"+sss[sss.length-1]);
	}
	
	@Test
	public  void test7(){
		String ss = "1、更新地图；2、修复了一些bug。";
		String[] sss = ss.split("；");
		for(int i=0;i<sss.length;i++){
			System.out.println(sss[i]);
		}
	}
	
	@Test
	public  void test8(){
		String ss = "com"+"\n";
		System.out.println(ss.length());//4
		System.out.println(ss.substring(0,ss.length()-1));
	}
	
	@Test
	public void test9(){
		String s = "2,1";
		int i = s.split(",").length;
		String[] array = s.split(",");
		for (int j = 0; j < array.length; j++) {
			System.out.println(j+" :" +array[j]);
		}

	}
}
