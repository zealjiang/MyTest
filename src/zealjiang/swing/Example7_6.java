package zealjiang.swing;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.byteArray.intToByteArray;

public class Example7_6 extends Applet{

	Image myPicture;
	public void init(){
		myPicture = getImage(getCodeBase(),"zealjing/swing/myPic.JPG");
		System.out.println("xx"+myPicture.getWidth(this));
		Image offScreenImage = createImage(size().width,size().height);
		Graphics ofGraphics = offScreenImage.getGraphics();
		ofGraphics.drawImage(myPicture,0,0,this);
		new BufferedDemo(myPicture);
	}
	
	public boolean imageUpdate(Image img,int infoFlg,int x,int y,int w,int h){
		if(infoFlg == ALLBITS){
			repaint();
			return false;	
		}else 
			return true;		
	}
}

class BufferedDemo extends JFrame{
	public BufferedDemo(Image img){
		this.getContentPane().add(new PicPanel(img));
		setTitle("Ë«»º³å¼¼ÊõÑİÊ¾");
		setSize(300,300);
		setVisible(true);
	}
}

class PicPanel extends JPanel implements MouseListener,MouseMotionListener{

	int x = 0, y = 0,dx = 0,dy = 0;
	BufferedImage bimg1,bimg2;
	boolean upState = true;
	
	PicPanel(Image img){
		this.setBackground(Color.white);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		bimg1 = new BufferedImage(img.getWidth(this), img.getHeight(this), 
				BufferedImage.TYPE_INT_ARGB);
		bimg2 = new BufferedImage(img.getWidth(this), img.getHeight(this), 
				BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2D1 = bimg1.createGraphics();
		Graphics2D g2D2 = bimg2.createGraphics();
		
		g2D2.drawImage(img,0,0,this);
		g2D2.drawImage(img,0,0,this);
		g2D2.drawRect(1,1,img.getWidth(this)-3,img.getHeight(this)-3);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		if(upState)
			g2D.drawImage(bimg1, x,y, this);
		else 
			g2D.drawImage(bimg2, x,y, this);
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
		if(e.getX()>=x && e.getX()<x + bimg1.getWidth() && 
				e.getY()>= y &&e.getY()<y+bimg1.getHeight()){
			upState = false;
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			dx = e.getX() - x;
			dy = e.getY() - y;
			repaint();
		}
	}

	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		upState = true;
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(! upState){
			x = e.getX() -dx;
			y = e.getY() -dy;
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
