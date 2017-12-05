


public class J21 {

	public static void main(String[] args) {
		int x,y,z;
		x=1;y=2;z=3;
		x+=y;
		y%=x;
		z/=x;
		System.out.println("\tx="+(x+=y));
		System.out.println("\ty="+y);
		System.out.println("\tz="+z);
		
		if(x>=0)y = x;else y = -x;
	}
}
