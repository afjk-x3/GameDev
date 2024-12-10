package entities;

import java.awt.Color;
import java.awt.Graphics;

public class Platform {
    private int x, y, width, height;
    private int leftX, leftY, leftWidth, leftHeight;
    private int rightX, rightY, rightWidth, rightHeight;

    public Platform(int x, int y, int width, int height, int leftX, int leftY, int leftWidth, int leftHeight/*, int rightX, int rightY, int rightWidth, int rightHeight*/) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height ;
        this.leftX = leftX;
        this.leftY = leftY;
        this.leftWidth = leftWidth;
        this.leftHeight = leftHeight;
        this.rightX = rightX;
        this.rightY = rightY;
//        this.rightWidth = rightWidth;
//        this.rightHeight = rightHeight;
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y + 43, width, height);
        g.setColor(Color.BLUE);
        g.fillRect(leftX, leftY + 50, leftWidth, leftHeight);
    }
    
    
    // Getters for collision detection
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width ;
    }

    public int getHeight() {
        return height ;
    }
    
    public int getLeftX() {
        return leftX;
    }

    public int getLeftY() {
        return leftY;
    }

    public int getLeftWidth() {
        return leftWidth ;
    }

    public int getLeftHeight() {
        return leftHeight ;
    }
}