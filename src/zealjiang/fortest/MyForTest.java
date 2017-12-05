package zealjiang.fortest;

import java.util.ArrayList;

import org.junit.Test;

/**
 * 只能用在嵌套循环中
 * @author zealjiang
 * @time 2016年5月10日下午4:27:56
 */
public class MyForTest {

	public static void main(String[] args) {
		int s,i;
		for(s=0,i=1;i<=100;s+=i,i++);
		System.out.println("s--->"+s+" i--->"+i);
	}
	
	private void tag(){
		tag1:
		for (int i = 0; i < 10; i++) {
			
			System.out.println("i: "+i);
			
			 
			System.out.println("-----------");
			
			if(i==5){
				continue tag1;
			}
		}
	}
	
	@Test
	public void indexList(){
		ArrayList<String> usedDeviceList = new ArrayList<>();
		usedDeviceList.add("1");
		usedDeviceList.add("2");
		usedDeviceList.add("3");
		usedDeviceList.add("4");
		usedDeviceList.add("5");
		usedDeviceList.add("6");
		
		
        //曾用设置最多保留5个
        if(usedDeviceList!=null&&usedDeviceList.size()>5){
            usedDeviceList.remove(usedDeviceList.size()-1);
        }
        
        for (String string : usedDeviceList) {
        	System.out.println(""+string);
		}			
	}
}
