package zealjiang.swing;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextArea;

public class Example8_2 extends Applet implements Runnable{

	Thread myThread = null;
	JTextArea t;
	int k;
	public void start() {
		t = new JTextArea(20,20);
		add(t);
		k = 0;
		setSize(500,400);
		if(myThread == null){
			myThread = new Thread(this);
			myThread.start();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (myThread !=null) {
			try {
				myThread.sleep(1000);
				k++;
			} catch (Exception e) {
				// TODO: handle exception
			}
			repaint();
		}
	}
	
	public void paint(Graphics g){
		double i = Math.random();
		if(i<0.5)
			g.setColor(Color.yellow);
		else {
			g.setColor(Color.blue);
		}
		g.fillOval(10,10, (int)(100*i), (int)(100*i));
		t.append("我在工作，已经休息了"+k+"次\n");
	}
	
	public void stop() {
		if(myThread != null){
			myThread.stop();
			myThread = null;
		}
	}

}
