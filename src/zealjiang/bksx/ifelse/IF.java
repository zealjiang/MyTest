package zealjiang.bksx.ifelse;

public class IF {

	public static void main(String[] args) {
		IF ifObj = new IF();
		ifelse();
		ifObj.notEqual();
		ifObj.notEqual2();
		ifObj.calHearRate();
	}
	
	public static void ifelse(){
		int i = 1;
		if(i>0){
			System.out.println("i>0");
		}else if(i==1){
			System.out.println("i==1");
		}else{
			System.out.println("i!=1");
		}
	}
	
	public void notEqual(){
		int mRecursion = -1;
		if(mRecursion!=0||mRecursion!=-1){
			//�˳���û�ҵ�
			mRecursion = 0;
			System.out.println("notEqual �˳�");
			return;
		}
		mRecursion = -1;
	}
	
	public void notEqual2(){
		int mRecursion = -1;
		if(mRecursion==1){
			//�˳���û�ҵ�
			mRecursion = 0;
			System.out.println("notEqual2 �˳�");
			return;
		}
		mRecursion = -1;
		System.out.println("notEqual2 û���˳�");
	}
	
	private void calHearRate(){
		IF IF1 = new IF();
		IF IF2 = null;
		if(null==IF1||null==IF2){
			System.out.println("IF1��IF2��null");
		}
	}
	
}
