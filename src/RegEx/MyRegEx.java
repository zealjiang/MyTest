package RegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyRegEx {

	public static void main(String[] args) {
		String str = "撒酒疯田啊dsalfk134,34;4/>   <+?$&%?";
		String str2 = "  ";
		String str3 = null;
		String str4 = "sa d";
//		System.out.println(str);
//		System.out.println("Reg"+RegString(str3));
//		System.out.println("  >>"+checkNull(str3));
		System.out.println("..>>"+checkLength("sa;;;;;;",6));
		String stra = null;
		if(stra==null){
			System.out.println(".safla");
		}
	}
	/**
	 * 检测是否包含特殊字符
	 * @param str	要检测的字符串
	 * @return	如果要检测的字符 串包括+/?%#&=';<>，或者是空字符串，或者是null,返回false,否则返回true;
	 */
	public static boolean RegString(String str){
		boolean boo = false;
		if(str==null){
			return boo = false;
		}
		String regEx = "[+/?%#&=';<>]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		System.out.println("..."+m.find(0));
		if(m.find(0)||str.length()<=0||str.trim().equals("")){
			boo = false;
		}else{		
			boo = true;
		}
		//返回过滤掉特殊字符后的字符
		return boo;
	}
	/**
	 * 
	 * @param str	要检测的字符串
	 * @return	检测字符串是否为空 ,为空返回true,不为空返回false
	 */
	public static boolean checkNull(String str){
		boolean boo = false;
		if(str.trim().equals("")||str==null){
			str = "";
			boo = true;
		}else{
			boo = false;
		}
		return boo;
	}
	/**
	 * 
	 * @param str	要检测的字符串
	 * @param length	字符串的长度 int
	 * @return	如果长度大于length,或长度小于等于0，返回false,长度小于length返回true
	 */
	public static boolean checkLength(String str,int length){
		boolean boo = false;
		if(str.length()>length||str.length()<=0){
			boo = false;
		}else{
			boo = true;
		}	
		return boo;
	}
/*	public static String StringFilter(String str){
		String regEx = "[+/?%#&=';<>\\s]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		//如果包含特殊字符打印false
		System.out.println(m.matches());
		//返回过滤掉特殊字符后的字符
		return m.replaceAll("").trim();
	}*/
}
