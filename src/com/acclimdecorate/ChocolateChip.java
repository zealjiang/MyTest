package com.acclimdecorate;

public class ChocolateChip extends Cookie{

	public ChocolateChip(){
		
		System.out.println("ChocolateChip");
	}
	
	public void outCanAcc(){
		System.out.println("com.acclimdecorate.ChocolateChip");
	}
	public static void main(String[] args) {
		ChocolateChip cc = new ChocolateChip();
		cc.bite();
	}
	
	private void getAChoco(){
		new ChocolateChip();
	}
}
