package zealjiang.listener;

import java.util.ArrayList;

public class MyBasket {

	public static ArrayList<MyInterface1> myInterface1List = new ArrayList<MyInterface1>();
	
	private static MyBasket mb = null;
	
	private MyBasket(){
		
	}
	
	public void addMyInterface1(MyInterface1 interf){
		if(interf!=null)
			myInterface1List.add(interf);
	};
	
	public ArrayList<MyInterface1> getMyInterface1s(){
		return myInterface1List;
	}
	
	public static MyBasket getInstance(){
		if(mb == null){
			mb = new MyBasket();
		}
		return mb;
	}
}
