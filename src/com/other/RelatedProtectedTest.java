package com.other;

import com.acclimdecorate.ChocolateChip;

public class RelatedProtectedTest {

	public static void main(String[] args) {
		ChocolateChip cc = new ChocolateChip();
		cc.outCanAcc();
		
		System.out.println("==================");
		
		RelatedProtectedTest rpt = new RelatedProtectedTest();
		rpt.sub();
		System.out.println("==================");
		
	}
	
	private void add(){
		System.out.println("private");
	}
	
	private void sub(){
		System.out.println("subtract");
		add();
	}
}
