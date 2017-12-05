package com.exception;


public class TryCatch {

	public static void main(String[] args) {
		TryCatch tc = new TryCatch();
		tc.tryCatch();
	}
	
	public void tryCatch(){
		try {
			int a = 3;
			if(a/(a-3)>0){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("after catch");
	}
}
