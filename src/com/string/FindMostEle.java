/**
 * 2013-11-26
 * 上午9:15:44
 */
package com.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 * @time 2013-11-26上午9:15:44
 */
public class FindMostEle {
		private static HashMap<String, Integer> map;
		public static HashMap<String, Integer> mostEle(String[] strArray){
			map = new HashMap<String, Integer>();
			String str = "";
			int count = 0;
			int result = 0;
			for(int i=0; i<strArray.length; i++)
			str += strArray[i];
			System.out.println("str-->"+str);
			for(int i=0; i<strArray.length; i++){
				String temp = str.replaceAll(strArray[i], "");
				System.out.println("temp-->"+temp);
				count = (str.length() - temp.length())/strArray[i].length();
				if (count > result){
					map.clear();
					map.put(strArray[i], count);
					result = count;
				}
				else if(count == result)
					map.put(strArray[i], count);
			}
			return map;
		}
		public static void main(String args[]){
			
/*			//方法一：
			String[] strArray = {"11", "11", "2", "2", "4", "5", "4"};
			HashMap<String, Integer> result = mostEle(strArray);
			ArrayList<Integer> c = new ArrayList<Integer>(result.values());
			Set<String> s = result.keySet();
			System.out.print("一共有"+ result.size() +"元素最多。它们分别是");
			System.out.print(s);
			System.out.println("，分别出现了"+ c.get(0) +"次。");*/
			//方法二：
			//int ar [] = {3,6,2,4,9,5,2,8,8,8,8,2};		
			int ar [] = {3,7,9,7,7,6,8,21,6,13,6};
			getMostAppearNumber(ar);
		}
		
		/**
		 * 2.	使用Java/C/C++实现一段程序，找出数组中出现次数最多数字，并输出出现次数。请标明算法的时间复杂度。
			参考如下：
			输入：
			C/C++: int arr[] = {3,7,9,7,7,6,8,21,6,13,6};
			Java:	int[] arr = {3,7,9,7,7,6,8,21,6,13,6};
			输出：
			(7,3),(6,3)

		 * @param array
		 * @author: Administrator
		 * @time: 2013-11-26下午1:57:52
		 */
		public static void getMostAppearNumber(int [] array){		
			LinkedHashMap<Integer,Integer> map = new LinkedHashMap<Integer,Integer>();
			int time = 0;
			Map.Entry<Integer,Integer> maxEntry =  null;
			
			for(int i=0;i<array.length;i++){
				if(map.get(array[i])  == null){
					map.put(array[i],1);
				}else{
					map.put(array[i],1+(Integer)map.get(array[i]));
				}			
			}		
			
			LinkedHashMap<Integer,Integer> map2 = new LinkedHashMap<Integer,Integer>();
			for(Map.Entry<Integer,Integer> entry:map.entrySet()){		
				if(entry.getValue()>time){
					time = entry.getValue();
					maxEntry = entry;
					map2.clear();
					map2.put(entry.getKey(), entry.getValue());
				}else if(entry.getValue() == time){
					time = entry.getValue();
					maxEntry = entry;
					map2.put(entry.getKey(), entry.getValue());
				}			
			}
			StringBuilder sb = new StringBuilder();
			for(Map.Entry<Integer,Integer> entry:map2.entrySet()){				
				sb.append("("+entry.getKey()+","+entry.getValue()+")"+",");				
			}
			String str = sb.substring(0, sb.length()-1).toString();
			System.out.println(str);
			System.out.println("数组中出现次数最多的数字是="+maxEntry.getKey()+";出现的次数是:"+maxEntry.getValue());			
		}	
}
