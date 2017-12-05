package com.math;

import org.junit.Test;

public class ChangeLontitude {

	/**
	 * 将格式为"222.33333,113.455322"的经纬度中的逗号前的数字加小数点位数5后，返回新的经纬度
	 * author zealjiang
	 * time 2014年9月11日 下午4:52:55
	 * note:  例如：传入是的222.33333,113.455322，返回的是222.33338,113.455322
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
