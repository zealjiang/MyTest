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
 * AES是美国联邦政府采用的商业及政府数据加密标准， 预计将在未来几十年里代替DES在各个领域中得到广泛应用。
 * AES提供128位密钥，因此，128位AES的加密强度是56位DES加密强度的1021倍还多。 假设可以制造一部可以在1秒内破解DES密码的机器，
 * 那么使用这台机器破解一个128位AES密码需要大约149亿万年的时间。
 * （更深一步比较而言，宇宙一般被认为存在了还不到200亿年）因此可以预计，美国国家标准局倡导的AES即将作为新标准取代DES。
 * 
 * @author zealjiang
 * @time 2015年12月28日上午11:14:25
 */
public class AESEncrypt {

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
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
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
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
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
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
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
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
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
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
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
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
	 * 将二进制转换成16进制
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
	 * 将16进制转换为二进制
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
	 * 加密 这种加密方式有两种限制 密钥必须是16位的 待加密内容的长度必须是16的倍数，如果不是16的倍数，就会出如下异常：
	 * javax.crypto.IllegalBlockSizeException: Input length not multiple of 16
	 * bytes at com.sun.crypto.provider.SunJCE_f.a(DashoA13*..) at
	 * com.sun.crypto.provider.SunJCE_f.b(DashoA13*..) at
	 * com.sun.crypto.provider.SunJCE_f.b(DashoA13*..) at
	 * com.sun.crypto.provider.AESCipher.engineDoFinal(DashoA13*..) at
	 * javax.crypto.Cipher.doFinal(DashoA13*..) 要解决如上异常，可以通过补全传入加密内容等方式进行避免。
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encrypt2(String content, String password) {
		try {
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
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
	 * 加密
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
	 * 解密
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
		// 加密
		System.out.println("加密前：" + content);
		byte[] encryptResult = encrypt(content, password);
		// String tt4 = rg.apache.axis.encoding.Base64.encode(encryptResult);
		String tt4 = Base64.getEncoder().encodeToString(encryptResult);
		System.out.println(new String(tt4));

		// 解密
		byte[] decryptResult = decrypt(encryptResult, password);
		System.out.println("解密后：" + new String(decryptResult));

		/*
		 * //如果想看加密后的内容按如下方法加密解密 String content = "test"; String password =
		 * "12345678"; //加密 System.out.println("加密前：" + content); byte[]
		 * encryptResult = encrypt(content, password); String encryptResultStr =
		 * parseByte2HexStr(encryptResult); System.out.println("加密后：" +
		 * encryptResultStr); //解密 byte[] decryptFrom =
		 * parseHexStr2Byte(encryptResultStr); byte[] decryptResult =
		 * decrypt(decryptFrom,password); System.out.println("解密后：" + new
		 * String(decryptResult));
		 */
	}
}
