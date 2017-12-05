package zealjiang.test;

public class Sample {


	public static void main(String[] args) {
		A p1, p2; 
		p2 = new A(12, 15); 
		p1 = p2;
		p2.x++; 
		System.out.println("p1.x=" + p1 .x); 
	}
}
