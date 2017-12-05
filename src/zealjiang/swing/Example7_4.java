package zealjiang.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Arc2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Example7_4 extends JFrame{

	public static void main(String[] args) {
		GraphicsDemo my = new GraphicsDemo();
	}
}

class ShapesPanel extends JPanel{
	
	public ShapesPanel() {
		// TODO Auto-generated constructor stub
		setBackground(Color.white);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		setBackground(Color.yellow);
		g.setXORMode(Color.red);
		g.setColor(Color.green);
		g.fillRect(20, 20, 80, 40);
		g.setColor(Color.yellow);
		g.fillRect(60, 20, 80, 40);
		g.setColor(Color.green);
		g.fillRect(20, 70, 80, 40);
		g.fillRect(60, 70, 80, 40);
		
		g.setColor(Color.green);
		g.drawLine(80,100,180,200);
		g.drawLine(100,100,200,200);
		
		g.drawLine(140, 140, 220, 220);
		g.setColor(Color.yellow);
		g.drawLine(20, 30, 160, 30);
		g.drawLine(20, 75, 160, 75);

	}
}

class GraphicsDemo extends JFrame{
	public GraphicsDemo() {
		// TODO Auto-generated constructor stub
		this.getContentPane().add(new ShapesPanel());
		setTitle("基本绘图方法演示");
		setSize(300,300);
		setVisible(true);
	}
}