package com.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class HttpGetContentLength {

	@Test
	public void getLength(){
		
		try {
			String url = "http://tour.ishowchina.com/download/jiuzhaigou.apk";
			URL curl = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) curl.openConnection();
			httpURLConnection.setDoInput(true);
			//httpURLConnection.setRequestProperty("Range", "bytes=" + fileSize + "-" + (info.size - 1));
			//获取下载文件的size
			long totalSize = httpURLConnection.getContentLength();
			System.out.println("totalSize :"+totalSize);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
