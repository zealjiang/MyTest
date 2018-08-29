package zealjiang.object;

public class CopyTest {

	private static int index;
	private int index_;
	
	
	
	public CopyTest() {
        index_++;
        index = index_;
	}

	public static int getIndex() {
		return index;
	}
}
