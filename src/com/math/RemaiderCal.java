package com.math;

public class RemaiderCal {

	public static void main(String[] args) {
		RemaiderCal rc = new RemaiderCal();
		//����һ
		rc.compareSelfWithInt();
	}
	
	/**
	 * һ��float���͵�����������������ֵ�ıȽ�
	 * @author zealjiang
	 * @date 2016��4��26�� ����10:23:53
	 *
	 */
	private void compareSelfWithInt(){
		float a = 4.37f;
		if(a>(int)a){
			System.out.println(4.37f+"��С������");
		}else{
			System.out.println(4.37f+"��С������");
		}
		
		float b = 4.00f;
		if(b>(int)b){
			System.out.println(4.00f+"��С������");
		}else{
			System.out.println(4.00f+"��С������");
		}
	}
}
