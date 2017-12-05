package com.encrypt;

import java.io.UnsupportedEncodingException;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES���ܽ����㷨
 */
public class AesEncryptionUtil {
	/** �㷨/ģʽ/��� **/
	private static final String CipherMode = "AES/CBC/PKCS5Padding";


	/** ������Կ **/
	private static SecretKeySpec createKey(String key) {
		byte[] data = null;
		if (key == null) {
			key = "";
		}
		StringBuffer sb = new StringBuffer(16);
		sb.append(key);
		while (sb.length() < 16) {
			sb.append("0");
		}
		if (sb.length() > 16) {
			sb.setLength(16);
		}


		try {
			data = sb.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new SecretKeySpec(data, "AES");
	}


	private static IvParameterSpec createIV(String password) {
		byte[] data = null;
		if (password == null) {
			password = "";
		}
		StringBuffer sb = new StringBuffer(16);
		sb.append(password);
		while (sb.length() < 16) {
			sb.append("0");
		}
		if (sb.length() > 16) {
			sb.setLength(16);
		}


		try {
			data = sb.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new IvParameterSpec(data);
	}


	/** �����ֽ����� **/
	public static byte[] encrypt(byte[] content, String password, String iv) {
		try {
			SecretKeySpec key = createKey(password);
			Cipher cipher = Cipher.getInstance(CipherMode);
			cipher.init(Cipher.ENCRYPT_MODE, key, createIV(iv));
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/** ����(���Ϊ16�����ַ���) **/
	public static String encrypt(String content, String password, String iv) {
		byte[] data = null;
		try {
			data = content.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		data = encrypt(data, password, iv);
		String result = byte2hex(data);
		return result;
	}


	/** �����ֽ����� **/
	public static byte[] decrypt(byte[] content, String password, String iv) {
		try {
			SecretKeySpec key = createKey(password);
			Cipher cipher = Cipher.getInstance(CipherMode);
			cipher.init(Cipher.DECRYPT_MODE, key, createIV(iv));
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/** 
	 * ����(������Ϊ�ַ���) 
	 * 
	 * password ��Կ
	 * iv ��Կƫ����
	 */
	public static String decrypt(String content, String password, String iv) {
		byte[] data = null;
		try {
			data = hex2byte(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		data = decrypt(data, password, iv);
		if (data == null)
			return null;
		String result = null;
		try {
			result = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}


	/** �ֽ�����ת��16�����ַ��� **/
	public static String byte2hex(byte[] b) { // һ���ֽڵ�����
		StringBuffer sb = new StringBuffer(b.length * 2);
		String tmp = "";
		for (int n = 0; n < b.length; n++) {
			// ����ת��ʮ�����Ʊ�ʾ
			tmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
		}
		return sb.toString().toUpperCase(); // ת�ɴ�д
	}


	/** ��hex�ַ���ת�����ֽ����� **/
	private static byte[] hex2byte(String inputString) {
		if (inputString == null || inputString.length() < 2) {
			return new byte[0];
		}
		inputString = inputString.toLowerCase();
		int l = inputString.length() / 2;
		byte[] result = new byte[l];
		for (int i = 0; i < l; ++i) {
			String tmp = inputString.substring(2 * i, 2 * i + 2);
			result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
		}
		return result;
	}
}

 
