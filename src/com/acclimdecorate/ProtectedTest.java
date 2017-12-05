package com.acclimdecorate;

public class ProtectedTest {

	public static void main(String[] args) {
		ChocolateChip cc = new ChocolateChip();
		cc.bite();
		
		System.out.println("======================");
		Cookie c = new Cookie();
		c.bite();
	}
}
