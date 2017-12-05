package com.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;


public class SplitByteArray {

	public static void main(String[] args) throws IOException {
		
		String str= "模压黄柳大规模压榨田压根是非得失堙柳大规模压" +
				"榨田压根是非得失柳大规模压榨田压根是非得失柳大规模压榨" +
				"田压根是非得失柳大规模压榨田压根是非得失柳" +
				"大规模压榨田压根是非得失柳大规模压榨田压根是非得失柳大规模压榨田压根是非得失";
		
		int state;
		String strs = "田压";
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
		//转换成byte[]
	

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
