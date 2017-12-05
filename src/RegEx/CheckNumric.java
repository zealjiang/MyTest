package RegEx;

public class CheckNumric {

	public static void main(String[] args) {
		String str1 = "46541324a";
		String str2 = "46541324";	
		System.out.println("str1 :"+checkFigure(str1)+" str2:"+checkFigure(str2));
	}
	
	/**
	 * 
	 * @note 检查字符串是否全为数字
	 * @author zealjiang
	 * @time 2015年12月25日下午4:18:53
	 * @param certiCode
	 * @return 全为数字返回true,返之反回false
	 */
	public static boolean checkFigure(String certiCode) {    
		try {    
				/*
				 * Parses the specified string as a signed decimal long value. 
				 * The ASCII character - ('-') is recognized as the minus sign.
				 * return   the primitive long value represented by string.
				 */
				Long.parseLong(certiCode);     
			}      
		catch (NumberFormatException e) {    
			return false;    
		}      
		return true;   
	}
}
