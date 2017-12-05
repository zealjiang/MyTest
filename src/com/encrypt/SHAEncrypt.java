package com.encrypt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SHAEncrypt {

	public static final String KEY = "F0DDFFD1AF524130A2AF7A6723D71EB0";
	
	public static final String SECRET = "67fce98d";
	
	
	public static void main(String[] args) {//&key=F0DDFFD1AF524130A2AF7A6723D71EB0&sign=3173E0967AFB7182416D749F24ABA4250B00226C
		String url = "http://tour.ishowchina.com/rest/order/submit?name=李志江&phone=130481198608046935&icno=18618269575&tickets=[%7B%22num%22:%221%22,%22scenicid%22:%221%22,%22icrowdkindid%22:%221%22,%22tickettypeid%22:%22577%22,%22date%22:%222015-03-24%22%7D]&key=F0DDFFD1AF524130A2AF7A6723D71EB0&sign=3173E0967AFB7182416D749F24ABA4250B00226C";
		String result = encryptParameters(url);
		System.out.println("result--->"+result);
	}
	/**
	 * 
	 * author zealjiang
	 * time 2015年3月17日 下午3:59:18
	 * note: 加密url
	 * @param url
	 */
	public static String encryptParameters(String url){
		
		//加密
		Map<String,Object> map = new HashMap<String,Object>();
			
		int i = url.indexOf("?");
		String a = url.substring(i+1);
		
		String[] p = a.split("&");
		for (int j = 0; j < p.length; j++) {
			//System.out.println(p[j]);
			
			String[] m = p[j].split("=");
			map.put(m[0], m[1]);
		}

		//map.put("key", KEY);
		
		return getSign(map,KEY,SECRET);
	}
	
	//生成签名
	public static String getSign(Map<String, Object> params,String appkey,String secret){
		// 对参数名进行字典排序  
		String[] keyArray = params.keySet().toArray(new String[0]);  
		Arrays.sort(keyArray);  
		  
		// 拼接有序的参数名-值串  
		StringBuilder stringBuilder = new StringBuilder(); 
		stringBuilder.append(appkey);  
		for (String key : keyArray){  
		    stringBuilder.append("&").append(key).append(params.get(key).toString());  
		}  
		stringBuilder.append(secret);  
		String codes = stringBuilder.toString();  
		String sign = org.apache.commons.codec.digest.DigestUtils.shaHex(codes).toUpperCase();//shaHex(codes).toUpperCase();
		return sign;
	}
}
