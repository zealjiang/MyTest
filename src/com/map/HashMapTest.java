package com.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;


public class HashMapTest {

	 
	 @Test
	 public void put(){
		 HashMap<Integer, List<String>> poiMap = new HashMap<Integer, List<String>>();
		 
		 List<String> list = new ArrayList<String>();
		 System.out.println(list.size());
		 String s0 = list.get(0);
		 list.add("����");
		 list.set(0, "����");
		 poiMap.put(0, list);
	 }
	 
	 /**
	  * ����hashmap���ܷ����ͬ��key
	  */
	 @Test
	 public void putSameKey(){
		 HashMap<String, String> poiMap = new HashMap<String, String>();
		 poiMap.put("10", "10");
		 poiMap.put("20", "20");
		 poiMap.put("30", "30");
		 poiMap.put("10", "010");
		 
		 Iterator iter = poiMap.entrySet().iterator();
		 while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			String val = (String) entry.getValue();
			System.out.println("key: "+key+"  value: "+val);
		 }
	 }
	 
	 
	 @Test
	 public void testEqual(){
		 
		 class MyName{
			 private String name;

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public MyName(String name) {
				super();
				this.name = name;
			}
			 
			
		 }
		 
		 HashMap<String, MyName> poiMap = new HashMap<String, MyName>();
		 poiMap.put("10", new MyName("����"));
		 poiMap.put("20", new MyName("��Ʊ"));
		 poiMap.put("30", new MyName("����"));
		 
		 ArrayList<MyName> list = new ArrayList<MyName>();


		 Iterator iter = poiMap.entrySet().iterator();
		 while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			MyName val = (MyName) entry.getValue();
			list.add(val);
		 }
		 
		 for (int i = 0; i < poiMap.size(); i++) {
			System.out.println(i+" :"+list.get(i).getName());
		 }
		 System.out.println("--------------------");
		 poiMap.get("10").setName("�޸��˻���");
		 for (int i = 0; i < poiMap.size(); i++) {
			System.out.println(i+" :"+list.get(i).getName());
		 }
	 }
	 
}
