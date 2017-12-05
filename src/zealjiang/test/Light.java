package zealjiang.test;

import com.byteArray.intToByteArray;

/**
 * ����������������100���ˣ���100���˵ı�ŷֱ���1��100����100յ�ƣ���100յ�Ƶı��Ҳ�Ǵ�1��100�������i�����ܲ��������i�������ĵƣ�һ��ʼ���еĵ�����ģ�����"��100���˲���һ����м�յ
 * �������ģ�����ٲ���һ���أ�"
 * @author zealjiang
 *
 */
public class Light {
	int children = 121;
	int lightss = 121;
	boolean[] lights = new boolean[lightss];
	static Light light = new Light();
	public static void main(String[] args) {
		
		//���100յ�ƶ������
		light.intialLights(false);
		light.operateNumTimes(1);
	}
	
	/**
	 * ��õ�num���˰��ɰ��ĵƺ����еƵ�����״̬(���пɰ��ĵƵı����num�ı���)
	 * @param num ��num����
	 * @param max �Ƶĸ���
	 * @return ���ص�num���˰��ɰ��ĵƺ����еƵ�����״̬
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
	 * ��ʼ���еĵƣ����booΪtrue,�����еĵ�Ϊ������֮Ϊ��
	 * @param boo
	 */
	private void intialLights(boolean boo){
		if(boo){
			for(int j=0;j<lightss;j++){
				lights[j] = true;
			}
		}else{
			for(int j=0;j<lightss;j++){
				lights[j] = false;
			}
		}

	}
	
	/**
	 * ���״̬Ϊ���ĵƵ�յ��
	 * @return  ����״̬Ϊ���ĵƵ�յ��
	 */
	private int getShineLightsnum(){
		int num = 0;
		for(int j=0;j<lightss;j++){
			if(lights[j]){
				num++;
			}
		}
		return num;
	}
	
	/**
	 * ���״̬Ϊ���ĵƵ�յ��
	 * @return  ����״̬Ϊ���ĵƵ�յ��
	 */
	private int getPutoutLightsnum(){
		int num = 0;
		for(int j=0;j<lightss;j++){
			if(!lights[j]){
				num++;
			}
		}
		return num;
	}
	
	/**
	 * ��ӡ����ǰ���еƵ�����״̬
	 * @return ���ص�ǰ���еƵ�����״̬����Ϣ���飬true��ʾ����false��ʾ��
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
	 * ִ��num�������Ĳ���
	 */
	private void operateNumTimes(int num){
		
		for(int i=0;i<num;i++){
			System.out.println("-------��"+(i+1)+"��----------");
			//�ӵ�һ�����ӿ�ʼ
			for(int j=1;j<=children;j++){			
				light.getLightsState(j,children);
				System.out.println("��"+j+"���˲������ƺ��������Ƶĸ���Ϊ"+light.getShineLightsnum());
				
				System.out.println("��"+j+"���˲������ƺ��������Ƶ�״̬Ϊ:"+light.printLightsState());
			}
			
		}

	}
}
