package com.array;

import java.util.ArrayList;

public class ArrayListPool {
	
    private ArrayList<Packet> mListBasket = new ArrayList<Packet>();
    
    public static void main(String[] args) {
    	ArrayListPool alp = new ArrayListPool();
    	
    	alp.getData();
    	
    	alp.setData();
	}
    
    public void getData(){
    	new Thread(){
    		@Override
    		public void run() {
    	    	while(true){	    
    	    		try {
						Thread.sleep(200);
						System.out.println("mListBasket.size(): "+mListBasket.size());
						if(mListBasket.size()>0){
							
							Packet op = mListBasket.get(0);
						    mListBasket.remove(0);
						    //if(op==null){
						        System.out.println("get op: "+op);
						    //}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
    	    	}
    		}
    	}.start();

    	
    }
    
    public void setData(){
    	new Thread(){
    		@Override
    		public void run() {
		    	for (int i = 0; i < 100; i++) {
		    		
		    		try {
						Thread.sleep(100);
						Packet packet = new Packet();
						mListBasket.add(packet);		
						System.out.println("i: "+i);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
    	
    		}
    	}.start();

    }
}
