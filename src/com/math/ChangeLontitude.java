package com.math;

import org.junit.Test;

public class ChangeLontitude {

	/**
	 * ����ʽΪ"222.33333,113.455322"�ľ�γ���еĶ���ǰ�����ּ�С����λ��5�󣬷����µľ�γ��
	 * author zealjiang
	 * time 2014��9��11�� ����4:52:55
	 * note:  ���磺�����ǵ�222.33333,113.455322�����ص���222.33338,113.455322
	 * @param lonlat
	 */
	@Test
	public void changeLontitude(){
		
		String lonlat = "222.33333,113.455322";
		
		String[] lonlats = lonlat.split(",");
		
		String lon = lonlats[0];
		
		int ll = lon.indexOf(".");
		
		String first = lon.substring(0,ll);
		String last = lon.substring(ll+1);
		
	    String newString = first +"."+ (Integer.valueOf(last)+5);
	    
	    String newLonlat = newString+","+lonlats[1];
		
		System.out.println(newLonlat+"");
	}
}
