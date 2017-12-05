
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckString {

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
	
	/**
	 * ����Ƿ���������ַ�
	 * @param str	Ҫ�����ַ���
	 * @param length	�������������ַ���
	 * @return	���Ҫ�����ַ� ������+/?%#&=';<>�������ǿ��ַ�����������null,���߳���Ϊ0�����length,����false,���򷵻�true;
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
		//���ع��˵������ַ�����ַ�
		return boo;
	}
	
	/**
	 * ����Ƿ���������ַ�
	 * @param str	Ҫ�����ַ���
	 * @param length	�������������ַ���
	 * @return	���Ҫ�����ַ� ������+/?%#&=';<>������2 ;
	 * ���Ҫ�����ַ� ���ǿ��ַ�����������null,���߳���Ϊ0����1;
	 * ���Ҫ�����ַ� �����ȴ���lenght����3;
	 * ��������������� ����0;
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
		//���ع��˵������ַ�����ַ�
		return i;
	}
}
