package com.comparator;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;


public class CollectionsSortMethod {

    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
    	for(int i=0;i<list.size();i++){
    		System.out.println("begin list-->"+((CityInfo)list.get(i)).getCityName()+" "+((CityInfo)list.get(i)).getCityNo());
    	}
    	
        T[] array = list.toArray((T[]) new Object[list.size()]);
        Arrays.sort(array, comparator);
        int i = 0;
        
    	for(int m=0;m<list.size();m++){
    		System.out.println("in list-->"+((CityInfo)list.get(m)).getCityName()+" "+((CityInfo)list.get(m)).getCityNo());
    	}
        
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            it.next();
            it.set(array[i++]);          
        }
        

        
        
    	for(int j=0;j<list.size();j++){
    		System.out.println("end list-->"+((CityInfo)list.get(j)).getCityName()+" "+((CityInfo)list.get(j)).getCityNo());
    	}

    }
    
    @Test
    public void sortTest(){
    	CityInfo city = new CityInfo();
		city.setCityName("保定市");
		city.setCityNo(010);
		
    	CityInfo city2 = new CityInfo();
		city2.setCityName("安徽省");
		city2.setCityNo(0312);
		
    	CityInfo city3 = new CityInfo();
		city3.setCityName("大连市");
		city3.setCityNo(026);
		
    	CityInfo city4 = new CityInfo();
		city4.setCityName("重庆市");
		city4.setCityNo(023);
		
    	ArrayList<CityInfo> list = new ArrayList<CityInfo>();
    	list.add(city);
    	list.add(city2);
    	list.add(city3);
    	list.add(city4);
    	
    	System.out.println("--------------ByNo--------------: ");
    	sort(list,new ByNo());
    	System.out.println("--------------ByName--------------: ");
    	sort(list,new ByName());
    }
    
	class ByNo implements Comparator<CityInfo> {
		@Override
		public int compare(CityInfo lhs, CityInfo rhs) {
			return lhs.cityNo - rhs.cityNo;
		}
	}
	
	class ByName implements Comparator<CityInfo> {
		Comparator cmp = Collator.getInstance(java.util.Locale.CHINA); 
		@Override
		public int compare(CityInfo lhs, CityInfo rhs) {
			return cmp.compare(lhs.cityName, rhs.cityName);
		}
	}
	
	@Test
	public void compara(){
		String[] arr = {"保全","安","全","保安","b","a","A","黛堡嘉莱","成都映像","大妙茶馆","中国红"};
		Comparator cmp = Collator.getInstance(java.util.Locale.CHINA); 
		Arrays.sort(arr, cmp);   
		for (int j = 0; j < arr.length; j++) {
			System.out.println("j :"+arr[j]);
		}
		
	}
	
	public class CityInfo {

		int cityNo;
		String cityName;
		public int getCityNo() {
			return cityNo;
		}

		public void setCityNo(int cityNo) {
			this.cityNo = cityNo;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String city_name) {
			this.cityName = city_name;
		}

	}
}
