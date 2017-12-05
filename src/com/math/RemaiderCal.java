package com.math;

public class RemaiderCal {

	public static void main(String[] args) {
		RemaiderCal rc = new RemaiderCal();
		//测试一
		rc.compareSelfWithInt();
	}
	
	/**
	 * 一个float类型的数据与其自身整型值的比较
	 * @author zealjiang
	 * @date 2016年4月26日 上午10:23:53
	 *
	 */
	private void compareSelfWithInt(){
		float a = 4.37f;
		if(a>(int)a){
			System.out.println(4.37f+"有小数部分");
		}else{
			System.out.println(4.37f+"无小数部分");
		}
		
		float b = 4.00f;
		if(b>(int)b){
			System.out.println(4.00f+"有小数部分");
		}else{
			System.out.println(4.00f+"无小数部分");
		}
	}
}
