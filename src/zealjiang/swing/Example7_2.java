package zealjiang.swing;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class Example7_2 extends Applet{

	public void paint(Graphics g){
		setSize(380,200);
		for(int i=0;i<=10;i++){
			Color myredcolor = new Color(i*25+5,0,0); 
			g.setColor(myredcolor);
			g.fillRect(i*32+5, 2, 28, 28);
		}
		
		for(int i=0;i<=10;i++){
			Color myredcolor = new Color(0,i*25+5,0); 
			g.setColor(myredcolor);
			g.fillRect(i*32+5, 32, 28, 28);
		}
		
		for(int i=0;i<=10;i++){
			Color myredcolor = new Color(0,0,i*25+5); 
			g.setColor(myredcolor);
			g.fillRect(i*32+5, 62, 28, 28);
		}
	}
}
