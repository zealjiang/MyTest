package zealjiang.test;

public class Test2 {

	public static void main(String[] args) {
		
		String ss = "bc2472df973c01,130481000000,30,22,0,01,02,13161909610,06,,,�����к�����," +
				"2011��01��01��,02,,2011��05��03��,01,06,1000000,1,1,    4 , 1  4 ,|" +
				"��������,C0000,,C00002034,,110108000000,���,130481198608046935,1,01," +
				"19860804000000,�ӱ�ʡ������,1,1, ,20120229091113,| , , , ,";
		getJcxzdw(ss);

		
		
	}
	
	/**
	 * ��λ�������
	 * @param data �����string����
	 * @param mySharedPreferences  SharedPreferences����
	 * @return ��ά����
	 */
	public static String[][] getJcxzdw(String data){
		
		String[][] arrayss = new String[1][50];
		String[] array = data.split("\\|");
		String[] dwxzym = array[0].split(",");//��λ����ҳ������
		String[] dwqtsj = array[1].split(",");//��λ����ҳ��֮ǰ������
		String[] fwxx = array[2].split(",");//��ʵ������Ϣ������
	
		for (int k = 0; k < dwxzym.length; k++) {
			System.out.println("dwxzym :["+k+"]"
					+ dwxzym[k]);
		}
		
		for (int k = 0; k < dwqtsj.length; k++) {
			System.out.println("dwqtsj :["+k+"]"
					+ dwqtsj[k]);
		}
		
		for (int k = 0; k < fwxx.length; k++) {
			System.out.println("fwxx :["+k+"]"
					+ fwxx[k]);
		}
		
		return arrayss;
		
	}
}
