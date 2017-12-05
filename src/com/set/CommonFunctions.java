package com.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * @author zealjiang
 * @2013-12-19ионГ10:36:44
 */
public class CommonFunctions {

	@Test
	public void canSaveSameObject(){
		Set set = new HashSet();
		set.add("1");
		set.add("1");
		Iterator it;
		for(it = set.iterator();it.hasNext();){
			System.out.println(it.next());
		}
		
	}
}
