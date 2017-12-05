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
		button = new JButton("打开我的体育娱乐之窗");
		bflag = true;
		window = new MenuWindow("体育娱乐之窗",100,100);
		button.addActionListener(this);
		add(button);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(bflag){
			window.setVisible(true);
			bflag = false;
			button.setLabel("关闭我的体育娱乐之窗");
		}else {
			window.setVisible(false);
			bflag = true;
			button.setLabel("打开我的体育娱乐之窗");
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
		JMenu menu1 = new JMenu("体育");
		addItem(menu1,"跑步",this);
		addItem(menu1,"跳绳",this);
		addItem(menu1,"打球",this);
		
		JMenu menu2 = new JMenu("娱乐");
		addItem(menu2,"唱歌",this);
		addItem(menu2,"跳舞",this);
		addItem(menu2,"游戏",this);
		
		JCheckBoxMenuItem item1 = new JCheckBoxMenuItem("骑马");
		item1.addActionListener(this);
		item1.setActionCommand("骑马");
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
		text.setText(e.getActionCommand()+"菜单项被选中！");
		if(e.getActionCommand().equals("游戏")){

		}
	}
	
}
