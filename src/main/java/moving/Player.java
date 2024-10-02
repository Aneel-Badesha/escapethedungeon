package moving;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import Main.Utility;
import panels.GamePanel;
import panels.KeyHandler;
/**
 * The Player class represents the player in the game. It is responsible for the player's position, animation, and speed.
 */
public class Player extends Moving{
    GamePanel gp;
    KeyHandler kh;
    BufferedImage[] leftFrames, rightFrames, curFrameDisplay;
    BufferedImage[] idleLeftFrames, idleRightFrames;

    final int HITBOX_OFFSET = 16;
    private int speedBuffTicks = 0, speedBuffDuration = 0;
    private boolean isSpeedBuff = false;
    final double DEFAULT_SPEED = 4;
    /**
     * Constructor for the Player class
     * @param gp gamepanel object
     * @param kh keyhandler object
     */
    public Player(GamePanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh = kh;
        init();
    }
    /**
     * Sets the player's position
     * @param tileX the x position of the tile
     * @param tileY the y position of the tile
     */
    public void setPos(int tileX, int tileY){
        x = tileX * Utility.TILE_SIZE;
        y = tileY * Utility.TILE_SIZE;
        prevX = tileX;
        prevY = tileY;
        targetX = tileX;
        targetY = tileY;
        hitBox.x = (int)(x + HITBOX_OFFSET/2);
        hitBox.y = (int)(y + HITBOX_OFFSET);
    }
    /**
     * Initializes the player's position and animation
     */
    private void init(){
        speed = DEFAULT_SPEED;

        direction = DIRECTION.RIGHT;

        loadFrames();
        curFrameDisplay = rightFrames;
        hitBox = new java.awt.Rectangle((int)(x + HITBOX_OFFSET/2), (int)(y + HITBOX_OFFSET), Utility.TILE_SIZE - HITBOX_OFFSET, Utility.TILE_SIZE - HITBOX_OFFSET);
    }
    /**
     * Loads the player's animation frames
     */
    private void loadFrames(){
        final int RUN_FRAMES = 6;
        leftFrames = Utility.getImageSet("/Player/run/Player_Left", RUN_FRAMES, Utility.TILE_SIZE);
        rightFrames = Utility.getImageSet("/Player/run/Player_Right", RUN_FRAMES, Utility.TILE_SIZE);

        final int IDLE_FRAMES = 4;
        idleLeftFrames = Utility.getImageSet("/Player/idle/left", IDLE_FRAMES, Utility.TILE_SIZE);
        idleRightFrames = Utility.getImageSet("/Player/idle/right", IDLE_FRAMES, Utility.TILE_SIZE);
    }
    /**
     * Updates the player's position and animation
     */
    public void update(){
        //targetX and targetY are prevX and prevY when the player reaches the target.
        if(targetX == prevX && targetY == prevY){
            if(kh.isUpPressed() && !gp.gridManager.isSolid(prevX, prevY - 1) && !gp.objectManager.isSolid(prevX, prevY - 1)){
                direction = DIRECTION.UP;
                targetY--;
            }else
            if(kh.isDownPressed() && !gp.gridManager.isSolid(prevX, prevY + 1) && !gp.objectManager.isSolid(prevX, prevY + 1)){
                direction = DIRECTION.DOWN;
                targetY++;
            }else
            if(kh.isLeftPressed() && !gp.gridManager.isSolid(prevX - 1, prevY) && !gp.objectManager.isSolid(prevX - 1, prevY)){
                direction = DIRECTION.LEFT;
                targetX--;
            }else
            if(kh.isRightPressed() && !gp.gridManager.isSolid(prevX + 1, prevY) && !gp.objectManager.isSolid(prevX + 1, prevY)){
                direction = DIRECTION.RIGHT;
                targetX++;
            }

            //prep animation
            if(kh.isUpPressed()){
                if(curFrameDisplay == leftFrames || curFrameDisplay == idleLeftFrames){
                    curFrameDisplay = leftFrames;
                }else{
                    curFrameDisplay = rightFrames;
                }
            }else
            if(kh.isDownPressed()){
                if(curFrameDisplay == leftFrames || curFrameDisplay == idleLeftFrames){
                    curFrameDisplay = leftFrames;
                }else{
                    curFrameDisplay = rightFrames;
                }
            }else
            if(kh.isLeftPressed()){
                curFrameDisplay = leftFrames;
            }else
            if(kh.isRightPressed()){
                curFrameDisplay = rightFrames;
            }else{
                //idle
                if(curFrameDisplay == leftFrames || curFrameDisplay == idleLeftFrames){
                    curFrameDisplay = idleLeftFrames;
                }else{
                    curFrameDisplay = idleRightFrames;
                }
            }
        }
        switch(direction){
            case UP:
                if(y-speed > targetY * Utility.TILE_SIZE){
                    y -= speed;
                }else{
                    gp.objectManager.collideWithObject(targetX, targetY);
                    y = targetY * Utility.TILE_SIZE;
                    prevY = targetY;
                }
                break;
            case DOWN:
                if(y+speed < targetY * Utility.TILE_SIZE){
                    y += speed;
                }else{
                    gp.objectManager.collideWithObject(targetX, targetY);
                    y = targetY * Utility.TILE_SIZE;
                    prevY = targetY;
                }
                break;
            case LEFT:
                if(x-speed > targetX * Utility.TILE_SIZE){
                    x -= speed;
                }else{
                    gp.objectManager.collideWithObject(targetX, targetY);
                    x = targetX * Utility.TILE_SIZE;
                    prevX = targetX;
                }
                break;
            case RIGHT:
                if(x+speed < targetX * Utility.TILE_SIZE){
                    x += speed;
                }else{
                    gp.objectManager.collideWithObject(targetX, targetY);
                    x = targetX * Utility.TILE_SIZE;
                    prevX = targetX;
                }
                break;
        }
        if(getIsSpeedBuff()){
            speedBuffTicks++;
            if(speedBuffTicks >= speedBuffDuration){
                stopSpeedBuff();
            }
        }
        hitBox.x = (int)(x + HITBOX_OFFSET/2);
        hitBox.y = (int)(y + HITBOX_OFFSET);
    }

    /**
     * Buffs the speed of the player
     * @param speedMultiplier the multiplier for the speed
     * @param frames the duration of the buff
     */
    public void buffSpeed(float speedMultiplier, int frames){
        setIsSpeedBuff(true);
        speedBuffDuration += frames;
        speed = DEFAULT_SPEED * speedMultiplier;
    }
    /**
     * Stops the speed buff
     */
    public void stopSpeedBuff(){
        setIsSpeedBuff(false);
        speedBuffTicks = 0;
        speed = DEFAULT_SPEED;
    }
    /**
     * Animates the player
     * @return the current frame of the animation
     */
    private BufferedImage animate(){
        if(frameCounter == 10){
            frameCounter = 0;
            curFrame++;
        }
        if(curFrame >= curFrameDisplay.length){
            curFrame = 0;
        }
        frameCounter++;
        return curFrameDisplay[curFrame];
        
    }
    /**
     * Draws the player
     */
    public void draw(Graphics2D g2d){
        g2d.drawImage(animate(), (int)x, (int)y, Utility.TILE_SIZE, Utility.TILE_SIZE, null);
    }
    /**
     * Returns the speed of the player
     * @return speedbuff value
     */
    public boolean getIsSpeedBuff() {
        return isSpeedBuff;
    }
    /**
     *  Sets the speed of the player
     *  @param value value to set to
     */
    public void setIsSpeedBuff(boolean value)   {
        isSpeedBuff = value;
    }

}
