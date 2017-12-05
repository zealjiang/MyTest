
public class StringLengthTest {
	public static void main(String[] args) {
		String str = "  ";
		System.out.println(isEmpty(str));
		System.out.println(isEmpty(str.trim()));
	}
    public static boolean isEmpty(String str)
    {
    	return str.length() == 0;
    }
}
