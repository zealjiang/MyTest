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
	private String[] provinceNameArray = {"����","����","����","����","����","�㶫","����","����","����","�ӱ�","������","����","����","����","����","����",
			"����","����","���ɹ�","����","�ຣ","����","ɽ��","�Ϻ�","ɽ��","�Ĵ�","���","�½�","����","����","�㽭"};

	@Test
	public void toCityList(){
		ArrayList<City> cityList = new ArrayList<City>();
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; // ���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
		try {
			String str = "";
			String str1 = "";
			fis = new FileInputStream("ʡ�м���.txt");// FileInputStream
			// ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
			isr = new InputStreamReader(fis);// InputStreamReader ���ֽ���ͨ���ַ���������,
			br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new
											// InputStreamReader�Ķ���
			while ((str = br.readLine()) != null) {
				str1 += str + "\n";
				City city = handleLineData(str);
				if(city!=null){
					cityList.add(city);
				}
			}
			// ����ȡ��һ�в�Ϊ��ʱ,�Ѷ�����str��ֵ����str1
			//System.out.println(str1);// ��ӡ��str1
			
			ArrayList<CityList> resultList = new ArrayList<CityList>();
			//��Ϊ������˳�����У�����ʡ ������ 
			for (int i = 0; i < cityList.size(); i++) {
				City city = cityList.get(i);
				if(city.getParentId()==0){//ʡ
					CityList cityListO = new CityList();
					cityListO.setCity(city);
					cityListO.setCityList(new ArrayList<City>());
					resultList.add(cityListO);
							
				}else{//��
					//���ѭ����ѭ������ʡ
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
			System.out.println("�Ҳ���ָ���ļ�");
		} catch (IOException e) {
			System.out.println("��ȡ�ļ�ʧ��");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
				// �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ����һ������
	 * @param line
	 * @return
	 */
	public City handleLineData(String line){
		City city = null;
		//System.out.println(line);// ��ӡ��str1
		//�����ֿ�ͷ
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
