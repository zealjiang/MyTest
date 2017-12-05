package com.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;


public class SplitByteArray {

	public static void main(String[] args) throws IOException {
		
		String str= "ģѹ�������ģѹե��ѹ���Ƿǵ�ʧܧ�����ģѹ" +
				"ե��ѹ���Ƿǵ�ʧ�����ģѹե��ѹ���Ƿǵ�ʧ�����ģѹե" +
				"��ѹ���Ƿǵ�ʧ�����ģѹե��ѹ���Ƿǵ�ʧ��" +
				"���ģѹե��ѹ���Ƿǵ�ʧ�����ģѹե��ѹ���Ƿǵ�ʧ�����ģѹե��ѹ���Ƿǵ�ʧ";
		
		int state;
		String strs = "��ѹ";
		byte[] bytes;
		bytes= arrayTobyte(str);
		byte[] test = new byte[8];
		
		byte[] tests = new byte[bytes.length];
		
		for(int k=0;k<bytes.length;k+=8){
			if((bytes.length-k)>8){
				state =8;
			}else{
				state =bytes.length-k;
			}
			for(int j=0;j<state;j++){
				test[j] = bytes[k+j];
			}
			
			
			for(int i=0;i<state;i++){
				tests[i+k]=test[i];
			}
			
			
			
		}
		String strss = byteToString(tests);
		System.out.println(strss);
		
	}
	
	public static byte[] arrayTobyte(String arrays) throws IOException{
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//urlConnection.getOutputStream()
		DataOutputStream dout = new DataOutputStream(baos);

		byte[] buffer = null;
		//ת����byte[]
	

		dout.writeUTF(arrays);


		return buffer = baos.toByteArray();
		
	}
	
	public  static String  byteToString(byte[] buffer){
		
		  String result = null ;
		  try {
		  ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
		  DataInputStream dis = new DataInputStream(bais);

				  result = dis.readUTF();


		  } catch (Exception e) {
		  e.printStackTrace();
		  }
		  
		  return result;
	}
	
	
}
