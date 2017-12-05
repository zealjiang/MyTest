package com.list;

import java.util.LinkedList;

public class MLinkedList {

	
	public static void main(String[] args) {
		MLinkedList list = new MLinkedList();
		list.delete();
	}
	
	/**
	 * 模拟一种情况，一个线程向LinkedList中加入数据
	 * 另一个线程从LinkedList中取出数据
	 * @author zealjiang
	 * @date 2016年6月1日 上午10:18:02
	 *
	 */
	int i = 0;
	public void delete(){
		final LinkedList<Integer> list = new LinkedList<Integer>();
		
		//加入数据
		new Thread(){
			public void run() {
				for (; i < 50; i++) {
					list.add(i);
					System.out.println("加入： "+i);
				}
			};
		}.start();
		
		//取出数据
		new Thread(){
			public void run() {
				while(i<50){
					if(list.size()>0){
						int num = list.remove();
						System.out.println("取出： "+num);
					}
				}
			};
		}.start();
		

	}
}
