package com.list;

import java.util.LinkedList;

public class MLinkedList {

	
	public static void main(String[] args) {
		MLinkedList list = new MLinkedList();
		list.delete();
	}
	
	/**
	 * ģ��һ�������һ���߳���LinkedList�м�������
	 * ��һ���̴߳�LinkedList��ȡ������
	 * @author zealjiang
	 * @date 2016��6��1�� ����10:18:02
	 *
	 */
	int i = 0;
	public void delete(){
		final LinkedList<Integer> list = new LinkedList<Integer>();
		
		//��������
		new Thread(){
			public void run() {
				for (; i < 50; i++) {
					list.add(i);
					System.out.println("���룺 "+i);
				}
			};
		}.start();
		
		//ȡ������
		new Thread(){
			public void run() {
				while(i<50){
					if(list.size()>0){
						int num = list.remove();
						System.out.println("ȡ���� "+num);
					}
				}
			};
		}.start();
		

	}
}
