package com.encrypt;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Test {

	static String password = "1234567890123456";
	static String iv = "5efd3f6060e20330";
	public static void main(String[] args) {
		//String json = "{\"firstName\":\"Brett\"}";
		String json = "{\"functionId\":\"HLW001\", \"params\":{\"name\":\"18618269575\", \"password\":\"e10adc3949ba59abbe56e057f20f883e\"}}";

		//加密方法一
		String aesJson = new String(AESEncrypt.DataEncrypt(json, password));
		
		aesJson = AesEncryptionUtil.encrypt(json,password,iv);
		
		System.out.println("aesJson: "+aesJson);
		

		
		//解密方法一		
		String aesdJson ;//= AESEncrypt.DataDecrypt(aesJson, password);
		
		aesdJson = AesEncryptionUtil.decrypt(aesJson,password,iv);
		
		System.out.println("aesdJson: "+aesdJson);
		
		try {
			
			json = "{\"functionId\":\"HLW006\", \"params\":{\"userId\":\"10613\", \"pageSize\":\"10\", \"page\":\"1\"}}";

			String b64 = Base64.encode(json.getBytes());
			System.out.println("b64 :"+b64);
			
			 String str = new String(Base64.decode(b64));
			 												 															
			System.out.println("str: "+str);
		} catch (Base64DecodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
