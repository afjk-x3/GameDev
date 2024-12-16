package LABANAN;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class RectangleClass extends JPanel implements ActionListener, MouseListener, KeyListener {
    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    private Timer timer = new Timer(10, this);
    private static final int RECTANGLE_WIDTH = 60;
    private static final int RECTANGLE_HEIGHT = 40;

    public RectangleClass() {
        this.addMouseListener(this);
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Rectangle rect : rectangles) {
            rect.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle r1 = rectangles.get(i);
            r1.move(getWidth(), getHeight());
            for (int j = i + 1; j < rectangles.size(); j++) {
                Rectangle r2 = rectangles.get(j);
                if (r1.collidesWith(r2)) {
                    r1.incrementCollisionCount();
                    r2.incrementCollisionCount();
                    resolveCollision(r1, r2);
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            rectangles.add(new Rectangle(e.getX(), e.getY(), RECTANGLE_WIDTH, RECTANGLE_HEIGHT));
        }
    }

    private void resolveCollision(Rectangle r1, Rectangle r2) {
        // Collision resolution logic for rectangles (axis-aligned bounding box)
        Rectangle bounds1 = r1.getBounds();
        Rectangle bounds2 = r2.getBounds();

        if (bounds1.intersects(bounds2)) {
            int overlapX = Math.min(bounds1.x + bounds1.width, bounds2.x + bounds2.width) - Math.max(bounds1.x, bounds2.x);
            int overlapY = Math.min(bounds1.y + bounds1.height, bounds2.y + bounds2.height) - Math.max(bounds1.y, bounds2.y);

            // Adjust positions based on the overlap
            if (overlapX > overlapY) {
                if (bounds1.y < bounds2.y) {
                    r1.setY(r1.getY() - overlapY);
                    r2.setY(r2.getY() + overlapY);
                } else {
                    r1.setY(r1.getY() + overlapY);
                    r2.setY(r2.getY() - overlapY);
                }
            } else {
                if (bounds1.x < bounds2.x) {
                    r1.setX(r1.getX() - overlapX);
                    r2.setX(r2.getX() + overlapX);
                } else {
                    r1.setX(r1.getX() + overlapX);
                    r2.setX(r2.getX() - overlapX);
                }
            }
        }
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("bukel bukel");
        RectangleClass panel = new RectangleClass();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
}
