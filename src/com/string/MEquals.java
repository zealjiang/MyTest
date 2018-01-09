package com.string;

import org.junit.Test;

import com.sun.istack.internal.Nullable;

public class MEquals {
	
	@Test
	public void mEquals(){
		int i = 6;
		String j = "6";
		System.out.println("int 6 equals String 6 :");
		if(j.equals(i)){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		
		System.out.println("(String)int 6 equals String 6 :");
		if(j.equals(String.valueOf(i))){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}
	
	@Test
	public void isEmpty(){
		String seriesId = null;
        if(isEmpty(seriesId)||"0".equals(seriesId)){
        	System.out.println("请先选择车系");
        }else{
        	System.out.println("OK");
        }
	}
	
	@Test
	public void nullToEmpty(){
		System.out.println(empty(null));
		System.out.println("nullToEmpty");
		System.out.println("nullToEmpty".equals(null));
	}
	
    public static String empty(@Nullable String str) {
        return isEmpty(str) ? "" : (str.toLowerCase().equals("null") ? "" : str);
    }
	
    /**
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
    
    @Test
    public void Test(){
    	int status = -4;
        if(status==1){
        	System.out.println("1");
        }else if(status>=-5&&status<=-1){
        	System.out.println("a");
        }else{
        	System.out.println("b");
        }
    }
    
}
