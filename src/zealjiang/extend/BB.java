package zealjiang.extend;

public class BB extends AA{

	@Override
	public void afterA() {
		// TODO Auto-generated method stub
		super.afterA();
		System.out.println("BB");
	}
	
	public static void main(String[] args) {
		BB bb = new BB();
		bb.a();
	}
}
