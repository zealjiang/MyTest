package com.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import com.entity.Picture;


public class DataHttpClient {


	public static void main(String[] args) {
		DataHttpClient dht = new DataHttpClient();
		dht.send();
		
	}

	public void send() {
		new Thread(){
			@Override
			public void run() {
				HttpURLConnection con = null;
				DataInputStream dis = null;
				// TODO Auto-generated method stub
				URL url = null;
				try {
					//�����������url
					url = new URL("http://130.10.7.157:8080/httpServer/data_receive");
					con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("POST");
					con.setDoOutput(true);
					con.setDoInput(true);
					con.setConnectTimeout(10000);
					//����һ����������д���ϵͳĬ�ϵ��ֽ���
					con.setChunkedStreamingMode(0);
					//�û���������װ�ڵ���
					BufferedOutputStream out = new BufferedOutputStream(con.getOutputStream());	
					//�ö�������װ������
					ObjectOutputStream oos = new ObjectOutputStream(out);
/*					//ObjectOutputStream ����ͬʱ���Ͷ���������ͬʱ���Ͷ�����󣬷�������Ҳֻ�ܽ��ܵ����ȷ��͵Ķ���
					//����һ��String���󵽶�������
					oos.writeObject(new String("comes from client"));*/
					File file = new File("e:/fengjing.jpg");
					FileInputStream fis = new FileInputStream(file);
					
/*					//�г��ļ��Ĵ�С������
 * 					File[] files = file.listFiles();
					for(int i=0; i<files.length;i++){
						if(files[i].isFile()){
							System.out.println(files[i].getName()+"  "+files[i].length());
						}else{
							System.out.println(files[i].getName());
						}					
					}*/
									 	
					//��һ��ͼƬ�ļ�����һ��byte[]������
					int total = fis.available();
					byte[] b = new byte[total];
					fis.read(b);
//					System.out.println(b.length);
/*					//��ͼƬ�ļ�д���������
					FileOutputStream fos = new FileOutputStream("e:/22.jpg");
					fos.write(b);*/
					Picture pic = new Picture();
					pic.setSize(total);
					pic.setName(file.getName());
					pic.setPath(file.getAbsolutePath());
					//����ͼƬ�ĺ�׺������ȡͼƬ������
					String[] strArray = file.getName().split("\\.");
					String postfix = strArray[strArray.length-1];
					//����ͼƬ������
					pic.setType(postfix);
					//��ͼƬ���ֽ�д�������
					pic.setContent(b);
					oos.writeObject(pic);
					oos.flush();
					System.out.println("�ϴ���ͼƬ�ܴ�С�� ��"+total+"�ֽ�");
					System.out.println("�ϴ���ͼƬ������ ��"+pic.getName());
					System.out.println("�ϴ���ͼƬ��·���� ��"+pic.getPath());
					System.out.println("�ϴ���ͼƬ������ ��"+pic.getType());
					//����ͼƬ�Ĵ�С����ʾ��ͬ������
					DecimalFormat df =new DecimalFormat("#.##");
					if(total<1024){
						System.out.println("ͼƬ�Ĵ�С�ǣ�"+df.format(total)+"bytes");
					}else if((total>1024)&&(total<1024*1024)){
						float k = (float) (total/1024.00);
						System.out.println("ͼƬ�Ĵ�С�ǣ�"+df.format(k)+"K");
					}else if((total>1024*1024)&&(total<1024*1024*1024)){
						float m = (float) (total/(1024.00*1024.00));
						System.out.println("ͼƬ�Ĵ�С�ǣ�"+df.format(m)+"M");
					}
					
					
					System.out.println("��Ϣ�ѷ���");
					if(con!=null){
						if(con.getInputStream()!=null){
							BufferedInputStream in = new BufferedInputStream(con.getInputStream());
							dis = new DataInputStream(in);
							String str = dis.readUTF();
							System.out.println(str);
						}	
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try{
					if(dis !=null){
						dis.close();
					}
					if (con != null)
						con.disconnect();
					}catch(Exception e){e.printStackTrace();}
				}
			}
		}.run();
	}
}
