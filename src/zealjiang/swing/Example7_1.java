package zealjiang.swing;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

public class Example7_1 extends Applet{
	
	Font f7 = new Font("Helvetica",Font.PLAIN,10);
	Font f1 = new Font("Helvetica",Font.PLAIN,18);
	Font f2 = new Font("Helvetica",Font.BOLD,10);
	Font f3 = new Font("Helvetica",Font.ITALIC,12);
	Font f4 = new Font("Courier",Font.PLAIN,12);
	Font f5 = new Font("TimesRoman",Font.BOLD+Font.ITALIC,14);
	Font f6 = new Font("Dialog",Font.ITALIC,14);
	
	public void paint(Graphics g){
		setSize(250,200);
		g.setFont(f1);
		g.drawString("18pt plain Helvetica", 5, 20);
		g.setFont(f2);
		g.drawString("10pt bold Helvetica", 5, 43);
		g.setFont(f3);
		g.drawString("12pt itatic Helvetica", 5, 58);
		g.setFont(f4);
		g.drawString("12pt plain Helvetica", 5, 75);
		g.setFont(f5);
		g.drawString("14pt bold & itatic Times Roman", 5, 92);
		g.setFont(f6);
		g.drawString("14pt itatic Dialog", 5, 111);
		g.setFont(f7);
		g.drawString("14pt plain Dialog", 5, 130);
		
		g.drawLine(3,153,150,153);
		g.drawLine(160,160,160,160);
	}
}
