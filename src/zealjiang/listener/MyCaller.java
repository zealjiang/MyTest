package zealjiang.listener;

import java.util.ArrayList;

public class MyCaller {

	public static void main(String[] args) {
		
		ImplementorA ia = new ImplementorA();
		ImplementorB ib = new ImplementorB();
		
		MyBasket mb = MyBasket.getInstance();
		ArrayList<MyInterface1> list =  mb.getMyInterface1s();
		
		for(int i=0;i<list.size();i++){
			list.get(i).didi();
		}
	}
} 
