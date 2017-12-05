package math;

import org.junit.Test;

import com.byteArray.intToByteArray;

public class main {
	public static void main(String[] args) {
		for(int i = 11; i<1000000; i+=11){
			if((i%3==2)&&(i%5==4)&&(i%7==6)&&(i%9==8)&&(i%11==0)){
				System.out.println(i);
				break;
			}
		}
	}
	
	@Test
	public void getInt(){
		float lon = 39.1f;
		System.out.println((int)lon/1);
	}
}
