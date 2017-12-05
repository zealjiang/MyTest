package com.http;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.Test;

public class InetAddressTest {


    
	@Test
	public void getByName(){
		try {
			InetAddress add = InetAddress.getByName("propserv.ishowchina.com");
			InetAddress add2 = InetAddress.getByName("www.baidu.com");
			System.out.println(add);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
