
public class MyRunnable implements Runnable{

	static int k =0 ;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<3;i++){
			System.out.println("  :"+i);
			System.out.println("   K:"+k);
		}
			if(k<1){
				MyRunnable mr = new MyRunnable();
				new Thread(mr).start();
				try {
					k++;
					System.out.println("  "+new Thread().currentThread().getName());
					Thread.currentThread().sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("  "+Thread.currentThread().getName());
				System.out.println("......kkkkkk...");
//				k++;			
			}	

	}
	public static void main(String[] args) {
		MyRunnable mr = new MyRunnable();
		new Thread(mr).start();
		System.out.println("  "+Thread.currentThread().getName());
		System.out.println(".....");
	}
}
