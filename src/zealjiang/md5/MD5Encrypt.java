package zealjiang.md5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encrypt {

	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
	
    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    
    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }


/*   public String EncoderByMd5(String str){ 
	//确定计算方法 
	MessageDigest md5=MessageDigest.getInstance("MD5");
	BASE64Encoder base64en = new BASE64Encoder();
	//加密后的字符串 
	 String newstr=base64en.encode(md5.digest(str.getBytes("utf-8"))); 
	return newstr; 
	}*/
    
    
    private static String MD52(String sourceStr) throws UnsupportedEncodingException {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String s = new String(sourceStr.getBytes(),"UTF-8");
            md.update(s.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            System.out.println("MD5(" + sourceStr + ",32) = " + result);
            System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }

    
    
    public static String testMD(String username) { 
    	String usernameMD5 = "";
    	try { 
    	MessageDigest messageDigest = MessageDigest.getInstance("MD5"); 
    	messageDigest.update(username.getBytes()); 
    	usernameMD5 = messageDigest.digest().toString(); 

    	} catch (Exception e) { 
    	e.printStackTrace(); 
    	}
		return usernameMD5; 
   } 

    
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		System.out.println(MD5Encrypt.MD5("http://tour.ishowchina.com/video/jiuzhaigou/mp3/诺日朗瀑布.mp3"));
		System.out.println(GetMD5Code("http://tour.ishowchina.com/video/jiuzhaigou/mp3/诺日朗瀑布.mp3"));
		System.out.println(MD52("http://tour.ishowchina.com/video/jiuzhaigou/mp3/诺日朗瀑布.mp3"));
		System.out.println(testMD("http://tour.ishowchina.com/video/jiuzhaigou/mp3/诺日朗瀑布.mp3"));
		System.out.println(md5("http://tour.ishowchina.com/video/jiuzhaigou/mp3/诺日朗瀑布.mp3"));
	}

}
