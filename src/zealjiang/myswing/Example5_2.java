package zealjiang.myswing;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Example5_2 {

	public static MyWindowDemo mw1;
	public static MyWindowDemo mw2;
	public static void main(String[] args) {
		JButton but1 = new JButton("我是一个按钮");
		String name1= "我是第一个窗口";
		String name2= "我是第二个窗口";
		mw1 = new MyWindowDemo(name1,but1,Color.blue,350,450);
		//mw1.pack();
		mw1.setVisible(true);
		JButton but2 = new JButton("我是另一个按钮");
		mw2 = new MyWindowDemo(name2,but2,Color.magenta,30,450);
		mw2.pack();
		mw2.setVisible(true);
	}
}

class MyWindowDemo extends JFrame{
	public MyWindowDemo(String name,JButton button,Color color,int w,int h){
		super();
		setTitle(name);
		setSize(w, h);
		Container contenPane = getContentPane();
		contenPane.add(button);
		contenPane.setBackground(color);
	}
}