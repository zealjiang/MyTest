package com.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;


public class ConcurrentHashMapTest {

	private static ConcurrentHashMap<String, String> mActiveRequestsMap = new ConcurrentHashMap<String, String>();
	public static void main(String[] args) {
		if (mActiveRequestsMap.putIfAbsent("1", "1") == null) {
			System.out.println("----");
		}
	}
	
	@Test
	public void test(){
		HashMap map = new HashMap<String,String>();
		   if (!map.containsKey("1"))
		       System.out.println(map.put("1", "1"));
		   else
			   System.out.println(map.get("1"));
	}
}
