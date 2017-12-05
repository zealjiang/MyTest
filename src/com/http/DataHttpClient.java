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
					//定义服务器的url
					url = new URL("http://130.10.7.157:8080/httpServer/data_receive");
					con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("POST");
					con.setDoOutput(true);
					con.setDoInput(true);
					con.setConnectTimeout(10000);
					//设置一次向输出流中传输系统默认的字节流
					con.setChunkedStreamingMode(0);
					//用缓冲流来封装节点流
					BufferedOutputStream out = new BufferedOutputStream(con.getOutputStream());	
					//用对象流封装缓冲流
					ObjectOutputStream oos = new ObjectOutputStream(out);
/*					//ObjectOutputStream 不成同时发送多个对象，如果同时发送多个对象，服务器端也只能接受到最先发送的对象
					//发送一个String对象到对象流中
					oos.writeObject(new String("comes from client"));*/
					File file = new File("e:/fengjing.jpg");
					FileInputStream fis = new FileInputStream(file);
					
/*					//列出文件的大小和名字
 * 					File[] files = file.listFiles();
					for(int i=0; i<files.length;i++){
						if(files[i].isFile()){
							System.out.println(files[i].getName()+"  "+files[i].length());
						}else{
							System.out.println(files[i].getName());
						}					
					}*/
									 	
					//将一个图片文件读到一个byte[]数组中
					int total = fis.available();
					byte[] b = new byte[total];
					fis.read(b);
//					System.out.println(b.length);
/*					//将图片文件写到输出流中
					FileOutputStream fos = new FileOutputStream("e:/22.jpg");
					fos.write(b);*/
					Picture pic = new Picture();
					pic.setSize(total);
					pic.setName(file.getName());
					pic.setPath(file.getAbsolutePath());
					//根据图片的后缀名来获取图片的类型
					String[] strArray = file.getName().split("\\.");
					String postfix = strArray[strArray.length-1];
					//设置图片的类型
					pic.setType(postfix);
					//将图片的字节写入对象中
					pic.setContent(b);
					oos.writeObject(pic);
					oos.flush();
					System.out.println("上传的图片总大小是 ："+total+"字节");
					System.out.println("上传的图片名称是 ："+pic.getName());
					System.out.println("上传的图片的路径是 ："+pic.getPath());
					System.out.println("上传的图片类型是 ："+pic.getType());
					//根据图片的大小来显示不同的名字
					DecimalFormat df =new DecimalFormat("#.##");
					if(total<1024){
						System.out.println("图片的大小是："+df.format(total)+"bytes");
					}else if((total>1024)&&(total<1024*1024)){
						float k = (float) (total/1024.00);
						System.out.println("图片的大小是："+df.format(k)+"K");
					}else if((total>1024*1024)&&(total<1024*1024*1024)){
						float m = (float) (total/(1024.00*1024.00));
						System.out.println("图片的大小是："+df.format(m)+"M");
					}
					
					
					System.out.println("消息已发送");
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
