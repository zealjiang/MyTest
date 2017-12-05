package zealjiang.swing;

import java.applet.Applet;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;



public class Example6_2 extends Applet{


	MyWindow2 myWindow = new MyWindow2("选择项目处理示例程序");
	
	

}


class Panel1 extends JPanel{
	JRadioButton box1,box2,box3;
	ButtonGroup group;
	Panel1(){
		setLayout(new GridLayout(1, 4));
		group = new ButtonGroup();
		box1 = new JRadioButton(MyWindow2.fName[0]+"计算机",false);
		box2 = new JRadioButton(MyWindow2.fName[1]+"计算机",false);
		box3 = new JRadioButton(MyWindow2.fName[2]+"计算机",false);
		group.add(box1);group.add(box2);group.add(box3);
		add(box1);add(box2);add(box3);
		add(new JLabel("计算机3选1"));
	}
}

class Panel2 extends JPanel{
	JCheckBox box1,box2,box3;
	ButtonGroup group;
	public Panel2() {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(1,3));
		group = new ButtonGroup();
		box1 = new JCheckBox("购买1台");
		box2 = new JCheckBox("购买2台");
		box3 = new JCheckBox("购买3台");
		group.add(box1);
		group.add(box2);
		group.add(box3);
		add(box1);add(box2);add(box3);
		add(new JLabel("选择1、2或3"));
	}
}
class MyWindow2 extends JFrame implements ItemListener{


	Panel1 panel1;
	Panel2 panel2;
	JLabel label1,label2;
	JTextArea text1,text2;
	static  String[] fName = {"HP","IBM","DELL"};
	static  double priTbl[][] = {{1.20,1.15,1.10},{1.70,1.65,1.60},
		{1.65,1.60,1.58}};
	static int production = -1;
	MyWindow2(String s){
		super(s);
		Container container = getContentPane();
		container.setLayout(new GridLayout(3,2));
		this.setLocation(100, 100);
		this.setSize(400,100);
		panel1 = new Panel1(); 
		panel2 = new Panel2();
		label1 = new JLabel("产品介绍",JLabel.CENTER);
		label2 = new JLabel("产品价格",JLabel.CENTER);
		text1 = new JTextArea(); 
		text2 = new JTextArea();
		container.add(label1);
		container.add(label2);
		container.add(panel1);
		container.add(panel2);
		container.add(text1);
		container.add(text2);
		
		panel1.box1.addItemListener(this);
		panel1.box2.addItemListener(this);
		panel1.box3.addItemListener(this);
		panel2.box1.addItemListener(this);
		panel2.box2.addItemListener(this);
		panel2.box3.addItemListener(this);
		this.setVisible(true);
		this.pack();
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getItemSelectable()==panel1.box1){
			production = 0;
			text1.setText(fName[0]+"公司生产");
			text2.setText("");
		}else if(e.getItemSelectable()==panel1.box2){
			production = 1;
			text1.setText(fName[1]+"公司生产");
			text2.setText("");
		}else if(e.getItemSelectable()==panel1.box3){
			production = 2;
			text1.setText(fName[2]+"公司生产");
			text2.setText("");
		}else{
			if(production == -1)return;
			if(e.getItemSelectable()==panel2.box1){
				text2.setText(""+priTbl[production][0]+"万元/台");
			}else if(e.getItemSelectable() ==panel2.box2){
				text2.setText(""+priTbl[production][1]+"万元/台");
			}else if(e.getItemSelectable() ==panel2.box3){
				text2.setText(""+priTbl[production][2]+"万元/台");
			} 
		}
	}	
}