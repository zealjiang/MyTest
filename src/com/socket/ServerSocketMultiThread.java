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
		ServerSocket ss = new ServerSocket(5566); // ����һ��Socket������������5566�˿�
		int i = 0;
		// ������ѭ����ͣ�ļ����˿�
		while (true) {
			Socket s = ss.accept();// ����Socket��������accept()������ȡ�ͻ���Socket����
			i++;
			System.out.println("��" + i + "���ͻ��˳ɹ����ӣ�");
			Client c = ssmt.new Client(i, s); // �����ͻ��˴����̶߳���
			Thread t = new Thread(c); // �����ͻ��˴����߳�
			t.start(); // �����߳�
		}

	}

	// �ͻ��˴����߳���(ʵ��Runnable�ӿ�)
	class Client implements Runnable {
		int clientIndex = 0; // ����ͻ���id
		Socket s = null; // ����ͻ���Socket����

		Client(int i, Socket s) {
			clientIndex = i;
			this.s = s;
		}

		public void run() {
			// ��ӡ���ͻ�������
			DataInputStream dis = null;
			FileOutputStream fop = null;
	        //��������ݵĲ����ļ�
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
				//System.out.println("��" + clientIndex + "���ͻ��˷�����Ϣ��" + dis.readUTF());
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
