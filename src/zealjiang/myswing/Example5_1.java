package zealjiang.myswing;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Example5_1 {

	public static void main(String[] args) {
		JFrame mw = new JFrame("�ҵĵ�һ������");
		mw.setSize(250, 200);
		JButton button = new JButton("����һ����ť");
		mw.getContentPane().add(button);
		mw.setVisible(true);
	}
}
