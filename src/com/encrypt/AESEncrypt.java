package com.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES�����������������õ���ҵ���������ݼ��ܱ�׼�� Ԥ�ƽ���δ����ʮ�������DES�ڸ��������еõ��㷺Ӧ�á�
 * AES�ṩ128λ��Կ����ˣ�128λAES�ļ���ǿ����56λDES����ǿ�ȵ�1021�����ࡣ �����������һ��������1�����ƽ�DES����Ļ�����
 * ��ôʹ����̨�����ƽ�һ��128λAES������Ҫ��Լ149�������ʱ�䡣
 * ������һ���Ƚ϶��ԣ�����һ�㱻��Ϊ�����˻�����200���꣩��˿���Ԥ�ƣ��������ұ�׼�ֳ�����AES������Ϊ�±�׼ȡ��DES��
 * 
 * @author zealjiang
 * @time 2015��12��28������11:14:25
 */
public class AESEncrypt {

	/**
	 * ����
	 * 
	 * @param content
	 *            ��Ҫ���ܵ�����
	 * @param password
	 *            ��������
	 * @return
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// ����������
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��
			byte[] result = cipher.doFinal(byteContent);
			return result; // ����
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����
	 * 
	 * @param content
	 *            ����������
	 * @param password
	 *            ������Կ
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// ����������
			cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��
			byte[] result = cipher.doFinal(content);
			return result; // ����
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����
	 * 
	 * @param content
	 *            ��Ҫ���ܵ�����
	 * @param password
	 *            ��������
	 * @return
	 * @throws NoSuchProviderException
	 */
	public static byte[] encryptNormal(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
			SecureRandom sr = new SecureRandom(password.getBytes());
			sr.setSeed(password.getBytes());
			kgen.init(128, sr); // 192 and 256 bits may not be available
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// ����������
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��
			byte[] result = cipher.doFinal(byteContent);
			return result; // ����
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����
	 * 
	 * @param content
	 *            ����������
	 * @param password
	 *            ������Կ
	 * @return
	 */
	public static byte[] decryptNormal(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
			SecureRandom sr = new SecureRandom(password.getBytes());
			sr.setSeed(password.getBytes());
			kgen.init(128, sr); // 192 and 256 bits may not be available
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// ����������
			cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��
			byte[] result = cipher.doFinal(content);
			return result; // ����
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��������ת����16����
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * ��16����ת��Ϊ������
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * ���� ���ּ��ܷ�ʽ���������� ��Կ������16λ�� ���������ݵĳ��ȱ�����16�ı������������16�ı������ͻ�������쳣��
	 * javax.crypto.IllegalBlockSizeException: Input length not multiple of 16
	 * bytes at com.sun.crypto.provider.SunJCE_f.a(DashoA13*..) at
	 * com.sun.crypto.provider.SunJCE_f.b(DashoA13*..) at
	 * com.sun.crypto.provider.SunJCE_f.b(DashoA13*..) at
	 * com.sun.crypto.provider.AESCipher.engineDoFinal(DashoA13*..) at
	 * javax.crypto.Cipher.doFinal(DashoA13*..) Ҫ��������쳣������ͨ����ȫ����������ݵȷ�ʽ���б��⡣
	 * 
	 * @param content
	 *            ��Ҫ���ܵ�����
	 * @param password
	 *            ��������
	 * @return
	 */
	public static byte[] encrypt2(String content, String password) {
		try {
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��
			byte[] result = cipher.doFinal(byteContent);
			return result; // ����
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * ����
	 * @param str
	 * @param key
	 * @return
	 */
	public static String DataEncrypt(String str, String key) {
		String encrypt = null;
		try {
			byte[] ret = encrypt(str, key);
			encrypt = new String(Base64.getEncoder().encode(ret));
		} catch (Exception e) {
			System.out.print(e);
			encrypt = str;
		}
		return encrypt;
	}

	/**
	 * ����
	 * @param str
	 * @param key
	 * @return
	 */
	public static String DataDecrypt(String str, String key) {
		String decrypt = null;
		try {
			byte[] ret = decrypt(Base64.getDecoder().decode(str), key);
			decrypt = new String(ret, "UTF-8");
		} catch (Exception e) {
			System.out.print(e);
			decrypt = str;
		}
		return decrypt;
	}

	public static void main(String[] args) {
		String content = "test";
		String password = "12345678";
		// ����
		System.out.println("����ǰ��" + content);
		byte[] encryptResult = encrypt(content, password);
		// String tt4 = rg.apache.axis.encoding.Base64.encode(encryptResult);
		String tt4 = Base64.getEncoder().encodeToString(encryptResult);
		System.out.println(new String(tt4));

		// ����
		byte[] decryptResult = decrypt(encryptResult, password);
		System.out.println("���ܺ�" + new String(decryptResult));

		/*
		 * //����뿴���ܺ�����ݰ����·������ܽ��� String content = "test"; String password =
		 * "12345678"; //���� System.out.println("����ǰ��" + content); byte[]
		 * encryptResult = encrypt(content, password); String encryptResultStr =
		 * parseByte2HexStr(encryptResult); System.out.println("���ܺ�" +
		 * encryptResultStr); //���� byte[] decryptFrom =
		 * parseHexStr2Byte(encryptResultStr); byte[] decryptResult =
		 * decrypt(decryptFrom,password); System.out.println("���ܺ�" + new
		 * String(decryptResult));
		 */
	}
}
