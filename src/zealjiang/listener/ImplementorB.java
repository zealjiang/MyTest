package zealjiang.listener;

public class ImplementorB implements MyInterface1{

	@Override
	public void didi() {
		// TODO Auto-generated method stub
		System.out.println("����didiʵ����B");
	}
	
	public ImplementorB(){
		MyBasket mb = MyBasket.getInstance();
		mb.addMyInterface1(this);
	}

}
