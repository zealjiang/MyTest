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
		
		
		String ss = "bc24730509f401,130402000000,30,22,1,13,11,02,�ɲ�,2011��12��17��,2009��11��17��,99,,����,01,06,C0000,A00001011,1000000,1,110101000000,1,   3  ,   3 2,|,����ɽ,130481198608046935,1,01,,̨��,0,bc2193a1677a00,1401051,20111117145157,0115001028,,bc24730509f400,1";
		
		
		// ��ǰ̨���������ݷ�װ��һά����
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
		// ��ǰ̨���������ݷ�װ��һά����
		String[] metadata = ss.split("\\|");
		
		
		for(int i=0;i<metadata.length;i++){
			String[] array = metadata[i].split(",");
			for(int j=0;j<array.length;j++){
				System.out.println("array["+j+"] "+array[j]);
			}
			
		}	
	}
	
	public static void test4(){
		String ss = "11,��,12,��ũ,13,����,02,ѧϰ��ѵ,04,���,05,��Ǩ,09,�β�����,10,Ͷ������,14,��������,99,����" +
				"|01,�Թ�����,02,���޷���,03,����,04,��ס,05,��λ����,06,��������,08,��������,99,����" +
				"|1,��ż,2,��Ů,3,��ĸ,4,�游ĸ/���游ĸ,5,����ĸ/����,6,����/��Ů/������/����Ů,7,ϱ��,8,�ֵܽ���,9,����" +
				"|12,��ũҵ,22,ũҵ,99,�޻���|";
		String[] sss = ss.split("\\|");
		System.out.println("test 4 :"+sss[sss.length-3]);
	}
	
	@Test
	public  void test5(){
		String ss = ",��,12,��ũ,13,����";
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
		String ss = "1�����µ�ͼ��2���޸���һЩbug��";
		String[] sss = ss.split("��");
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
