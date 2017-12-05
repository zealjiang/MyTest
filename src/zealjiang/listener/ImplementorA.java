package zealjiang.listener;

public class ImplementorA implements MyInterface1{

	@Override
	public void didi() {
		// TODO Auto-generated method stub
		System.out.println("我是didi实现者A");
	}
	
	public ImplementorA(){
		MyBasket mb = MyBasket.getInstance();
		mb.addMyInterface1(this);
	}

}
