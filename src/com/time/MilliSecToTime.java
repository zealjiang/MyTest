package com.time;

public class MilliSecToTime {

	public static void main(String[] args) {
		MilliSecToTime mst = new MilliSecToTime();
		mst.millSecToTime();
		System.out.println("Java\u5e73\u53f0\u5b9e\u73b0\u56fe\u7247\u7b49\u6bd4\u4f8b\u7f29\u7565\u56fe\u751f\u6210");
	}
	
	private void millSecToTime(){
		System.out.println(System.currentTimeMillis());
		System.out.println(new java.util.Date(System.currentTimeMillis()));
		System.out.println(new java.util.Date(Long.valueOf("1763650693000")));
		System.out.println(new java.util.Date(Long.valueOf("1448140871000")));//1468779553
		System.out.println(new java.util.Date(Long.valueOf("1468779553000")));
	}
}
