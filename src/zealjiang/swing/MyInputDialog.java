package zealjiang.swing;

import javax.swing.JOptionPane;

public class MyInputDialog {

	public static void main(String[] args) {
		String result = (String) JOptionPane.showInputDialog(
				null, "������һ������","����Ի���",JOptionPane.PLAIN_MESSAGE,
				null,null,null);
		int n = Integer.parseInt(result);
		System.out.println("n-->"+n);
		
	}
}
