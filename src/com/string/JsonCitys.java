package com.string;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.junit.Test;

import com.file.ReadFileToString;
import com.google.gson.Gson;

public class JsonCitys {

	private int[] provinceIdArray = {1,2,31,3,4,5,6,7,8,9,11,10,12,13,15,16,14,17,18,19,20,23,21,24,22,25,26,28,27,29,30};
	private String[] provinceNameArray = {"安徽","北京","重庆","福建","甘肃","广东","广西","贵州","海南","河北","黑龙江","河南","湖北","湖南","江苏","江西",
			"吉林","辽宁","内蒙古","宁夏","青海","陕西","山东","上海","山西","四川","天津","新疆","西藏","云南","浙江"};

	@Test
	public void toCityList(){
		ArrayList<City> cityList = new ArrayList<City>();
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
		try {
			String str = "";
			String str1 = "";
			fis = new FileInputStream("省市集合.txt");// FileInputStream
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
			br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
											// InputStreamReader的对象
			while ((str = br.readLine()) != null) {
				str1 += str + "\n";
				City city = handleLineData(str);
				if(city!=null){
					cityList.add(city);
				}
			}
			// 当读取的一行不为空时,把读到的str的值赋给str1
			//System.out.println(str1);// 打印出str1
			
			ArrayList<CityList> resultList = new ArrayList<CityList>();
			//因为数据是顺序排列，所有省 所有市 
			for (int i = 0; i < cityList.size(); i++) {
				City city = cityList.get(i);
				if(city.getParentId()==0){//省
					CityList cityListO = new CityList();
					cityListO.setCity(city);
					cityListO.setCityList(new ArrayList<City>());
					resultList.add(cityListO);
							
				}else{//市
					//这层循环是循环所有省
					for (int j = 0; j < resultList.size(); j++) {
						CityList cityListO = resultList.get(j);
						City pcity = cityListO.getCity();
						if(pcity.getCityId()==city.getParentId()){
							cityListO.getCityList().add(city);
						}
					}
				}
			}
			//System.out.println(resultList);
			CityListBean clb = new CityListBean();
			clb.setCityListBean(resultList);
	        String json = new Gson().toJson(clb);
	        System.out.println(json);
		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("读取文件失败");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
				// 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 处理一行数据
	 * @param line
	 * @return
	 */
	public City handleLineData(String line){
		City city = null;
		//System.out.println(line);// 打印出str1
		//以数字开头
		String reg = "[0-9]";
		boolean isNum = Pattern.compile(reg).matcher(line.substring(0, 1)).find();
		if(isNum){
			String[] data = line.split("\t");
			city = new City();
			city.setCityId(Integer.valueOf(data[0]));
			city.setCityName(data[1]);
			city.setParentId(Integer.valueOf(data[2]));
		}
		return city;
	}
	
	class City{
		private int cityId;
		private String cityName;
		private int parentId;
		
		public int getCityId() {
			return cityId;
		}
		public void setCityId(int cityId) {
			this.cityId = cityId;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public int getParentId() {
			return parentId;
		}
		public void setParentId(int parentId) {
			this.parentId = parentId;
		}	
	}
	
	class CityList{
		private City city;
		private ArrayList<City> cityList;
		
		public City getCity() {
			return city;
		}
		public void setCity(City city) {
			this.city = city;
		}
		public ArrayList<City> getCityList() {
			return cityList;
		}
		public void setCityList(ArrayList<City> cityList) {
			this.cityList = cityList;
		}
	}
	
	class CityListBean{
		private ArrayList<CityList> cityListBean;

		public ArrayList<CityList> getCityListBean() {
			return cityListBean;
		}

		public void setCityListBean(ArrayList<CityList> cityListBean) {
			this.cityListBean = cityListBean;
		}
		
		
	}
}
