package com.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGetUrlContent {

	public static void main(String[] args) {

/*		String url = "http://s0.hao123img.com/res/r/image/2013-09-16/";

		HttpGetUrlContent h = new HttpGetUrlContent();
		h.getUrlContent(url);*/
		
/*		String[] suffix;
		suffix = "www.zj.com.cn.".split("cn");
		for(int i=0;i<suffix.length;i++){
			System.out.println(suffix[i]);
		}*/
	}
	
	public void getUrlContent(String url){
		
		try {
			File urlContentString = new File("d:/123"+".png");
			FileOutputStream fos = new FileOutputStream(urlContentString);
			
			URL murl = new URL(url);
			HttpURLConnection hConn = (HttpURLConnection)murl.openConnection();
			InputStream in = hConn.getInputStream();
			
			byte[] buffer = new byte[1024];
			int n = 0;
			while((n = in.read(buffer))!= -1){
				fos.write(buffer,0,n);
				fos.flush();
			}
			in.close();
			fos.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据网路路径获取 页面源码
	 * 
	 * @param pageUrl
	 * @param encoding
	 * @return
	 */
	public void getPageSource(String pageUrl, String encoding) {
		
		
		File urlContentString = new File("123"+".html");
		
		try {
			boolean boo = urlContentString.createNewFile();
			if(!boo){
				urlContentString.delete();
				getPageSource(pageUrl,encoding);
				return;
			}
			
			FileOutputStream fos = new FileOutputStream(urlContentString);
			OutputStreamWriter osw = new OutputStreamWriter(fos,encoding);
	        BufferedWriter out = new BufferedWriter(osw);


			// 构建一URL对象
			URL url = new URL(pageUrl);
			// 使用openStream得到一输入流并由此构造一个BufferedReader对象
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream(), encoding));
			String line;
			// 读取www资源
			while ((line = in.readLine()) != null) {
				out.write(line+"\n");
				out.flush();
			}
			in.close();
			out.close();
			osw.close();
			fos.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

}
