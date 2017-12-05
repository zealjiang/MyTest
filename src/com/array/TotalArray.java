package com.array;

import java.util.ArrayList;

public class TotalArray {

	ArrayList<String> list = new ArrayList<String>();
	
	ArrayList<String> alist = new ArrayList<String>();
	
	ArrayList<String> blist = new ArrayList<String>();
	
	public static void main(String[] args) {
		TotalArray a = new TotalArray();
		a.createAList();
		a.createBList();
		a.createList();
		a.deleteList(0);
	}
	
	public void createAList(){
		alist.add("A1");
		alist.add("A2");
		alist.add("A3");
	}
	
	public void createBList(){
		blist.add("B1");
		blist.add("B2");
		blist.add("B3");
	}
	
	public void createList(){
/*		for (int i = 0; i < alist.size(); i++) {
			list.add(alist.get(i));			
		}
		for (int i = 0; i < blist.size(); i++) {
			list.add(blist.get(i));			
		}*/
		
		list.addAll(alist);
		list.addAll(blist);
	}
	
	public void deleteList(int position){
		list.remove(position);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("list "+i+" :"+list.get(i));
		}
		System.out.println("---------------------------");
		for (int i = 0; i < alist.size(); i++) {
			System.out.println("alist "+i+" :"+alist.get(i));
		}
		System.out.println("---------------------------");
		for (int i = 0; i < blist.size(); i++) {
			System.out.println("blist "+i+" :"+blist.get(i));
		}
	}
}
