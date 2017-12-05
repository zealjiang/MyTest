package com.comparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 所有参数按照字段名的ascii码从小到大排序
 * @author zealjiang
 * @time 2016年1月28日下午4:34:05
 */
public class AscllSort {

	static HashMap<String,String> map = new HashMap<String,String>();
	public AscllSort() {
		
	}


	public static HashMap<String,String> makeFakeData(){
		map.put("state", "1111");
		map.put("page", "1111");
		map.put("pageSize", "1111");
		map.put("info", "1111");
		map.put("photo", "1111");
		
		return map;
	}
	
	public static HashMap<String,String> getSortedMap(HashMap<String,String> map){
		

		ArrayList<String> list = new ArrayList<String>();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			list.add(key);
			System.out.println(key);
		}

	     String temp;  
	     for(int i=0;i<list.size()-1;i++){  
	         for(int j=0;j<list.size()-1-i;j++){  
	        	
		         if(compare(list.get(j),list.get(j+1))==1){
		             temp=list.get(j);  
		             list.set(j, list.get(j+1));
		             list.set(j+1, temp);
		         }
	         }  
	     } 
	     
	     System.out.println("=================");
	     for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		 }
	     System.out.println("=================");
	     
	     LinkedHashMap<String,String> newMap = new LinkedHashMap<String,String>();
	     for (int i = 0; i < list.size(); i++) {
			String key = list.get(i);
			String value = map.get(key);
			newMap.put(key, value);
		 }
	     
	    System.out.println("--------------------");
		Iterator iter2 = newMap.entrySet().iterator();
		while (iter2.hasNext()) {
			Map.Entry entry = (Map.Entry) iter2.next();
			String key = (String) entry.getKey();
			System.out.println(key);
		}
	     
		return newMap;
	}
	
	public static void sort(){
		String[] a = new String[]{"state","page","pageSize","info","photo"};
	 
	     String temp;  
	     for(int i=0;i<a.length-1;i++){  
	         for(int j=0;j<a.length-1-i;j++){  
	        	
		         if(compare(a[j],a[j+1])==1){
		             temp=a[j];  
		             a[j]=a[j+1];  
		             a[j+1]=temp;
		         }
	         }  
	     } 
	     
	     for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
	public static int compare(String str1,String str2){
		
		int compare = 0;
		int length = 0;
		boolean boo = str1.length()>str2.length()? true:false;
		if(boo){
			length = str2.length();
		}else{
			length = str1.length();
		}
		for (int i = 0; i < length; i++) {
			int i1 = (int)str1.charAt(i);
			int i2 = (int)str2.charAt(i);
	         if(i1>i2){  
	        	 compare = 1;
	        	 break;
	         }else if(i1<i2){
	        	 compare = -1;
	        	 break; 
	         }else{
	        	 compare = 0;
	        	 continue;
	         }
		}
		if(compare==0){
			if(boo){
				compare = 1;
			}else{
				compare = -1;
			}
		}
		
		return compare;
	}
	
	//字符串转换为ascii
	  public static String StringToA(String content){
	    String result = "";
	    int max = content.length();
	    for (int i=0; i<max; i++){
	      char c = content.charAt(i);
	      int b = (int)c;
	      result = result + b;
	    }
	    return result;
	  }
	 
	  //ascii转换为string
	  public static String AToString(int i){
	    return Character.toString((char)i);
	  }
	  public static void main(String args[]){
	    //请在终端运行
/*	    System.out.println(StringToA("page"));
	    System.out.println(StringToA("pageSize"));
	    System.out.println(StringToA("state"));*/
	    //System.out.println(AToString(new Integer()));
	    
	    sort();
	    System.out.println("****************************");
		  
		getSortedMap(makeFakeData());
	  }
}
