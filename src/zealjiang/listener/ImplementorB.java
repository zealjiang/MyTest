package zealjiang.listener;

public class ImplementorB implements MyInterface1{

	@Override
	public void didi() {
		// TODO Auto-generated method stub
		System.out.println("我是didi实现者B");
	}
	
	public ImplementorB(){
		MyBasket mb = MyBasket.getInstance();
		mb.addMyInterface1(this);
	}

}
