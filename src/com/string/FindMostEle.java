/**
 * 2013-11-26
 * ����9:15:44
 */
package com.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 * @time 2013-11-26����9:15:44
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
			
/*			//����һ��
			String[] strArray = {"11", "11", "2", "2", "4", "5", "4"};
			HashMap<String, Integer> result = mostEle(strArray);
			ArrayList<Integer> c = new ArrayList<Integer>(result.values());
			Set<String> s = result.keySet();
			System.out.print("һ����"+ result.size() +"Ԫ����ࡣ���Ƿֱ���");
			System.out.print(s);
			System.out.println("���ֱ������"+ c.get(0) +"�Ρ�");*/
			//��������
			//int ar [] = {3,6,2,4,9,5,2,8,8,8,8,2};		
			int ar [] = {3,7,9,7,7,6,8,21,6,13,6};
			getMostAppearNumber(ar);
		}
		
		/**
		 * 2.	ʹ��Java/C/C++ʵ��һ�γ����ҳ������г��ִ���������֣���������ִ�����������㷨��ʱ�临�Ӷȡ�
			�ο����£�
			���룺
			C/C++: int arr[] = {3,7,9,7,7,6,8,21,6,13,6};
			Java:	int[] arr = {3,7,9,7,7,6,8,21,6,13,6};
			�����
			(7,3),(6,3)

		 * @param array
		 * @author: Administrator
		 * @time: 2013-11-26����1:57:52
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
			System.out.println("�����г��ִ�������������="+maxEntry.getKey()+";���ֵĴ�����:"+maxEntry.getValue());			
		}	
}
