/**
 * 2013-11-26
 * 下午1:12:33
 */
package com.array;

import java.util.ArrayList;

/**
 * @author Administrator
 * @time 2013-11-26下午1:12:33
 */
public class QuickSort {

	public static int partition(int[] arr,int i,int j){
		
		int base = arr[i];
		while(i<j){
			while(i<j&&arr[j]>=base)
				j--;
			if(i<j){
				arr[i]=arr[j];
				i++;
			}
			while(i<j&&arr[i]<=base)
				i++;
			if(i<j){
				arr[j]=arr[i];
				j--;
			}
		}
		arr[i] = base;
		return i;
	}
	
	public static void quickSort(int[] arr,int low,int high){
		int p;
		if(low<high){
			p=partition(arr,low,high);
			quickSort(arr,low,p-1);
			quickSort(arr,p+1,high);
		}
			
	}
	
	/**
	 * 1.	使用Java/C/C++写一段程序，找出数组中第k大小的数，输出数所在的位置。请标明算法的时间复杂度。
		参考如下：
		输入：
		C/C++: int arr[] = {3, 7, 9, 21, 13};
		Java:	int[] arr = {3, 7, 9, 21, 13};
		找出第3大的数，输出：
		9,3

	 * @param args
	 * @param assigned
	 * @author: Administrator
	 * @time: 2013-11-26下午1:57:31
	 */
	public static void getAssignedBigNum(int[] args,int assigned){
		int[] oldArgs = args.clone();
		quickSort(args,0,args.length-1);
		for(int i=0;i<oldArgs.length;i++){
			if(args[assigned-1]==oldArgs[i]){
				System.out.println(args[assigned-1]+","+(i+1));
			}
				
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {3, 7, 9, 21, 13};
		//getAssignedBigNum(arr,3);
		
/*		PacketQueue mPacketQueue = PacketQueue.getInstance();
		mPacketQueue.put("a");
		mPacketQueue.put("i");
		mPacketQueue.put("l");
		System.out.println(mPacketQueue.get());
		mPacketQueue.put("i");
		System.out.println(mPacketQueue.get());
		mPacketQueue.put("u");
		mPacketQueue.put("g");
		mPacketQueue.put("l");*/
		
		ArrayList<String> list = new ArrayList<String>();
		PacketQueue mPacketQueue = PacketQueue.getInstance();
		list.add("1");
		mPacketQueue.put(list.clone());
		list.clear();
		list.add("2");
		mPacketQueue.put(list.clone());
		list.clear();
		list.add("3");
		mPacketQueue.put(list.clone());
		
		new Test().testPacketQueue();
	}
	
}
