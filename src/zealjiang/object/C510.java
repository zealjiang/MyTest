package zealjiang.object;

public class C510 extends A1{
	final static int cc =1;
	static final int c =1;
	public static void main(String[] args) {
		C510 p1 = new C510();
		p1.Printme();
		
	}
}

class A1{
	int x=0;int y =1;
	public void Printme(){
		System.out.println("x="+x+" y="+y);
		System.out.println("I am an"+this.getClass().getName());
	}
}
