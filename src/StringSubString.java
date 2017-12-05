
public class StringSubString {

	public static void main(String[] args) {
		String raw_value = "90   sp";
		String dust1 = raw_value.substring(0, raw_value.length()-2);
		Integer value = Integer.parseInt(dust1.trim());
		System.out.println(value);
	}
}
