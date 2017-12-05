
/**
 * static静态代码块只有类.class第一次加载时执行一次，而且是先于其它加载的
 *
 */
public class StaticMethod {

	static{
		System.out.println("我是一个static方法");
		for(int i=0 ;i<10; i++){
			System.out.println("i :" +i);
		}
	}

	public static void main(String[] args) {
		System.out.println("main");
		
	}

}
