package com.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class ByteArray {

	
	private static Key  key=null;
	private static IvParameterSpec	 iv=null;
	private static	Cipher cipher=null;
	
	
	public static void main(String[] args) throws Exception {
		
		String[][] arrays = new String[2][2];
		arrays[0][0] = "���Ҫ";
		arrays[0][1] = "������";
		arrays[1][0] = "���ش�";
		arrays[1][1] = "��׼";
		
		String key = "@#abcdef";
		String iv = "@1abcdef";
		ByteArray   des   =   new   ByteArray();
        des.init(key, iv);
		
		//String[][]ת����byte[]
		byte[] test = arrayTobyte(arrays);
		//byte[]����
		byte[] ent = encrypt(test);
		//byte[] ����
		byte[] dec = decrypt(ent);
		//byte[]ת����String[][]
		String[][] arrayss = byteToString(dec);
		 for (int i = 0; i <arrayss.length; i++) {
			  for (int j = 0; j < arrayss[i].length; j++) {
	
				  System.out.println("ss"+arrayss[i][j]);
			  }
		  }
	}
	
	public static byte[] arrayTobyte(String[][] arrays){
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//urlConnection.getOutputStream()
		DataOutputStream dout = new DataOutputStream(baos);

		byte[] buffer = null;
		//ת����byte[]
		for(int m=0;m<arrays.length;m++){
			for(int k=0;k<arrays[m].length;k++){
				try {
					dout.writeUTF(arrays[m][k]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return buffer = baos.toByteArray();
		
	}
	
	public static String[][] byteToString(byte[] buffer){
		
		  String[][] result = new String[2][2];
		  try {
		  ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
		  DataInputStream dis = new DataInputStream(bais);
		  for (int i = 0; i <result.length; i++) {
			  for (int j = 0; j < result[i].length; j++) {
				  result[i][j] = dis.readUTF();
//				  System.out.println("ss"+result[i][j]);
			  }
		  }
		  } catch (Exception e) {
		  e.printStackTrace();
		  }
		  
		  return result;
	}
	
	/**
	 * ����
	 * @param key
	 * @param iv
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data) throws Exception{
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		return cipher.doFinal(data);
	}
	
	
	/**
	 * ����
	 * @param key
	 * @param iv
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data) throws Exception{
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
//		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @param skey
	 * @param siv
	 */
	public static void init(String skey,String siv)throws Exception{
		// DES�㷨Ҫ����һ�������ε������Դ
		DESKeySpec dks = new DESKeySpec(skey.getBytes("UTF-8"));
		// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
		// һ��SecretKey����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		key = keyFactory.generateSecret(dks);
        iv = new IvParameterSpec(siv.getBytes("UTF-8"));
		// Cipher����ʵ����ɼ��ܲ���
		cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//		this.cipher =   Cipher.getInstance("DES/ECB/None");

	}
	
	
	
}
