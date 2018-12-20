package zealjiang.extend;

import org.junit.Test;

public  class A {
	@Test
	public  void  a() {
		System.out.println("a");
		B b = new B();
		b.b();
	}
	
	public static boolean isOpen;
	
	@Test
	public  void  b() {
		isOpen = true;
		System.out.println("isOpen: "+isOpen);
		System.out.println("isOpen: "+A.isOpen);
		
		B b = new B();
		b.getIsOpen();
		System.out.println("isOpen: "+B.isOpen);
	}
	
}
