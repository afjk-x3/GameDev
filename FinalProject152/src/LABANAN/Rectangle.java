package LABANAN;

import java.awt.*;

public class Rectangle {
     int x, y, width, height;
    private int dx, dy;
    private int collisionCount = 0;
    private Color color;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dx = 2 ;
        this.dy = 2;
    }

    public void move(int panelWidth, int panelHeight) {
        x += dx;
        y += dy;

        if (x < 0 || x + width > panelWidth) {
            dx = -dx;
        }
        if (y < 0 || y + height > panelHeight) {
            dy = -dy;
        }
    }

    public boolean collidesWith(Rectangle other) {
        Rectangle bounds1 = this.getBounds();
        Rectangle bounds2 = other.getBounds();
        
        return bounds1.intersects(bounds2);
    }

    public void incrementCollisionCount() {
        collisionCount++;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(collisionCount), x + width / 2 - 5, y + height / 2 + 5);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getDx() { return dx; }
    public int getDy() { return dy; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setVelocity(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public boolean intersects(Rectangle r) {
        return (this.x < r.x + r.width &&
                this.x + this.width > r.x &&
                this.y < r.y + r.height &&
                this.y + this.height > r.y);
    }
    
    

}
