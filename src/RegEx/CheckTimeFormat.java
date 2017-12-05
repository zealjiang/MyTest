package RegEx;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckTimeFormat {

	public static void main(String[] args) {
		boolean boo;
		String year = "1987";
		String month = "02";
		String day = "30";
		boo = checkDate(year,month,day);
		System.out.println("boo>>"+boo);
	}
	
	/**
	 * 
	 * @note 校验日期格式
	 * @author zealjiang
	 * @time 2015年12月25日下午2:56:26
	 * @param year
	 * @param month
	 * @param day
	 * @return 符合返回true，返之返回false
	 */
    public static boolean checkDate(String year, String month, String day) {         
    	SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");    
    	try {        
    		String s3 = year + month + day;   
    		/*
    		 * java.text.DateFormat
    		 * Specifies whether or not date/time parsing shall be lenient. 
    		 * With lenient parsing, the parser may use heuristics to interpret 
    		 * inputs that do not precisely match this object's format. With strict parsing, 
    		 * inputs must match this object's format.
				Parameters
				value 	true to set the calendar to be lenient, false otherwise. 
    		 */
    		simpledateformat.setLenient(false); 
    		/*
    		 * java.text.DateFormat
    		 * Parses a date from the specified string using the rules of this date format.
    		 * Parameters
				string 	the string to parse.
				Returns
    			the Date resulting from the parsing.
    		 */
    		Date date = simpledateformat.parse(s3);
    		boolean boo = date.after(new Date());
    		if(boo){
    			System.out.println("出生日期大于当前日期");
    			return false;
    		}
    	}       
    	catch (java.text.ParseException parseexception) {     
    		return false;   
    	}        
    	return true; 
    }
}
