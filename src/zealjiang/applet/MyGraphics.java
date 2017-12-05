package zealjiang.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class MyGraphics extends Applet{
	
	public void paint(Graphics g){
		g.setColor(Color.blue);
		g.drawString("欢迎你学习JAVA语言", 30, 20);
		g.setColor(Color.red);
		g.drawString("只要认真学习，多上机实习，一定能学好JAVA语言。", 30, 50);
		g.setColor(Color.black);
		g.drawString("只要认真学习，多上机实习，一定能学好JAVA语言。", 50, 80);
	}
}
