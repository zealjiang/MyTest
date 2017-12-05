package com.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import org.junit.Test;

public class MyList2Array {

	@Test
	public void list2Array(){
		List<String> list = new ArrayList<String>();
		
		list.add("n1");
		list.add("n2");
		list.add("n3");

		String[] arrays = new String[list.size()];
		list.toArray(arrays);
		
		for(int i=0;i<arrays.length;i++){
			System.out.println("arrays["+i+"]"+arrays[i]);
		}
	}
}
