package zealjiang.test;

import javax.swing.JOptionPane;

public class java_1 {
	public static void main(String args[]) {
		int x, y, result;
		String xval, yval;
		xval = JOptionPane.showInputDialog("输入第1个整数:");
		yval = JOptionPane.showInputDialog("输入第2个整数:");
		x = Integer.parseInt(xval);
		y = Integer.parseInt(yval);
		result = x * y;
		JOptionPane.showMessageDialog(null, "两个数的积: " + result);
		System.exit(0);
	}
}
