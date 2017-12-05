package zealjiang.swing;

import java.applet.Applet;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Example6_10 extends Applet implements KeyListener{

	int count = 0;
	Button button = new Button();
	TextArea textArea = new TextArea(5,20);
	
	public void init(){
		button.addKeyListener(this);
		add(button);
		add(textArea);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int t = e.getKeyCode();
		if(t >= KeyEvent.VK_A && t<= KeyEvent.VK_Z){
			textArea.append((char)t+" ");
			count++;
			if(count%10==0)
				textArea.append("\n");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
