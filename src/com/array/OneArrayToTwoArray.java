package com.array;

public class OneArrayToTwoArray {

	static String[] provinceID_array = null;
	static String[] provinceNAME_array = null;
	public static void main(String[] args) {
		String province_data = "110000000000,������,340000000000,����ʡ,350000000000,����ʡ,120000000000,�����," +
				"130000000000,�ӱ�ʡ,140000000000,ɽ��ʡ,150000000000,���ɹ�������,210000000000,����ʡ," +
				"220000000000,����ʡ,230000000000,������ʡ,310000000000,�Ϻ���,320000000000," +
				"����ʡ,330000000000,�㽭ʡ,440000000000,�㶫ʡ,360000000000,����ʡ,370000000000," +
				"ɽ��ʡ,520000000000,����ʡ,530000000000,����ʡ,450000000000,����׳��������,460000000000," +
				"����ʡ,500000000000,������,420000000000,����ʡ,430000000000,����ʡ,410000000000,����ʡ," +
				"510000000000,�Ĵ�ʡ,540000000000,����������,610000000000,����ʡ,620000000000,����ʡ," +
				"630000000000,�ຣʡ,640000000000,���Ļ���������,650000000000,�½�ά���������,";

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
			System.out.println("provinceID_array ��"+provinceID_array[i]);
		}
	}

	
}
