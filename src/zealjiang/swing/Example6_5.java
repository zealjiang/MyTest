package zealjiang.swing;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class Example6_5 extends Applet implements ActionListener{

	MenuWindow window;
	JButton button;
	boolean bflag;
	
	public void init(){
		button = new JButton("���ҵ���������֮��");
		bflag = true;
		window = new MenuWindow("��������֮��",100,100);
		button.addActionListener(this);
		add(button);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(bflag){
			window.setVisible(true);
			bflag = false;
			button.setLabel("�ر��ҵ���������֮��");
		}else {
			window.setVisible(false);
			bflag = true;
			button.setLabel("���ҵ���������֮��");
		}
	}

}

class MenuWindow extends JFrame implements ActionListener{

	public static JTextField text;
	private void addItem(JMenu menu,String menuName,ActionListener listener){
		
		
		
		JMenuItem anItem = new JMenuItem(menuName);
		anItem.addActionListener(listener);
		anItem.setActionCommand(menuName);
		menu.add(anItem);
	}
	
	MenuWindow(String s,int w,int h){
		setTitle(s);
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());
		setLocation(100,100);
		setSize(w,h);
		JMenu menu1 = new JMenu("����");
		addItem(menu1,"�ܲ�",this);
		addItem(menu1,"����",this);
		addItem(menu1,"����",this);
		
		JMenu menu2 = new JMenu("����");
		addItem(menu2,"����",this);
		addItem(menu2,"����",this);
		addItem(menu2,"��Ϸ",this);
		
		JCheckBoxMenuItem item1 = new JCheckBoxMenuItem("����");
		item1.addActionListener(this);
		item1.setActionCommand("����");
		menu2.add(item1);
		
		JMenuBar menubar = new JMenuBar();
		text = new JTextField();
		menubar.add(menu1);
		menubar.add(menu2);
		setJMenuBar(menubar);
		con.add(text,BorderLayout.NORTH);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		text.setText(e.getActionCommand()+"�˵��ѡ�У�");
		if(e.getActionCommand().equals("��Ϸ")){

		}
	}
	
}
