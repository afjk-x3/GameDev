package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import LABANAN.GamePanel;

public class KeyboardInputs implements KeyListener{
	private GamePanel GP;
	public KeyboardInputs(GamePanel GP) {
		this.GP = GP;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			GP.changeYDelta (-60);
			break;
		case KeyEvent.VK_A:
			GP.changeXDelta (-80);
			break;
		case KeyEvent.VK_S:
			GP.changeYDelta (50);
			break;
		case KeyEvent.VK_D:
			GP.changeXDelta (80);
			break;
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
