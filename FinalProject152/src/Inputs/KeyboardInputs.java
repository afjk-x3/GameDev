package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import LABANAN.GamePanel;

public class KeyboardInputs implements KeyListener {
    private GamePanel GP;
    private long lastAttackTimePlayer2 = 0;  // Store the last time Player 2's attack was triggered
    private static final long ATTACK_COOLDOWN = 1000;  // 3 seconds cooldown in milliseconds

    public KeyboardInputs(GamePanel GP) {
        this.GP = GP;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not necessary for this case
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // PLAYER 1
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                GP.getGame().getPlayer().setLeft(true);
                GP.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_S:
                GP.getGame().getPlayer().setCrouch(true);
                break;
            case KeyEvent.VK_D:
                GP.getGame().getPlayer().setRight(true);
                GP.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_SPACE:
                GP.getGame().getPlayer().setJumped(true);
                break;
            case KeyEvent.VK_C:
                GP.getGame().getPlayer().setSungkit(true);
                break;
            case KeyEvent.VK_V:
                GP.getGame().getPlayer().setLaunch(true);
                break;
        }

        // PLAYER 2 (with cooldown for attack)
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                GP.getGame().getPlayer2().setLeft(true);
                GP.getGame().getPlayer2().setRight(false);
                break;
            case KeyEvent.VK_DOWN:
                GP.getGame().getPlayer2().setCrouch(true);
                break;
            case KeyEvent.VK_RIGHT:
                GP.getGame().getPlayer2().setRight(true);
                GP.getGame().getPlayer2().setLeft(false);
                break;
            case KeyEvent.VK_UP:
                GP.getGame().getPlayer2().setJumped(true);
                break;
            case KeyEvent.VK_J:
                long currentTime = System.currentTimeMillis();
                // Check if enough time has passed since the last attack
                if (currentTime - lastAttackTimePlayer2 >= ATTACK_COOLDOWN) {
                    GP.getGame().getPlayer2().setAttacking(true);  // Trigger attack
                    lastAttackTimePlayer2 = currentTime;  // Update the last attack time
                }
                break;
            case KeyEvent.VK_K:
                GP.getGame().getPlayer2().setSungkit(true);
                break;
            case KeyEvent.VK_L:
                GP.getGame().getPlayer2().setLaunch(true);
                break;
            case KeyEvent.VK_P:
                GP.getGame().getPlayer2().setBlocking(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // PLAYER 1
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                GP.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
                GP.getGame().getPlayer().setCrouch(false);
                break;
            case KeyEvent.VK_D:
                GP.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                GP.getGame().getPlayer().setJumped(false);
                break;
            case KeyEvent.VK_C:
                GP.getGame().getPlayer().setSungkit(false);
                break;
            case KeyEvent.VK_V:
                GP.getGame().getPlayer().setLaunch(false);
                break;
        }

        // PLAYER 2
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                GP.getGame().getPlayer2().setLeft(false);
                break;
            case KeyEvent.VK_DOWN:
                GP.getGame().getPlayer2().setCrouch(false);
                break;
            case KeyEvent.VK_RIGHT:
                GP.getGame().getPlayer2().setRight(false);
                break;
            case KeyEvent.VK_UP:
                GP.getGame().getPlayer2().setJumped(false);
                break;
            case KeyEvent.VK_J:
                GP.getGame().getPlayer2().setAttacking(false);
                break;
            case KeyEvent.VK_K:
                GP.getGame().getPlayer2().setSungkit(false);
                break;
            case KeyEvent.VK_L:
                GP.getGame().getPlayer2().setLaunch(false);
                break;
            case KeyEvent.VK_P:
                GP.getGame().getPlayer2().setBlocking(false);
                break;
        }
    }
}
