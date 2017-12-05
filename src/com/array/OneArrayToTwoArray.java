package com.array;

public class OneArrayToTwoArray {

	static String[] provinceID_array = null;
	static String[] provinceNAME_array = null;
	public static void main(String[] args) {
		String province_data = "110000000000,北京市,340000000000,安徽省,350000000000,福建省,120000000000,天津市," +
				"130000000000,河北省,140000000000,山西省,150000000000,内蒙古自治区,210000000000,辽宁省," +
				"220000000000,吉林省,230000000000,黑龙江省,310000000000,上海市,320000000000," +
				"江苏省,330000000000,浙江省,440000000000,广东省,360000000000,江西省,370000000000," +
				"山东省,520000000000,贵州省,530000000000,云南省,450000000000,广西壮族自治区,460000000000," +
				"海南省,500000000000,重庆市,420000000000,湖北省,430000000000,湖南省,410000000000,河南省," +
				"510000000000,四川省,540000000000,西藏自治区,610000000000,陕西省,620000000000,甘肃省," +
				"630000000000,青海省,640000000000,宁夏回族自治区,650000000000,新疆维吾尔自治区,";

		String[] pro = province_data.split(","); 
		
		provinceID_array = new String[pro.length/2];
		provinceNAME_array = new String[pro.length/2];
		int j = 0, k = 0;
		for(int i=0;i<pro.length;i++){
			if(i%2==0){				
				provinceID_array[j] = pro[i];
				j++;
			}else{
				provinceNAME_array[k] = pro[i];
				k++;
			}
		}
		
		for(int i=0;i<provinceID_array.length;i++){
			System.out.println("provinceID_array ："+provinceID_array[i]);
		}
	}

	
}
