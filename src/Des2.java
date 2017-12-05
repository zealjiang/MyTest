

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class Des2 {

	
	private Key  key=null;
	private IvParameterSpec	 iv=null;
	private	Cipher cipher=null;
	/**
	 * 加密
	 * @param key
	 * @param iv
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] data) throws Exception{
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		return cipher.doFinal(data);
	}
	
	
	/**
	 * 解密
	 * @param key
	 * @param iv
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] data) throws Exception{
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
//		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @param skey
	 * @param siv
	 */
	public void init(String skey,String siv)throws Exception{
		// DES算法要求有一个可信任的随机数源
		DESKeySpec dks = new DESKeySpec(skey.getBytes("UTF-8"));
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		this.key = keyFactory.generateSecret(dks);
        this.iv = new IvParameterSpec(siv.getBytes("UTF-8"));
		// Cipher对象实际完成加密操作
		this.cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//		this.cipher =   Cipher.getInstance("DES/ECB/None");

	}
	

	
	
    public   static   void   main(String[]   args)   throws   Exception   {
		String key = "@#abcdef";
		String iv = "@1abcdef";
        Des2   des   =   new   Des2();
        des.init(key, iv);
        
	   File file = new File("d:/tt1.xls");
	   FileInputStream fin = new FileInputStream(file);
	   byte[] b=new byte[fin.available()];
	   fin.read(b);
		FileOutputStream fileOut = new FileOutputStream(new File("d:/tt3.xls"));// 写入文件
		byte[] temp=des.decrypt(b);
		fileOut.write(temp);
		fin.close();
		fileOut.close();
       
        
        
        
        
        
//		FileInputStream fileIn = new FileInputStream(new File("d:/tt4.xls"));// 读取文件
//		FileOutputStream fileOut = new FileOutputStream(new File("d:/tt3.xls"));// 写入文件
//        
//		byte[] buff = new byte[48];
//		byte[] temp = new byte[8];
//		while (-1 != fileIn.read(buff, 0, buff.length)) {
//			temp=des.decrypt(buff);
////			temp=cipher.doFinal(buff);
//			fileOut.write(temp);
//		}
//		System.out.println("#55555#");
//		fileIn.close();
//		fileOut.close();       
        
  
        
        
    }   
}
