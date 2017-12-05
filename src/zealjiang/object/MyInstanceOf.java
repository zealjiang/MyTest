package zealjiang.object;

public class MyInstanceOf {

	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		C c = new C();
		
		test(a);
	}
	
	public static void test(Father f){
		if(f instanceof A)
			System.out.println("A");
		else if(f instanceof B)
			System.out.println("B");
		else
			System.out.println("Other");
	}
}
class Father{
	
}
class A extends Father{
	
}
class B extends Father{
	
}
class C {
	
}