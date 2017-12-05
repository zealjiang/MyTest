package com.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class myList {

	static List list;
	public static void main(String[] args) {
		
		list = new ArrayList();
		
		list.add("1");
		list.add("2");
		list.add("3");
		//list.clear();
		System.out.println("list size() :"+list.size()+" top :"+getTopItem());
		list.remove(list.size()-1);
		
		
	}
	public static String getTopItem(){
		if(list.size()>0){
			String item = (String)list.get(0);
			return item;
		}else{
			return "no item";
		}	
	}
	
	/**
	 * 
	 * 替换ArrayList中与指定元素相同的最后一个元素并替换为设制的元素值
	 * @author: zj
	 * @time: 2013-11-21上午10:20:52
	 */
	@Test
	public void whichReturn(){
		ArrayList list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("1");
		
		System.out.println("原数据：------");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}

		if(list.lastIndexOf("1")!=-1){
			list.set(list.lastIndexOf("1"),"5");
		}
		
		System.out.println("替换1后的数据：------");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void isAddSameObject(){
		ArrayList list = new ArrayList();
		list.add("1");
		list.add("1");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
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
	
	
	@Test
	public void deleteTheGet(){
		ArrayList list = new ArrayList();
		list.add("tiger");
		list.add("fish");
		
		System.out.println(list.get(0));
		list.remove(0);
		System.out.println(list.get(0));
		list.remove(0);
		
	}
	
	/**
	 * 删除list中指定位置之前的所有数据(位置从0开始)
	 * @author zealjiang
	 * @date 2016年5月13日 上午10:47:14
	 *
	 */
	@Test
	public void deleteThePosBefore(){
		ArrayList list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		
		System.out.println("--------删除之前 list中的数据---------");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i+" :"+list.get(i));
		}
		//删除第4个之前的所有数据(位置从0开始)
		for (int i = 0; i < 4; i++) {
			list.remove(0);
		}
		
		System.out.println("--------删除之后 list中的数据---------");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i+" :"+list.get(i));
		}
		
	}
	
	/**
	 * 删除list中指定位置之前的所有数据(位置从0开始)
	 * @author zealjiang
	 * @date 2016年5月13日 上午10:47:14
	 *
	 */
	@Test
	public void deleteThePosBefore2(){
		ArrayList list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		
		System.out.println("--------删除之前 list中的数据---------");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i+" :"+list.get(i));
		}
		//删除第4个之前的所有数据(位置从0开始)
		deleteThePosBefore(list,4);
		
		System.out.println("--------删除之后 list中的数据---------");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i+" :"+list.get(i));
		}
		
	}
	
	
	/**
	 * 删除list中指定位置之前的所有数据(位置从0开始)
	 * @author zealjiang
	 * @date 2016年5月13日 上午11:02:05
	 * @param list
	 * @param pos
	 * @return
	 *
	 */
	public boolean deleteThePosBefore(ArrayList list,int pos){
		if(list==null){
			System.out.println("deleteThePosBefore(): 传入的list为null");
			return false;
		}
		if(pos<1){
			System.out.println("deleteThePosBefore(): pos<1");
			return false;
		}
		int size = list.size();
		if(pos>=size){
			System.out.println("deleteThePosBefore(): pos>=size");
			return false;
		}
		
		//删除第pos个之前的所有数据(位置从0开始)
		for (int i = 0; i < pos; i++) {
			list.remove(0);
		}	
		return true;
	}
	
	@Test
	public void testEmptySplit(){
		String stest = "";
        String[] poss = stest.split(",");
        //System.out.println(poss);
        for (int i = 0; i < poss.length; i++) {
        	System.out.println(i+" "+poss[i]);
		}
	}
	
	@Test
	public void testOneSplit(){
		String stest = "$1$";
        String[] poss = stest.split(",");
        //System.out.println(poss);
        for (int i = 0; i < poss.length; i++) {
        	System.out.println(i+" "+poss[i]);
		}
	}
}
