package com.array;

import java.io.File;


public class ForBreak {

	public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {  
        	boolean tag = false;
        	for(int j = 0; j < 2; j++){
                if(i==1){
                	tag = true;
                	break;               	    
                } 
                System.out.println("i :"+i+" "+"j :"+j);
        	} 
            if(!tag)
            	System.out.println(i+"haha :");
        }
	}
	

}
