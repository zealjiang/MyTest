package zealjiang.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Example6_4 {
	public static void main(String[] args) {
		ComboBoxDemo myCombo = new ComboBoxDemo();
	}
}

class ComboBoxDemo extends JFrame implements ActionListener,ItemListener{

	public static final int Width = 350;
	public static final int Height = 150;
	
	String proList[] = {"踢足球","打篮球","打排球"};
	JTextField text;
	JComboBox comboBox;
	
	public ComboBoxDemo(){
		setSize(Width,Height);
		setTitle("组合框使用示意程序");
		Container conPane = getContentPane();
		conPane.setBackground(Color.LIGHT_GRAY);
		conPane.setLayout(new FlowLayout());
		comboBox = new JComboBox(proList);
		comboBox.addActionListener(this);
		comboBox.addItemListener(this);
		comboBox.setEditable(false);
		conPane.add(comboBox);
		text = new JTextField(10);
		conPane.add(text);
		this.setVisible(true);
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == comboBox){
			text.setText(comboBox.getSelectedItem().toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == comboBox)
			text.setText(comboBox.getSelectedItem().toString());
	}
	
}