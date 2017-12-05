package zealjiang.lambda;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.Test;

public class Java8Lambda {


	public static void main(String[] args) throws Exception {
		//Normol
	    new Thread(new Runnable() {
	        @Override
	        public void run() {
	            System.out.println("Hello World1!");
	        }
	    }).start();

	    
	    //lambda   
	    new Thread(() -> System.out.println("Hello World2!")).start();

	}
	
	@Test
	public void testArray(){
	    //lambda
	    String [] datas = new String[] {"peng","zhao","li"};
	    Arrays.sort(datas);
	    Stream.of(datas).forEach(param ->     System.out.println(param));
	    
	    Arrays.sort(datas,(v1 , v2) -> Integer.compare(v1.length(), v2.length()));
	    Stream.of(datas).forEach(param -> System.out.println(param));
	}
}
