/**
 * 2013-10-16
 * 下午1:12:01
 */
package com.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

/**
 * @author Administrator
 * @time 2013-10-16下午1:12:01
 */
public class GetTime {

	@Test
	public void getTime(){
		String dateString = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss",
				Locale.CHINA);
		dateString = formatter2.format(new java.util.Date());
		System.out.println(dateString);
		
	}
	
	public String getTime2(){
		String dateString = "";
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss",
				Locale.CHINA);
		return dateString = formatter2.format(new java.util.Date());
		//System.out.println(dateString);	
	}
	
	@Test
    public void setClock(){
        String time = getTime2();
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < 7; i++) {
        	String shex = Integer.toHexString(Integer.valueOf(time.substring(2 * i, 2 * (i + 1))));
        	String shex2 = shex.length()==2 ? shex:"0"+shex;
            sb.append(shex2+",");
        }
        sb.deleteCharAt(sb.length()-1);

        System.out.println("time: "+ sb.toString());
    }
	
	@Test
    public void setClock2(){
        String time = getTime2();
        StringBuilder sb  = new StringBuilder();
        String syear = Integer.toHexString(Integer.valueOf(time.substring(0, 4)));
    	if(syear.length()==3){   		
    		sb.append("0"+syear.charAt(0)+","+syear.substring(1)+",");
    	}
        for (int i = 2; i < 7; i++) {
        	String shex = Integer.toHexString(Integer.valueOf(time.substring(2 * i, 2 * (i + 1))));
        	String shex2 = shex.length()==2 ? shex:"0"+shex;
            sb.append(shex2+",");
        }
        sb.deleteCharAt(sb.length()-1);

        System.out.println("time: "+ sb.toString());
    }
	
	@Test
	public void  parseTime2(){		  
			try {
				SimpleDateFormat  formatter=new SimpleDateFormat("yyyy-M-d");
				Date d =  formatter.parse("2013-10-02");

				SimpleDateFormat  sdf1=new SimpleDateFormat("yyyy-M-d");
				String r_data = sdf1.format(d);
				System.out.println(r_data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	
	
	@Test
	public void  parseTime3(){		
		
		int leftTime = 9918171;
		
		int hour = leftTime / 3600;
		
		int minutes = leftTime % 3600 / 60;
		
		int seconds = leftTime % 3600 % 60;
		 
		StringBuilder sb = new StringBuilder();
		
		if(hour!=0)
			sb.append(hour+"小时");
		
		if(minutes!=0)
			sb.append(minutes+"分钟");
		
		if(seconds!=0)
			sb.append(seconds+"秒");
		
		System.out.println(sb.toString()); 

	  }
}
