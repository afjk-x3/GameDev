package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import LABANAN.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel GP;

    public MouseInputs(GamePanel GP) {
        this.GP = GP;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            GP.getGame().getPlayer().setAttacking(true);  // Trigger attack on left mouse button click
        }
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Use this method if you need to track mouse movement
    }

    // Other mouse event methods (not necessary to implement for this case)
    @Override public void mouseDragged(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON3) {
        	GP.getGame().getPlayer().setBlocking(true); 
        }
    }
    @Override public void mouseReleased(MouseEvent e) {
    	if (e.getButton() == MouseEvent.BUTTON1) {
            GP.getGame().getPlayer().setAttacking(false);  // Trigger attack on left mouse button click
        }
    	
    	if(e.getButton() == MouseEvent.BUTTON3) {
        	GP.getGame().getPlayer().setBlocking(false); 
        }
    }
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
