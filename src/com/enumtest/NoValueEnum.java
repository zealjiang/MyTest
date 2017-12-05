package com.enumtest;

import java.util.ArrayList;

public class NoValueEnum {

    /**������ʾ8������ */
    public enum WAVE {
        ECGI, ECGII, ECGC1, ECGC2,ECGC3,ECGC4,ECGC5,ECGC6
    }
    
	public static void main(String[] args) {
		NoValueEnum ne = new NoValueEnum();
		ne.test();
	}
	
	private void test(){
		ArrayList<WAVE> list = new ArrayList<WAVE>();
		list.add(WAVE.ECGC1);
		list.add(WAVE.ECGC3);
		list.add(WAVE.ECGC5);
		
		System.out.println("����WAVE.ECGI��"+list.contains(WAVE.ECGI));
		System.out.println("����WAVE.ECGC3��"+list.contains(WAVE.ECGC3));
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i+"   "+list.get(i).equals(WAVE.ECGC1));
		}
		
		System.out.println(WAVE.values()[0]);
	}
}
