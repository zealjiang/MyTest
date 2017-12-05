package zealjiang.bksx.friendly;

import zealjiang.bksx.collactor.CollactorTest;

class MyFriendly {
	
	public static void main(String[] args) {
		CollactorTest ct = new CollactorTest();
		ct.main(new String[]{"sxx"});
		ct.method3();
		
	}
	
	private void privateMethod(){
		
		System.out.println("I am privateMethod");
	}
	
	void friendlyMethod(){
		
		System.out.println("I am friendlyMethod");
	}
}
