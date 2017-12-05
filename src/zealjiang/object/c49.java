package zealjiang.object;

import java.applet.Applet;
import java.awt.Graphics;

public class c49 extends Applet{
	
	public void paint(Graphics g){
		Ca m1 = new Ca();
		Ca m2 = new Ca();
		g.drawString("m2.nn="+m2.nn, 20, 30);
		g.drawString("m2.k="+m2.k, 20, 50);
		g.drawString("m1.nn="+m1.nn, 20, 70);
		g.drawString("m1.k="+m2.k, 20, 90);
	}

	
}