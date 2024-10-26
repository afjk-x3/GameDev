package LABANAN;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

public class GamePanel extends JPanel {
	
	private MouseInputs MI;
	private float xDelta = 64, yDelta = 62;
//	private float xDir = 0.9f, yDir = 0.9f; //movement speed
	private BufferedImage img, img2, img3;
	private BufferedImage[] idleAni, pugayAni, strikeAni;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int aniTick2, aniIndex2, aniSpeed2 = 45;
	private int aniTick3, aniIndex3, aniSpeed3 = 45;
	
	public GamePanel() {
		
		MI = new MouseInputs(this);
		
		importImg();
		loadAnimations();
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(MI);
		addMouseMotionListener(MI);
			
	}
	
	private void importImg() {		
		InputStream idle = getClass().getResourceAsStream("/IDLE_RIGHT.png");
		InputStream pugay = getClass().getResourceAsStream("/PUGAY_DEFAULT.png");
		InputStream strike = getClass().getResourceAsStream("/STRIKE.png");
		
		try {
			img = ImageIO.read(idle);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				idle.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			img2 = ImageIO.read(pugay);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pugay.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			img3 = ImageIO.read(strike);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				strike.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void loadAnimations() {
		idleAni = new BufferedImage[6];
		pugayAni = new BufferedImage[8];
		strikeAni = new BufferedImage[7];
		
		for(int i = 0; i < idleAni.length; i++) {
			idleAni[i] = img.getSubimage(i * 64, 0, 64, 50);
		} 
		
		for(int i = 0; i < pugayAni.length; i++) {
			pugayAni[i] = img2.getSubimage(i * 64, 0, 64, 64);
		}
		
		for(int i = 0; i < strikeAni.length; i++) {
			strikeAni[i] = img3.getSubimage(i * 64, 0, 64, 64);
		}
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1920,1080);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void changeXDelta(int value) {
		this.xDelta += value;
		if (xDelta < 0) {
	            xDelta = 0;
	        } else if (xDelta + 180 > getWidth()) {
	            xDelta = getWidth() - 180;
	        }
	}
	
	public void changeYDelta(int value) {
		this.yDelta += value;
		if(yDelta < 0) {
			yDelta = 0;
		} else if (yDelta + 150 > getHeight()) {
			yDelta = getHeight() - 150;
		}
	}
	
	public void updateGame() {
		updateAnimationTick();
//		setAnimation(); //??? MOVEMENT ANIMATION
//		updatePos();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateAnimationTick();
		
		g.drawImage(idleAni[aniIndex], (int) xDelta, (int) yDelta, 200, 160, null);
		
		g.drawImage(pugayAni[aniIndex2], 300, 64, 200, 200, null);
		
		g.drawImage(strikeAni[aniIndex3], 500, 64, 200, 200, null);
	    
	}

	private void updateAnimationTick() {
		
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= idleAni.length) {
				aniIndex = 0;
			}
		}
		
		aniTick2++;
		if(aniTick2 >= aniSpeed2) {
			aniTick2 = 0;
			aniIndex2++;
			if(aniIndex2 >= pugayAni.length) {
				aniIndex2 = 0;
			}
		}
		
		aniTick3++;
		if(aniTick3 >= aniSpeed3) {
			aniTick3 = 0;
			aniIndex3++;
			if(aniIndex3 >= strikeAni.length) {
				aniIndex3 = 0;
			}
		}
		
	}

	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		
	}

}
