package zealjiang.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class MyGraphics extends Applet{
	
	public void paint(Graphics g){
		g.setColor(Color.blue);
		g.drawString("��ӭ��ѧϰJAVA����", 30, 20);
		g.setColor(Color.red);
		g.drawString("ֻҪ����ѧϰ�����ϻ�ʵϰ��һ����ѧ��JAVA���ԡ�", 30, 50);
		g.setColor(Color.black);
		g.drawString("ֻҪ����ѧϰ�����ϻ�ʵϰ��һ����ѧ��JAVA���ԡ�", 50, 80);
	}
}
