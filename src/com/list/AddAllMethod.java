package com.list;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AddAllMethod {

	
	@Test
	public void addAll(){
		
		List s1 = new ArrayList();
		s1.add("1");
		s1.add("2");
		s1.add("3");
		
		List s = new ArrayList();
		
		for(int i=0;i<s.size();i++){
			System.out.println(s.get(i));
		}
		System.out.println("-------------------");
		s.addAll(s1);
		for(int i=0;i<s.size();i++){
			System.out.println(s.get(i));
		}
		
		
		List s3 = new ArrayList();

		System.out.println("-------------------");
		s.addAll(s3);
		for(int i=0;i<s.size();i++){
			System.out.println(s.get(i));
		}
		
		
		List s2 = new ArrayList();
		s2.add("4");
		s2.add("5");
		s2.add("6");
		
		
		System.out.println("-------------------");
		s.addAll(s2);
		for(int i=0;i<s.size();i++){
			System.out.println(s.get(i));
		}
				
	}
}
