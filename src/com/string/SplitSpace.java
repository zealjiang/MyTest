package com.string;

public class SplitSpace {

	public static void main(String[] args) {
		
/*		//����һ
		String str = "   2  3  36";
		
		String test = getSplitspace(str);
		System.out.println("test  :"+test);*/
		
		
/*		//���Զ�
		String test2 = "0 5 8 ";
		getNonSpace(test2);*/
		
		
		//������
		String str = " 1 3 2";
		getSplitspace(str);
		
		//������
		calLength("");
	}
	
	public static String getSplitspace(String str){
		
		StringBuilder sb = new StringBuilder();
		//ȥ��
		String new_str = str.replace(" ","");
		char[] char_array = new_str.toCharArray();
		//�ӣ���
		
		for(int i=0;i<char_array.length;i++){
			
			sb.append(char_array[i]+",");
//			System.out.print(char_array[i]+",");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println("sb "+sb.toString());

		return sb.toString();
	}
	
	public static String getNonSpace(String str){
		
		String[] new_data = str.split(" ");
		for(int i=0;i<new_data.length;i++){
			System.out.print(new_data[i]);
		}
		return null;
	}
	
	public static String getSplitspace2(String str){
		
		StringBuilder sb = new StringBuilder();
		
		String[] new_data = str.split(" ");
		for(int i=0;i<new_data.length;i++){			
			sb.append(new_data[i]+",");
		}
		String ss = sb.substring(0,sb.length()-1);
		System.out.println("sb "+ss);

		return ss;
	}
	
	/**
	 * �����Կո�ָ���string�����ݳ���
	 * @author zealjiang
	 * @date 2016��4��25�� ����3:48:52
	 * @param str
	 * @return
	 *
	 */
	public static int calLength(String str){
		String data="60 80 e0 e0 e0 e0 e0 e0 e0 80 e0 df df df df df df 80 df df e0 e0 e0 e0 e0 80 e0 e0 e0 e0 e0 e0 e0 80 e0 e0 e0 e0 df df df 80 df df df df df de de 80 de de dc de de de df 80 df df df dd df df df 80 de de de e0 de de de 80 de a6";
		String[] array = data.split(" ");
		System.out.println("array length: "+array.length);
		return array.length;
	}
	
}
