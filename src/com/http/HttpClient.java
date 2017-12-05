package com.http;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.entity.Picture;

public class HttpClient {

	ObjectOutputStream objOutput = null;
	DataInputStream dis = null;

	public static void main(String[] args) {
		HttpClient ht = new HttpClient();
		ht.send();
	}

	public void send() {
		Runnable httpRunnable = new Runnable() {

			@Override
			public void run() {
				HttpURLConnection con = null;
				// TODO Auto-generated method stub
				try {
					URL url = new URL("http://130.10.7.157:8080/httpServer/receive");
					con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("POST");
					con.setDoOutput(true);
					con.setDoInput(true);
					con.setConnectTimeout(10000);
					System.out.println("...");
//					// 连接到服务器
					con.connect();
					con.getOutputStream().write("this is a string".getBytes());
					con.getOutputStream().flush();

					byte[] bytes = new byte[1024 * 100];
					int temp = 0;
					while ((temp = con.getInputStream().read(bytes)) > 0) {
						System.out.println(new String(bytes, 0, temp));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (con != null)
						con.disconnect();
				}
			}
		};
		httpRunnable.run();
	}
}
