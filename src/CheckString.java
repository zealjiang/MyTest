
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckString {

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
	
	/**
	 * 检测是否包含特殊字符
	 * @param str	要检测的字符串
	 * @param length	允许输入的最大字符数
	 * @return	如果要检测的字符 串包括+/?%#&=';<>，或者是空字符串，或者是null,或者长度为0或大于length,返回false,否则返回true;
	 */
	public static boolean RegString(String str,int length){
		boolean boo = false;
		if(str==null){
			return boo = false;
		}
		String regEx = "[+/?%#&=';<>]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if(m.find(0)||str.trim().length()<=0||str.trim().equals("")||str.trim().length()>length){
			boo = false;
		}else{
			boo = true;
		}
		//返回过滤掉特殊字符后的字符
		return boo;
	}
	
	/**
	 * 检测是否包含特殊字符
	 * @param str	要检测的字符串
	 * @param length	允许输入的最大字符数
	 * @return	如果要检测的字符 串包括+/?%#&=';<>，返回2 ;
	 * 如果要检测的字符 串是空字符串，或者是null,或者长度为0返回1;
	 * 如果要检测的字符 串长度大于lenght返回3;
	 * 不满足上面的条件 返回0;
	 */
	public static int checkString(String str,int length){
		int i = 0;
		if(str==null||str.trim().equals("")||str.length()<=0){
			return i = 1;
		}
		String regEx = "[+/?%#&=';<>]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if(m.find(0)){
			return i = 2;
		}
		if(str.trim().length()>length){
			return i = 3;
		}
		//返回过滤掉特殊字符后的字符
		return i;
	}
}
