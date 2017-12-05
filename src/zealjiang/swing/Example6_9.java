package zealjiang.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class Example6_9 {

	public static void main(String[] args) {
		MyWindow6_9 myWindow = new MyWindow6_9("滚动条示意程序");
	}
}

class MyWindow6_9 extends JFrame{
	
	public MyWindow6_9(String s) {
		// TODO Auto-generated constructor stub
		super(s);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		this.setLocation(100,100);
		JScrollBar xAxi = new JScrollBar(JScrollBar.HORIZONTAL,50,1,0,100);
		JScrollBar yAxi = new JScrollBar(JScrollBar.VERTICAL,50,1,0,100);
		MyListener listener = new MyListener(xAxi,yAxi,238,118);
		JPanel scrollCanvas = new JPanel();
		scrollCanvas.setLayout(new BorderLayout());
		scrollCanvas.add(listener,BorderLayout.CENTER);
		scrollCanvas.add(xAxi,BorderLayout.SOUTH);
		scrollCanvas.add(yAxi,BorderLayout.EAST);
		container.add(scrollCanvas,BorderLayout.CENTER);
		this.setVisible(true);
		this.pack();
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(500,300);
	}
}

class MyListener extends JComponent implements MouseListener,MouseMotionListener
					,AdjustmentListener{
	
	private int x,y;
	private JScrollBar xScrollBar;
	private JScrollBar yScrollBar;
	
	public MyListener(JScrollBar xaBar,JScrollBar yasix,int x0,int y0) {
		// TODO Auto-generated constructor stub
		xScrollBar = xaBar;
		yScrollBar = yasix;
		x = x0;
		y = y0;
		xScrollBar.addAdjustmentListener(this);
		yScrollBar.addAdjustmentListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void paint(Graphics g){
		g.setColor(getBackground());
		Dimension size = getSize();
		g.fillRect(0, 0, size.width, size.height);
		g.setColor(Color.blue);
		g.fillRect(x, y, 50, 50);
	}
	
	private void updateScrollBars(int x,int y){
		int d;
		d = (int) (((float)x/(float)getSize().width)*100.0);
		xScrollBar.setValue(d);
		d = (int) (((float)x/(float)getSize().height)*100.0);
		yScrollBar.setValue(d);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
		updateScrollBars(x, y);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
		updateScrollBars(x, y);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == xScrollBar){
			x = (int)((float)xScrollBar.getValue()/100.0)*getSize().width;
		}else if(e.getSource() == yScrollBar){
			y = (int)((float)yScrollBar.getValue()/100.0)*getSize().height;
		}
		repaint();
	}
	
}