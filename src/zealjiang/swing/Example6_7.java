package zealjiang.swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class Example6_7 {

	public static void main(String[] args) {
		MyWindow6_7 myWindow = new MyWindow6_7("������ʵ��");
	}
}

class MyScrollBar extends JScrollBar{
	public MyScrollBar(int init,int len,int low,int high){
		super(JScrollBar.HORIZONTAL,init,len,low,high);
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(125,20);
	}
}

class MyWindow6_7 extends JFrame implements ActionListener,AdjustmentListener{

	private JButton button;
	private JTextField text;
	private boolean barOpened;
	
	MyWindow6_7(String s){
		super(s);
		MyScrollBar tempBar = new MyScrollBar(10,10,0,255);
		Container con = this.getContentPane();
		con.setLayout(new GridLayout(2,1));
		this.setSize(200,100);
		this.setLocation(100,100);
		button = new JButton("��/�չ�����");
		button.addActionListener(this);
		barOpened = false;
		tempBar.addAdjustmentListener(this);
		text = new JTextField("�������ر�",20);
		con.add(button);
		con.add(text);
		con.add(tempBar);
		this.setVisible(true);
		this.pack();
	}
	
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		if(barOpened){
			MyScrollBar myBar = (MyScrollBar) e.getAdjustable();
			text.setText("ѡ���ֵ�ǣ�"+myBar.getValue());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button){
			if(barOpened){
				text.setText("�������ر�");
			}else 
				text.setText("��������");
			barOpened = ! barOpened;
		}
			
	}
	
}
