import java.text.SimpleDateFormat;
import java.util.Date;


public class SimpleDateFormatTest {

	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat(
						"yyyyMMddHHmm00").format(new Date()));
		System.out.println("-->>"+new SimpleDateFormat().format(new Date()));
		
		System.out.println(Integer.toHexString(2131165395));
	}
}
