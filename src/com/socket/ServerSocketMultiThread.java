package com.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketMultiThread {

	public static void main(String[] args) throws Exception {

		ServerSocketMultiThread ssmt = new ServerSocketMultiThread();
		ServerSocket ss = new ServerSocket(5566); // 创建一个Socket服务器，监听5566端口
		int i = 0;
		// 利用死循环不停的监听端口
		while (true) {
			Socket s = ss.accept();// 利用Socket服务器的accept()方法获取客户端Socket对象。
			i++;
			System.out.println("第" + i + "个客户端成功连接！");
			Client c = ssmt.new Client(i, s); // 创建客户端处理线程对象
			Thread t = new Thread(c); // 创建客户端处理线程
			t.start(); // 启动线程
		}

	}

	// 客户端处理线程类(实现Runnable接口)
	class Client implements Runnable {
		int clientIndex = 0; // 保存客户端id
		Socket s = null; // 保存客户端Socket对象

		Client(int i, Socket s) {
			clientIndex = i;
			this.s = s;
		}

		public void run() {
			// 打印出客户端数据
			DataInputStream dis = null;
			FileOutputStream fop = null;
	        //保存包数据的测试文件
			File file = new File("d:/b.txt");
	        if (file.exists()) {
	            file.delete();
	        }

	        try {
	            fop = new FileOutputStream(file);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	       
	        
			try {
				dis = new DataInputStream(s.getInputStream());
				//System.out.println("第" + clientIndex + "个客户端发出消息：" + dis.readUTF());
				try {
					int n;
					byte[] buffer = new byte[1024];
				
					while (-1 != (n = dis.read(buffer))) {
						System.out.println("--read count: "+n);
						for (int i = 0; i < n; i++) {		
							fop.write((Integer.toHexString(buffer[i] & 0xFF) + " ").getBytes());
						}	
					}
				} finally {
					try {
						if (fop != null) {
							fop.close();
						}
					} catch (IOException ioe) {

					}
					System.out.println("read over");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				try {
					if(dis!=null){
						dis.close();
					}
					if(s!=null){
						s.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
