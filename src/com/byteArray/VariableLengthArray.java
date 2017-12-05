package com.byteArray;

import java.util.ArrayList;

public class VariableLengthArray {

    byte a[];
    ArrayList<Byte> arrayList = new ArrayList<Byte>();
	
	public static void main(String[] args) {
		VariableLengthArray v = new VariableLengthArray();
		//v.m1();
		v.m2();
	}
	
	private void m1(){
		a[0] = 1;
		a[1] = 2;
		
		for (int i = 0; i < a.length; i++) {
			System.out.println("i: "+a[i]);
		}
	}
	
	private void m2(){
		arrayList.add((byte)0x97);
		arrayList.add((byte)0x43);
		arrayList.add((byte)0x5A);
		
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println("i: "+arrayList.get(i));
		}
	}
}
