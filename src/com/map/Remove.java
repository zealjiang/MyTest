package com.map;

import java.util.HashMap;

public class Remove {

	public static  HashMap<Integer,Integer> position_state = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) {
		//Ìí¼Ó
		add(position_state,0,0);
		add(position_state,1,1);
		add(position_state,2,2);
		add(position_state,3,3);
	
		for(int i=0;i<position_state.size();i++){
			System.out.println(position_state.get(i));
		}
		System.out.println("---------------------------------------------");
		//ÐÞ¸Ä
		alter(position_state,1,4);
		for(int i=0;i<position_state.size();i++){
			System.out.println(position_state.get(i));
		}
		System.out.println("---------------------------------------------");
		//É¾³ý
		remove(position_state,1);
		System.out.println("---------------------------------------------");
		for(int i=0;i<position_state.size();i++){
			System.out.println(position_state.get(i));
		}
	}
	
	public static void remove(HashMap<Integer,Integer> hm,int i){
		
		hm.remove(i);
	}
	
	public static void add(HashMap<Integer,Integer> hm,int key,int value){
		hm.put(key, value);
	}
	
	public static void alter(HashMap<Integer,Integer> hm,int key,int value){
		hm.put(key, value);
	}
}
