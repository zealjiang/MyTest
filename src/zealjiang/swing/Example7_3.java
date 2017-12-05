package zealjiang.swing;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Locale;

import javax.accessibility.AccessibleContext;

public class Example7_3 extends Applet{

	int i = 1;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public AccessibleContext getAccessibleContext() {
		// TODO Auto-generated method stub
		return super.getAccessibleContext();
	}

	@Override
	public AppletContext getAppletContext() {
		// TODO Auto-generated method stub
		return super.getAppletContext();
	}

	@Override
	public String getAppletInfo() {
		// TODO Auto-generated method stub
		return super.getAppletInfo();
	}

	@Override
	public AudioClip getAudioClip(URL url, String name) {
		// TODO Auto-generated method stub
		return super.getAudioClip(url, name);
	}

	@Override
	public AudioClip getAudioClip(URL url) {
		// TODO Auto-generated method stub
		return super.getAudioClip(url);
	}

	@Override
	public URL getCodeBase() {
		// TODO Auto-generated method stub
		return super.getCodeBase();
	}

	@Override
	public URL getDocumentBase() {
		// TODO Auto-generated method stub
		return super.getDocumentBase();
	}

	@Override
	public Image getImage(URL url, String name) {
		// TODO Auto-generated method stub
		return super.getImage(url, name);
	}

	@Override
	public Image getImage(URL url) {
		// TODO Auto-generated method stub
		return super.getImage(url);
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return super.getLocale();
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return super.getParameter(name);
	}

	@Override
	public String[][] getParameterInfo() {
		// TODO Auto-generated method stub
		return super.getParameterInfo();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		setBackground(Color.yellow);
		System.out.println("....init");
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return super.isActive();
	}

	@Override
	public void play(URL url, String name) {
		// TODO Auto-generated method stub
		super.play(url, name);
	}

	@Override
	public void play(URL url) {
		// TODO Auto-generated method stub
		super.play(url);
	}

	@Override
	public void resize(Dimension d) {
		// TODO Auto-generated method stub
		super.resize(d);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void showStatus(String msg) {
		// TODO Auto-generated method stub
		super.showStatus(msg);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("....start");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("....paint");
		i = i+8;
		if(i>160)
			i = 1;
		g.setColor(Color.red);
		g.fillRect(i, 10, 20, 20);
		g.drawString("我正在学习update()方法！", 100, 100);
		try{
			Thread.sleep(1000);
		}catch (Exception e) {
			// TODO: handle exception			
		}
		repaint();
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("....update");
		g.clearRect(i, 10, 200, 100);
		
		
/*		try{
			Thread.sleep(1000);
		}catch (Exception e) {
			// TODO: handle exception			
		}*/
		paint(g);
	}

	
}
