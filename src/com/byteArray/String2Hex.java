package com.byteArray;

public class String2Hex {

	public static void main(String[] args) {
		String2Hex sh = new String2Hex();
		System.out.println(sh.str2HexStr("10"));
		System.out.println(Long.valueOf("11", 16));
		System.out.println(Integer.valueOf("11", 16));
		//ʮ����ת16����
		System.out.println(Integer.toHexString(20));
		System.out.println("20160421".substring(0, 2));
		System.out.println("20160421".substring(2, 4));
	}
	

	
	
	/**   
	 * �ַ���ת����ʮ�������ַ���  
	 * @param String str ��ת����ASCII�ַ���  
	 * @return String ÿ��Byte֮��ո�ָ�����: [61 6C 6B]  
	 */      
	public static String str2HexStr(String str)    
	{      
	  
	    char[] chars = "0123456789ABCDEF".toCharArray();      
	    StringBuilder sb = new StringBuilder("");    
	    byte[] bs = str.getBytes();      
	    int bit;      
	        
	    for (int i = 0; i < bs.length; i++)    
	    {      
	        bit = (bs[i] & 0x0f0) >> 4;      
	        sb.append(chars[bit]);      
	        bit = bs[i] & 0x0f;      
	        sb.append(chars[bit]);    
	        sb.append(' ');    
	    }      
	    return sb.toString().trim();      
	}   
	
	public static String dec2Hex(){
		Integer.valueOf("20", 16);
		return null;
	}

}
