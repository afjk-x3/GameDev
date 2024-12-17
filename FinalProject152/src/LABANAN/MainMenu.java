package LABANAN;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class MainMenu extends JPanel {

    private JFrame mainFrame;
    private Image menuBG;

    public MainMenu() {
        loadMenuBackgroundImage();

        // Set up the main frame
        mainFrame = new JFrame("LABANAN");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
//        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());

        // Add the main menu (this panel) to the frame
        mainFrame.add(this);

        // Add menu components
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(180, 50, -150, 50);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Start Button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.setBackground(Color.RED);
//        startButton.setForeground(Color.PINK);
        startButton.setFocusPainted(false);   // Remove focus outline
        gbc.gridy = 1;
        add(startButton, gbc);

        // Exit Button
        JButton exitButton = new JButton("Exit Game");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridy = 2;
        add(exitButton, gbc);

        // Add Action Listeners
        startButton.addActionListener(e -> {
            mainFrame.dispose(); // Close the main menu
            new Game();          // Start the game
        });

        exitButton.addActionListener(e -> System.exit(0)); // Exit the application

        mainFrame.setVisible(true);
    }

    private void loadMenuBackgroundImage() {
        try {
            menuBG = ImageIO.read(getClass().getResourceAsStream("/menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading background image");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (menuBG != null) {
            g.drawImage(menuBG, 0, 0, getWidth(), getHeight(), null);
        } else {
            System.out.println("Background image is missing!");
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
