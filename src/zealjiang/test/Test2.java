package zealjiang.test;

public class Test2 {

	public static void main(String[] args) {
		
		String ss = "bc2472df973c01,130481000000,30,22,0,01,02,13161909610,06,,,北京市海淀区," +
				"2011年01月01日,02,,2011年05月03日,01,06,1000000,1,1,    4 , 1  4 ,|" +
				"北控三兴,C0000,,C00002034,,110108000000,孙楠,130481198608046935,1,01," +
				"19860804000000,河北省邯郸市,1,1, ,20120229091113,| , , , ,";
		getJcxzdw(ss);

		
		
	}
	
	/**
	 * 单位简采新增
	 * @param data 传入的string数据
	 * @param mySharedPreferences  SharedPreferences对象
	 * @return 二维数据
	 */
	public static String[][] getJcxzdw(String data){
		
		String[][] arrayss = new String[1][50];
		String[] array = data.split("\\|");
		String[] dwxzym = array[0].split(",");//单位新增页面数据
		String[] dwqtsj = array[1].split(",");//单位新增页面之前的数据
		String[] fwxx = array[2].split(",");//核实房屋信息的数据
	
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
