package zealjiang.object;

/** 
 * ���ø�ֵ,ʵ�����Ƕ���ĸ���
 * ע�⣺���һ������ָ������һ��������һ����������Է����˱仯
 * �������Ҳ���ŷ����仯
 * @author zealjiang
 * @time 2016��5��10������3:43:08
 */
public class QuoteAssign {

	public static void main(String[] args) {
		QuoteAssign qa = new QuoteAssign();
		qa.quoteAssign();
	}
	
	private void quoteAssign(){
		PosVal pv2 = null;
		PosVal pv1 = new PosVal();
		pv1.setBytePoi(1);
		pv1.setValue(20);
		
		pv2 = pv1;
		System.out.println("pv2: "+pv2.value);
		pv1.setBytePoi(3);
		pv1.setValue(34);
		System.out.println("pv2: "+pv2.value);
		pv1 = null;
		System.out.println("pv1: "+pv1);
	    System.out.println("pv2: "+pv2);
	    System.out.println("pv2: "+pv2.value);
	    
	}
	
	class PosVal{
		private int bytePoi;
		private int packetPoi;
		private int value;
		
		public int getBytePoi() {
			return bytePoi;
		}
		public void setBytePoi(int bytePoi) {
			this.bytePoi = bytePoi;
		}
		public int getPacketPoi() {
			return packetPoi;
		}
		public void setPacketPoi(int packetPoi) {
			this.packetPoi = packetPoi;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int maxValue) {
			this.value = maxValue;
		}	
	}
}
