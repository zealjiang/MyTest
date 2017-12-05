package zealjiang.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 implements Runnable{


	public static void main(String[] args) {
		Test1 test = new Test1();
		test.run();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i=0;
		while(true){
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String djrq = sdf.format(new Date());

			System.out.println("当前时间："+i+"    "+djrq
					);
			if(i==10)break;
		}
	}
}
