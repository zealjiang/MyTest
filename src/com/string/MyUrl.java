package com.string;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

public class MyUrl {

	
	@Test
	public void uri(){
		
			URL url;
			try {
				url = new URL("http://tour.ishowchina.com/rest/order/submit?name=¿Ó÷æΩ≠&phone=18618269575&icno=130481198608046935&tickets=[{\"num\":\"1\",\"scenicid\":\"1\",\"icrowdkindid\":\"1\",\"tickettypeid\":\"590\",\"date\":\"2015-04-10\"}]&key=F0DDFFD1AF524130A2AF7A6723D71EB0&sign=ABCB49B4184DA04356ED5F36E06E4ABB25702889");
				try {
					URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
					System.out.println(uri.toString());
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(url.toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	}
}
