package zealjiang.test;

import javax.swing.JOptionPane;

public class java_1 {
	public static void main(String args[]) {
		int x, y, result;
		String xval, yval;
		xval = JOptionPane.showInputDialog("�����1������:");
		yval = JOptionPane.showInputDialog("�����2������:");
		x = Integer.parseInt(xval);
		y = Integer.parseInt(yval);
		result = x * y;
		JOptionPane.showMessageDialog(null, "�������Ļ�: " + result);
		System.exit(0);
	}
}
