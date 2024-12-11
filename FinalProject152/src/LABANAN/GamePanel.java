package LABANAN;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

public class GamePanel extends JPanel {

    private MouseInputs MI;
    private Game game;
    private Image bg; // Declare the image variable for the background
    private Image mainPlatform;

    public GamePanel(Game game) {
        MI = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        loadBackgroundImage(); // Load the image here
        loadPlatformImage();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(MI);
        addMouseMotionListener(MI);

        // Enable focus for keyboard inputs
        setFocusable(true);
        requestFocusInWindow();
    }

    // Method to load the background image
    private void loadBackgroundImage() {
        try {
            bg = ImageIO.read(getClass().getResourceAsStream("/BACKGROUND.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading background image");
        }
    }
    
    private void loadPlatformImage() {
    	try {
    		mainPlatform = ImageIO.read(getClass().getResourceAsStream("/PLATFORM_ORIGINAL.png"));
    	} catch (IOException e) {
    		e.printStackTrace();
    		System.out.println("Error loading background image");
    	}
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1920, 1080);
        setPreferredSize(size);
    }

    public void updateGame() {
        // Logic for updating game state
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        if (bg != null) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
            g.drawImage(mainPlatform, 524, 345, 840, 500, null);
        }

        // Render the game elements
        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
