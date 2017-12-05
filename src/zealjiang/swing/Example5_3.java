package zealjiang.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Example5_3 {

	public static void main(String[] args) {
		ButtonDemo mDemo = new ButtonDemo();
		mDemo.setVisible(true);
	}
}

class ButtonDemo extends JFrame implements ActionListener{

	public static final int Width = 250;
	public static final int Height = 200;
	
	public ButtonDemo() {
		// TODO Auto-generated constructor stub
		setSize(Width, Height);
		Container conPane = getContentPane();
		conPane.setBackground(Color.BLUE);
		conPane.setLayout(new FlowLayout());
		JButton redButton = new JButton("red");
		redButton.addActionListener(this);
		conPane.add(redButton);
		
		JButton greenButton = new JButton("green");
		greenButton.addActionListener(this);
		conPane.add(greenButton);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Container conPane = getContentPane();
		if(e.getActionCommand().equals("red")){
			conPane.setBackground(Color.red);
		}else if(e.getActionCommand().equals("green"))
			conPane.setBackground(Color.GREEN);
		else{};
	}
	
}