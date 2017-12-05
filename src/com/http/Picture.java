package com.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class Picture {

	private String title;
	private String source;
	private String upPath;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUpPath() {
		return upPath;
	}
	public void setUpPath(String upPath) {
		this.upPath = upPath;
	}

    //检查连接的有效xìng
	@Test
    public void checkURL() {
    	String url = "http://image.jingzhengu.com/JietongdaImage/2016/10/25/95c709b7-515f-481d-a153-8e038a5ac04b_902.jpg";
        boolean value = false;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            if (code != 200) {
                value = false;
            } else {
                value = true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }finally {
             System.out.println(value);
        }
    }
	
}

