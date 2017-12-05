package zealjiang.swing;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class Example8_3 extends Applet implements Runnable{

	Thread redBall,blueBull;
	Graphics redPen,bluePen;
	int blueSeta = 0,redSeta = 0;
	
	public void init(){
		setSize(250,200);
		redBall = new Thread(this);
		blueBull = new Thread(this);
		redPen = getGraphics();
		bluePen = getGraphics();
		redPen.setColor(Color.red);
		bluePen.setColor(Color.blue);
		setBackground(Color.gray);
	}
	
	public void start(){
		redBall.start();
		blueBull.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int x,y;
		while(true){
			if(Thread.currentThread() == redBall){
				x = (int)(80.0*Math.cos(3.1415926/180.0*redSeta));
				y = (int)(80.0*Math.sin(3.1415926/180.0*redSeta));
				
				redPen.setColor(Color.gray);
				redPen.fillOval(100+x, 100+y, 10, 10);
				redSeta += 3;
				if(redSeta >= 360)
					redSeta = 0;
				
				x = (int)(80.0*Math.cos(3.1415926/180.0*redSeta));
				y = (int)(80.0*Math.sin(3.1415926/180.0*redSeta));
				
				redPen.setColor(Color.red);
				redPen.fillOval(100+x, 100+y, 10, 10);
				
				try {
					redBall.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(Thread.currentThread() == blueBull){
				x = (int)(80.0*Math.cos(3.1415926/180.0*blueSeta));
				y = (int)(80.0*Math.sin(3.1415926/180.0*blueSeta));
				
				bluePen.setColor(Color.gray);
				bluePen.fillOval(100+x, 100+y, 10, 10);
				blueSeta -= 3;
				if(blueSeta <= -360)
					blueSeta = 0;
				
				x = (int)(80.0*Math.cos(3.1415926/180.0*blueSeta));
				y = (int)(80.0*Math.sin(3.1415926/180.0*blueSeta));
				
				bluePen.setColor(Color.blue);
				bluePen.fillOval(150+x, 100+y, 10, 10);
				try {
					blueBull.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
