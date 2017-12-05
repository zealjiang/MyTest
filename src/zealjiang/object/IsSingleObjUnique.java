package zealjiang.object;


public class IsSingleObjUnique {

	static AA aa;
	
	public static void main(String[] args) {
		aa = AA.getInstance();
		System.out.println("1 aa :"+aa.hashCode());
		aa.destroy();
		System.out.println("2 aa :"+aa.hashCode());
		aa.destroy(aa);
		System.out.println("3 aa :"+aa.hashCode());//为什么aa不是null?
		
		
		aa.setNum(10);
		System.out.println("1 num :"+aa.getNum());
		aa.changeNum();
		System.out.println("1 num :"+aa.getNum());
		
		aa = null;
		System.out.println("4 aa :"+aa);
	}
	

}

class AA{
	public static AA a;
	private static int num;
	
	private AA() {
	
	}
	
	public static AA getInstance(){
		if(a!=null)a=null;
		
		a = new AA();
		System.out.println("a create:"+a.hashCode());
		return a;
	}
	
	public void destroy(){
		a = null;
		System.out.println("a destroy :"+a);
	}
	
	public void destroy(AA aa){
		a = null;
		aa = null;
		System.out.println("a destroy :"+a);
		System.out.println("aa destroy :"+aa);
	}
	
	public void setNum(int n){
		num = n;
	}
	
	public int getNum(){
		return num;
	}
	
	public int changeNum(){
		return ++num;
	}
}