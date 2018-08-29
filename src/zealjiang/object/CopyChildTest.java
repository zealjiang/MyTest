package zealjiang.object;


public class CopyChildTest extends CopyTest{

	public static void main(String[] args) {
		test();
	}
	
	private static void test() {
		System.out.println(getIndex()+"");
		CopyChildTest test = new CopyChildTest();
		System.out.println(getIndex()+"");
	}
}
