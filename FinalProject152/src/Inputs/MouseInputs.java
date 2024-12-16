package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import LABANAN.GamePanel;
import entities.Player;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel GP;
    private Player p1;
    private long lastAttackTime = 0;  // Store the last time the attack was triggered
    private static final long ATTACK_COOLDOWN = 2000;  // 3 seconds cooldown in milliseconds

    public MouseInputs(GamePanel GP) {
        this.GP = GP;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
        	long currentTime = System.currentTimeMillis();
            
            // Check if enough time has passed since the last attack
            if (currentTime - lastAttackTime >= ATTACK_COOLDOWN) {
                GP.getGame().getPlayer().setAttacking(true);  // Trigger attack
                lastAttackTime = currentTime;  // Update the last attack time
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Use this method if you need to track mouse movement
    }

    // Other mouse event methods (not necessary to implement for this case)
    @Override public void mouseDragged(MouseEvent e) {}
    
    @Override public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            GP.getGame().getPlayer().setBlocking(true); 
            long currentTime = System.currentTimeMillis();
            
            if (currentTime - lastAttackTime >= ATTACK_COOLDOWN) {
                GP.getGame().getPlayer().setAttacking(true);  // Trigger attack
                lastAttackTime = currentTime;  // Update the last attack time
            }
        }
    }

    @Override public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            GP.getGame().getPlayer().setAttacking(false);  // Trigger attack on left mouse button click
        }
        
        if (e.getButton() == MouseEvent.BUTTON3) {
            GP.getGame().getPlayer().setBlocking(false); 
        }
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
