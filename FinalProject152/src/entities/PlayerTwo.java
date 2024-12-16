package entities;

import static utilz.Constant.PlayerConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class PlayerTwo extends Entity {
	private float gravity = 0.4f;  // Gravity force pulling the player down
	private float yVelocity = 9f;   // Vertical velocity
	private boolean isOnGround = false; // Whether the player is standing on the platform
	
	private float x, y;
	private int width, height;
	
 
	private BufferedImage[] runAniLeft;
    private BufferedImage[] idleAni, pugayAni, strikeAni, jumpAni, runAni, sungkitAni, downAni, jumpAttkAni, downBlockAni;
    
    private int aniPugayTick, aniPugayIndex, aniPugaySpeed = 45;
    private int aniSungkitTick, aniSungkitIndex, aniSungkitSpeed = 15;
    private int aniDownIndex;
    private int aniAtkTick, aniAtkIndex, aniAtkSpeed = 21;//ATTACK
    private int aniTick, aniIndex, aniSpeed = 21; //IDLE
    private int aniJumpTick, aniJumpIndex, aniJumpSpeed = 21;//JUMP
    private int aniJumpAttkTick, aniJumpAttkIndex, aniJumpAttkSpeed = 21;//JUMP
    private int aniRunTick, aniRunIndex, aniRunSpeed= 21;//MOVE
 
    private int  aniDownBlockIndex;
    private int aniRunLeftTick, aniRunLeftIndex, aniRunLeftSpeed= 21;//MOVE
    private float playerSpeed = 2.0f;

    private int playerAction = PUGAY;
    private boolean left, right, pugay;
    private boolean moving = false, attacking = false, sungkit = false;
    private boolean movingLeft = false;
	private Platform platform;
	
	private boolean downBlock = false;
	private boolean crouching = false;
	private boolean jumpAttacking = false;
	private boolean jump = false;

	  // Hitbox variables
    private int hitboxOffsetX = 30; // Adjust as needed
    private int hitboxOffsetY = 0; // Adjust as needed
    private int hitboxWidth = 60; // Adjust as needed
    private int hitboxHeight = 110; // Adjust as needed
    
  //attack hitbox
    private int ATKhitboxOffsetX = 30; // Adjust as needed
    private int ATKhitboxOffsetY = 0; // Adjust as needed
    private int ATKhitboxWidth = 60; // Adjust as needed
    private int ATKhitboxHeight = 110; // Adjust as needed
    
    //block hitbox
    private int BLKhitboxOffsetX = 30; // Adjust as needed
    private int BLKhitboxOffsetY = 0; // Adjust as needed
    private int BLKhitboxWidth = 60; // Adjust as needed
    private int BLKhitboxHeight = 110; // Adjust as needed

    public PlayerTwo(float x, float y, Platform platform) {
        super(x, y);
        this.x = x;
        this.y = y;	
        this.width = 50;
        this.height = 100;
        this.platform = platform; // Assign the platform for collision checks
        loadAnimations2();
        
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
      
    }

 public void render(Graphics g) {
    	
        // Render based on the current player action (IDLE, RUNNING, or ATTACK)
        	switch(playerAction) {

            case IDLE:
            	g.drawImage(idleAni[aniIndex], (int) x  - 40 , (int) y - 40, 150, 150, null);
            	 g.setColor(Color.RED);
                 g.drawRect((int) x + hitboxOffsetX, (int) y + hitboxOffsetY, hitboxWidth, hitboxHeight);
                break;
                
            case RUNNING:
                // Render running animation
            	g.drawImage(runAni[aniRunIndex], (int) x  - 40 , (int) y - 40 , 150 , 150, null);
                break;
                
            case RUNNINGATLEFT:
                // Render running animation
            	g.drawImage(runAniLeft[aniRunLeftIndex], (int) x  - 40  , (int) y - 40 , 150 , 150, null);
                break;
                
            case JUMP:
            	g.drawImage(jumpAni[aniJumpIndex], (int) x  - 40 , (int) y - 40, 150, 150, null);
            	break;
            	
            case LAUNCH:
                // Render attack animation (should be shown in a different layer or position if needed)
                g.drawImage(jumpAttkAni[aniJumpAttkIndex], (int) x  - 40 , (int) y - 40, 150, 150, null); // Adjust position if necessary
                break;
                
            case ATTACK:
                // Render attack animation (should be shown in a different layer or position if needed)
                g.drawImage(strikeAni[aniAtkIndex], (int) x  - 40 , (int) y - 40, 150, 150, null); // Adjust position if necessary
                g.drawRect((int) x + hitboxOffsetX, (int) y + hitboxOffsetY, hitboxWidth, hitboxHeight);
                g.drawRect((int) x + ATKhitboxOffsetX - 80, (int) y + ATKhitboxOffsetY +30 , ATKhitboxWidth + 20, ATKhitboxHeight - 100);//ATTACK HITBOX
                break;
             
            case SUNGKIT:
            	g.drawImage(sungkitAni[aniSungkitIndex], (int) x  - 40 , (int) y - 40, 150, 150, null);
            	break;
                
            case CROUCH:
            	g.drawImage(downAni[aniDownIndex], (int) x - 40 , (int) y - 40, 150, 150 , null);  // Adjust size for crouching
            	g.drawRect((int) x + hitboxOffsetX, (int) y + hitboxOffsetY + 15, hitboxWidth, hitboxHeight- 10);
            	
                break;
               
            case DOWNBLOCK:
            	g.drawImage(downBlockAni[aniDownBlockIndex], (int) x  - 40  , (int) y - 40, 150, 150 , null);  // Adjust size for crouching
                g.drawRect((int) x + hitboxOffsetX, (int) y + hitboxOffsetY, hitboxWidth, hitboxHeight);
                g.drawRect((int) x + BLKhitboxOffsetX - 50 , (int) y + BLKhitboxOffsetY + 10 , BLKhitboxWidth - 50, BLKhitboxHeight - 10 );
                break;
                
            default:
                // Render idle animation
                g.drawImage(idleAni[aniIndex], (int) x, (int) y - 40, 150, 150, null);
                
                
             // Draw the hitbox (for debugging)
                g.setColor(Color.RED);
                g.drawRect((int) x + hitboxOffsetX, (int) y + hitboxOffsetY, hitboxWidth, hitboxHeight);
                break;
        	}
        	
        	
            
        }
    private void updateAnimationTick() {
    	
    	//IDLE ANIMATION
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    	
    	aniPugayTick++;
        if (aniPugayTick >= aniPugaySpeed) {
            aniPugayTick = 0;
            aniPugayIndex++;
            if (aniPugayIndex >= GetSpriteAmount(playerAction)) {
                aniPugayIndex = 0;
                // Stop the attack animation once it completes
                if (pugay) {
                    playerAction = IDLE; // Change to idle after attack
                }
            }
        }

    	//ATTACK ANIMATION
        aniAtkTick++;
        if (aniAtkTick >= aniAtkSpeed) {
            aniAtkTick = 0;
            aniAtkIndex++;
            if (aniAtkIndex >= GetSpriteAmount(playerAction)) {
                aniAtkIndex = 0;
                // Stop the attack animation once it completes
                if (attacking) {
                    attacking = false; // Reset attacking flag after attack finishes
                }
            }
        }
        
    	//JUMP ANIMATION
        aniJumpTick++;
        if (aniJumpTick >= aniJumpSpeed) {
            aniJumpTick = 0;
            aniJumpIndex++;
            if (aniJumpIndex >= GetSpriteAmount(playerAction)) {
                aniJumpIndex = 0;
            }
        }
        
        //MOVE ANIMATION
        aniRunTick++;
        if (aniRunTick >= aniRunSpeed) {
            aniRunTick = 0;
            aniRunIndex++;
            if (aniRunIndex >= GetSpriteAmount(playerAction)) {
                aniRunIndex = 0;
                // Stop the attack animation once it completes
//                if (right && left) {
//                    right = false;
//                    left = false;
//                }
            }
        }
        
        aniRunLeftTick++;
        if (aniRunLeftTick >= aniRunLeftSpeed) {
            aniRunLeftTick = 0;
            aniRunLeftIndex++;
            if (aniRunLeftIndex >= GetSpriteAmount(playerAction)) {
                aniRunLeftIndex = 0;
                // Stop the attack animation once it completes
//                if (left && right) {
//                    left = false;
//                    right = false;
//                }
            }
        }
        
        aniSungkitTick++;
        if (aniSungkitTick >= aniSungkitSpeed) {
            aniSungkitTick = 0;
            aniSungkitIndex++;
            if (aniSungkitIndex >= GetSpriteAmount(playerAction)) {
                aniSungkitIndex = 0;
                // Stop the attack animation once it completes
                if (sungkit) {
                    sungkit = false; // Reset attacking flag after attack finishes
                }
            }
        }
        
        aniJumpAttkTick++;
        if (aniJumpAttkTick >= aniJumpAttkSpeed) {
            aniJumpAttkTick = 0;
            aniJumpAttkIndex++;
            if (aniJumpAttkIndex >= GetSpriteAmount(playerAction)) {
                aniJumpAttkIndex = 0;
                // Stop the attack animation once it completes
                if (jumpAttacking) {
                    jumpAttacking = false; // Reset attacking flag after attack finishes
                }
            }
        }
        
    }


    private void updatePos() {
    	moving = false;

    	if (left && !right) {
    	    x -= playerSpeed;
    	    moving = true;
    	    movingLeft = true; // Indicate moving left
    	} else if (right && !left) {
    	    x += playerSpeed;
    	    moving = true;
    	    movingLeft = false; // Indicate moving right
    	}

        
        // Apply gravity if not on the ground
        if (!isOnGround) {
            yVelocity += gravity;  // Increase velocity due to gravity
        }

        float nextX = x;
        float nextY = y + yVelocity; // Add vertical velocity to position

        if (nextX + 64 > platform.getLeftX() && nextX < platform.getLeftX() + platform.getLeftWidth()) {
            if (nextY + 64 > platform.getLeftY() && y + 64 <= platform.getLeftY()) {
                // Player is falling onto the platform
                nextY = platform.getLeftY() - 58; // Position player on top of the platform
                yVelocity = 0;                // Stop vertical velocity
                isOnGround = true;            // Mark player as on the ground
            }
        }         
        
     // Check collision with the center rectangle
        else if (nextX + 64 > platform.getX() && nextX < platform.getX() + platform.getWidth()) {
            if (nextY + 64 > platform.getY() && y + 64 <= platform.getY()) {
                // Player is falling onto the platform
                nextY = platform.getY() - 64; // Position player on top of the platform
                yVelocity = 0;                // Stop vertical velocity
                isOnGround = true;            // Mark player as on the ground
            }
        }
        
        else if (nextX + 64 > platform.getRightX() && nextX < platform.getRightX() + platform.getRightWidth()) {
            if (nextY + 64 > platform.getRightY() && y + 64 <= platform.getRightY()) {
                // Player is falling onto the platform
                nextY = platform.getRightY() - 57; // Position player on top of the platform
                yVelocity = 0;                // Stop vertical velocity
                isOnGround = true;            // Mark player as on the ground
            }
        }
        else {
            isOnGround = false; // Player is not on any platform
        }

        // Update position
        x = nextX;
        y = nextY;
    }


    private void setAnimation() {
        if (attacking) {
            playerAction = ATTACK; // Attack animation
        } else if (crouching) {
            playerAction = CROUCH;
        } else if (sungkit) {
            playerAction = SUNGKIT;
        } else if (moving) {
            if (movingLeft) {
                playerAction = RUNNINGATLEFT; // Running left animation
            } else {
                playerAction = RUNNING; // Running right animation
            }
        } else if (jump) {
            playerAction = JUMP;
        } else if (pugay) {
            playerAction = PUGAY;
        } else if (downBlock) {
            playerAction = DOWNBLOCK;
        } else if (jumpAttacking) {
            playerAction = LAUNCH;
        } else {
            playerAction = IDLE; // Default idle
        }
    }

    private void loadAnimations2() {
    	
    	InputStream idle = getClass().getResourceAsStream("/Sprite2_Idle(LEFT).png");
        InputStream pugay = getClass().getResourceAsStream("/Sprite_Pugay(RIGHT).png");
        InputStream strike = getClass().getResourceAsStream("/Sprite2_StrikeOnly(LEFT)).png");
        InputStream run = getClass().getResourceAsStream("/Sprite2_Running(RIGHT).png");
        InputStream runLeft = getClass().getResourceAsStream("/Sprite2_Running(LEFT).png");
        InputStream crouch = getClass().getResourceAsStream("/Sprite2_CrouchAtk(BLUE-LEFT).png");
        InputStream sungkit = getClass().getResourceAsStream("/Sprite2_CrouchAtK(LEFT).png");
        InputStream up = getClass().getResourceAsStream("/Sprite2_Jump(LEFT).png");
        InputStream launch = getClass().getResourceAsStream("/Sprite2_JumpAtk(LEFT).png");
        InputStream downBlock = getClass().getResourceAsStream("/Sprite2_CrouchBlock(LEFT).png");

        try {
            BufferedImage img = ImageIO.read(idle);
            idleAni = new BufferedImage[6]; // Ensure the correct number of frames here
            for (int i = 0; i < idleAni.length; i++) {
                idleAni[i] = img.getSubimage(i * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                idle.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
        	BufferedImage img2 = ImageIO.read(pugay);
            pugayAni = new BufferedImage[7]; // Ensure the correct number of frames here
            for (int j = 0; j < pugayAni.length; j++) {
                pugayAni[j] = img2.getSubimage(j * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pugay.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
        	BufferedImage img3 = ImageIO.read(strike);
            strikeAni = new BufferedImage[5]; // Ensure the correct number of frames here
            for (int z = 0; z < strikeAni.length; z++) {
                strikeAni[z] = img3.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                strike.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
        	BufferedImage runImg = ImageIO.read(run);
            runAni = new BufferedImage[12]; // Ensure the correct number of frames here
            for (int z = 0; z < runAni.length; z++) {
                runAni[z] = runImg.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                run.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
        	BufferedImage runLeftImg = ImageIO.read(runLeft);
            runAniLeft = new BufferedImage[12]; // Ensure the correct number of frames here
            for (int z = 0; z < runAniLeft.length; z++) {
                runAniLeft[z] = runLeftImg.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                runLeft.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
        	BufferedImage jumpImg = ImageIO.read(up);
            jumpAni = new BufferedImage[6]; // Ensure the correct number of frames here
            for (int z = 0; z < jumpAni.length; z++) {
                jumpAni[z] = jumpImg.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                up.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
        	BufferedImage launchImg = ImageIO.read(launch);
            jumpAttkAni = new BufferedImage[6]; // Ensure the correct number of frames here
            for (int z = 0; z < jumpAttkAni.length; z++) {
                jumpAttkAni[z] = launchImg.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                launch.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
        	BufferedImage sungkitImg = ImageIO.read(sungkit);
            sungkitAni = new BufferedImage[5]; // Ensure the correct number of frames here
            for (int z = 0; z < sungkitAni.length; z++) {
                sungkitAni[z] = sungkitImg.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sungkit.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
        	BufferedImage downImg = ImageIO.read(crouch);
            downAni = new BufferedImage[1]; // Ensure the correct number of frames here
            for (int z = 0; z < downAni.length; z++) {
                downAni[z] = downImg.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                crouch.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
        	BufferedImage downBlockImg = ImageIO.read(downBlock);
            downBlockAni = new BufferedImage[1]; // Ensure the correct number of frames here
            for (int z = 0; z < downAni.length; z++) {
                downBlockAni[z] = downBlockImg.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                downBlock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	
    }
    

    public void resetDirBooleans() {
        left = false;
        right = false;
    }
    
    public void setJumped(boolean jump) {
    	this.jump = jump;
        if (jump && isOnGround) {
            yVelocity = -15;  // Apply an upward velocity to simulate jump
            isOnGround = false;
        }
    }

    
    public void setSungkit(boolean sungkit) {
    	if(sungkit) {
    		this.sungkit = true;
    		playerAction = SUNGKIT;
//    		aniIndex = 0;
    	}
    }

    
    public boolean isAttacking() {
        return attacking;
    }
    
    public void setAttacking(boolean attacking) {
    	
        if (attacking) {
        	this.attacking = true;
            playerAction = ATTACK; // Switch to attack animation immediately
//            aniIndex = 0; // Reset animation frame for attack
        }
    }
    
    public void setLaunch(boolean jumpAttk) {
    	
        if (jumpAttk) {
        	this.jumpAttacking = true;
            playerAction = LAUNCH; // Switch to attack animation immediately
//            aniIndex = 0; // Reset animation frame for attack
        }
    }
    
    public boolean isCrouching() {
        return crouching;
    }
    public void setCrouch(boolean crouch) {
    	this.crouching = crouch;
    }
    
    public boolean isBlocking() {
        return downBlock;
    }
    public void setBlocking(boolean blocking) {
    	this.downBlock = blocking;
    }

    public boolean isJump() {
    	return jump;
    }
    public void setJump(boolean jump) {
    	this.jump = jump;
    }

    public boolean isRight() {
        return right;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    
    public boolean isLeft() {
        return left;
    }
    public void setLeft(boolean left) {
    	this.left = left;
    }

    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    
}
