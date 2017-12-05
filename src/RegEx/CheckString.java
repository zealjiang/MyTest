package RegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckString {
	public static void main(String[] args) {
		String str = "2";
		Boolean boo = isNumeric(str);
		System.out.println("...>"+boo);
	}
	
	/**
	 * 
	 * @param str 要输入的String类型的字符串
	 * @return	如果输入的字符串可转换为数字返回true,否则返回false
	 */
	public static boolean isNumeric(String str)
	{
	Pattern pattern = Pattern.compile("[0-9]*"); //[0-9]*   *号是意思 是匹配前面的子表达式0次或多次
	Matcher isNum = pattern.matcher(str);
	if( !isNum.matches() )
	{
	return false;
	}
	return true;
	} 

}
