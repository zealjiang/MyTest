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
	 * @param str Ҫ�����String���͵��ַ���
	 * @return	���������ַ�����ת��Ϊ���ַ���true,���򷵻�false
	 */
	public static boolean isNumeric(String str)
	{
	Pattern pattern = Pattern.compile("[0-9]*"); //[0-9]*   *������˼ ��ƥ��ǰ����ӱ��ʽ0�λ���
	Matcher isNum = pattern.matcher(str);
	if( !isNum.matches() )
	{
	return false;
	}
	return true;
	} 

}
