package com.array;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by zealjiang on 2016/3/25 14:43.
 */
public class PacketQueue {
	
    /**链表的最大长度，如果超过此长度，删除最旧的数据*/
    private int maxSize = 500;
    
	public static void main(String[] args) {
		PacketQueue mPacketQueue = PacketQueue.getInstance();

		mPacketQueue.put("a");
		mPacketQueue.put("i");
		mPacketQueue.put("l");
		System.out.println(mPacketQueue.get());
		mPacketQueue.put("i");
		System.out.println(mPacketQueue.get());
		mPacketQueue.put("u");
		mPacketQueue.put("g");
		mPacketQueue.put("l");
		mPacketQueue.printQueue();
	}


    private PacketQueue() {
    }

    private static PacketQueue packetQueue = null;
    //private static LinkedList l1 = null;

    //静态工厂方法
    public static PacketQueue getInstance() {
        if (packetQueue == null) {
            packetQueue = new PacketQueue();
            //l1 = new LinkedList();
        }
        return packetQueue;
    }


    private LinkedList ll = new LinkedList();

    public void put(Object o) {
        //如果队列长度超过了最大长度就删除队头元素
        //System.out.println("size: "+ll.size());
        if(ll.size()>=maxSize){
            ll.removeFirst();
        }
    	
        ll.add(o);
    }

    public Object get() {
        return ll.removeFirst();
    }

    public boolean empty() {
        return ll.isEmpty();
    }

    public void printQueue() {
        for (int i = 0; i < ll.size(); i++) {
            System.out.println(ll.get(i));
        }
    }
    public void printQueue2() {
        for (int i = 0; i < ll.size(); i++) {
            System.out.println(((ArrayList<String>)ll.get(i)).get(0));
        }
    }
}
