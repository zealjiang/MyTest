package RegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyRegEx {

	public static void main(String[] args) {
		String str = "���Ʒ��ﰡdsalfk134,34;4/>   <+?$&%?";
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
	 * ����Ƿ���������ַ�
	 * @param str	Ҫ�����ַ���
	 * @return	���Ҫ�����ַ� ������+/?%#&=';<>�������ǿ��ַ�����������null,����false,���򷵻�true;
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
		//���ع��˵������ַ�����ַ�
		return boo;
	}
	/**
	 * 
	 * @param str	Ҫ�����ַ���
	 * @return	����ַ����Ƿ�Ϊ�� ,Ϊ�շ���true,��Ϊ�շ���false
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
	 * @param str	Ҫ�����ַ���
	 * @param length	�ַ����ĳ��� int
	 * @return	������ȴ���length,�򳤶�С�ڵ���0������false,����С��length����true
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
		//������������ַ���ӡfalse
		System.out.println(m.matches());
		//���ع��˵������ַ�����ַ�
		return m.replaceAll("").trim();
	}*/
}
