package zealjiang.object;

/** 
 * 引用赋值,实际是是对象的复制
 * 注意：如果一个对象指向了另一个对象，另一个对象的属性发生了变化
 * 这个对象也跟着发生变化
 * @author zealjiang
 * @time 2016年5月10日下午3:43:08
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
