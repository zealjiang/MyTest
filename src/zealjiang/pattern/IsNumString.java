package zealjiang.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IsNumString {

	public static void main(String[] args) {
		System.out.println(""+isNumeric("2a31"));
		System.out.println(""+isNumeric("2"));
	}
	
	public static boolean isNumeric(String str) {

		Pattern pattern = Pattern.compile("[0-9]*");

		Matcher isNum = pattern.matcher(str);

		if (!isNum.matches()) {

			return false;

		}

		return true;

	}
	
	
}
