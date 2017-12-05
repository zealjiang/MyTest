package zealjiang.applet;

import java.applet.Applet;
import java.awt.Graphics;

public class Example3_20 extends Applet{

	int pos;
	public void init(){
		System.out.println("init.....");
		pos = 5;
	}
	
	public void start(){
		System.out.println("start.....");
		repaint();
	}
	
	public void stop(){
		System.out.println("stop.....");
		
	}
	
	public void destroy(){
		System.out.println("destroy.....");
		
	}
	public void paint(Graphics g){
		System.out.println("paint.....");
		g.drawString("我们正在学习java程序设计", 20, pos+10);
		pos = (pos+20)%100+5;
	}
}
