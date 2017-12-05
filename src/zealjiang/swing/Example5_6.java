package zealjiang.swing;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.byteArray.intToByteArray;

public class Example5_6 extends Applet{

	MyWindow myWindow;
	public void init(){
		myWindow = new MyWindow(400, 350);
	}
}

class MyWindow extends JFrame{
	public MyWindow(int w,int h) {
		// TODO Auto-generated constructor stub
		setTitle("滚动面板实例");
		Container con = getContentPane();
		con.setPreferredSize(new Dimension(w, h));
		con.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		
		p.setLayout(new GridLayout(6, 6));
		for(int i=0;i<6;i++){
			p.add(new JLabel("JLabel"));
			for(int j=1;j<=2;j++){
				p.add(new JButton("按钮"+(2*i+j)));
				p.add(new JLabel("标签"+(2*i+j)));
			}
			p.add(new JLabel("JLabel"));
		}
		p.setBackground(Color.yellow);
		//p.setPreferredSize(new Dimension(w+200, h+60));
		JScrollPane scrollPane = new JScrollPane(p);
		scrollPane.setPreferredSize(new Dimension(w-260, h-60));
		add(scrollPane,BorderLayout.CENTER);
		setVisible(true);
		
		pack();
	}
}