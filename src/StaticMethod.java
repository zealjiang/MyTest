
/**
 * static��̬�����ֻ����.class��һ�μ���ʱִ��һ�Σ������������������ص�
 *
 */
public class StaticMethod {

	static{
		System.out.println("����һ��static����");
		for(int i=0 ;i<10; i++){
			System.out.println("i :" +i);
		}
	}

	public static void main(String[] args) {
		System.out.println("main");
		
	}

}
