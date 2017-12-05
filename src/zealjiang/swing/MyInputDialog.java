package zealjiang.swing;

import javax.swing.JOptionPane;

public class MyInputDialog {

	public static void main(String[] args) {
		String result = (String) JOptionPane.showInputDialog(
				null, "请输入一个整数","输入对话框",JOptionPane.PLAIN_MESSAGE,
				null,null,null);
		int n = Integer.parseInt(result);
		System.out.println("n-->"+n);
		
	}
}
