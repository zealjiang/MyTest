
public class StringEqualsIgnoreCase {

	public static void main(String[] args) {
		String s1 = "ABC";
		String s2 = "abc";
		String s3 = "aBc";
		System.out.println(s1.equalsIgnoreCase(s2));
		System.out.println(s1.equalsIgnoreCase(s3));
	}
}
