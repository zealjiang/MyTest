package zealjiang.problem.test;

/**
 * 问题描述：现在有100个人，这100个人的编号分别是1到100，有100盏灯，这100盏灯的编号也是从1到100，编号是i的人能操作编号是i整数倍的灯，一开始所有的灯是灭的，问你"这100个人操作一遍后，有几盏
 * 灯是亮的？如果再操作一遍呢？"
 * @author zealjiang
 *
 */
public class Light {

	boolean[] lights = new boolean[100];
	static Light light = new Light();
	public static void main(String[] args) {
		
		//最初100盏灯都是灭的
		light.intialLights(false);
		light.operateNumTimes(2);
	}
	
	/**
	 * 获得第num个人按可按的灯后，所有灯的亮灭状态(其中可按的灯的编号是num的倍数)
	 * @param num 第num个人
	 * @param max 灯的个数
	 * @return 返回第num个人按可按的灯后，所有灯的亮灭状态
	 */
	private boolean[] getLightsState(int num,int max){
		for(int j=num;j<max+1;j++){
			
			if(j%num==0){
				if(lights[j-1]){
					lights[j-1] = false;
				}else{
					lights[j-1] = true;
				}			
			}	
		}
		return lights;
	}
	
	/**
	 * 初始所有的灯，如果boo为true,置所有的灯为亮，反之为灭
	 * @param boo
	 */
	private void intialLights(boolean boo){
		if(boo){
			for(int j=0;j<100;j++){
				lights[j] = true;
			}
		}else{
			for(int j=0;j<100;j++){
				lights[j] = false;
			}
		}

	}
	
	/**
	 * 获得状态为亮的灯的盏数
	 * @return  返回状态为亮的灯的盏数
	 */
	private int getShineLightsnum(){
		int num = 0;
		for(int j=0;j<100;j++){
			if(lights[j]){
				num++;
			}
		}
		return num;
	}
	
	/**
	 * 获得状态为亮的灯的盏数
	 * @return  返回状态为亮的灯的盏数
	 */
	private int getPutoutLightsnum(){
		int num = 0;
		for(int j=0;j<100;j++){
			if(!lights[j]){
				num++;
			}
		}
		return num;
	}
	
	/**
	 * 打印出当前所有灯的亮灭状态
	 * @return 返回当前所有灯的亮灭状态的信息数组，true表示亮，false表示灭
	 */
	private String printLightsState(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int j=0;j<lights.length;j++){
			sb.append(lights[j]+",");
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 执行num次这样的操作
	 */
	private void operateNumTimes(int num){
		
		for(int i=0;i<num;i++){
			System.out.println("-------第"+(i+1)+"遍----------");
			//从第一个孩子开始
			for(int j=1;j<=100;j++){			
				light.getLightsState(j,100);
				System.out.println("第"+j+"个人操作过灯后所有亮灯的个数为"+light.getShineLightsnum());
				
				System.out.println("第"+j+"个人操作过灯后所有亮灯的状态为:"+light.printLightsState());
			}
			
		}

	}
}
