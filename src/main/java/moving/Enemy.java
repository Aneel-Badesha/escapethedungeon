package moving;

import java.awt.image.BufferedImage;

import Main.Utility;
import panels.GamePanel;

/**
 * The abstract class representing an enemy in the game.
 * It extends the Moving class and provides common functionality for all enemies.
 */
public abstract class Enemy extends Moving{
    GamePanel gp;
    private BufferedImage[] leftFrames, rightFrames, curFrameDisplay;
    final int HITBOX_OFFSET = 16;
    final int NUM_FRAMES = 6;
    /**
     * Represents an enemy in the game.
     */
    Enemy(int tileX, int tileY, GamePanel gp){
        this.gp = gp;
        this.x = tileX * Utility.TILE_SIZE;
        this.y = tileY * Utility.TILE_SIZE;
        this.prevX = tileX;
        this.prevY = tileY;
        this.targetX = tileX;
        this.targetY = tileY;
        loadFrames();
        hitBox = new java.awt.Rectangle((int)(x + HITBOX_OFFSET/2), (int)(y + HITBOX_OFFSET), Utility.TILE_SIZE - HITBOX_OFFSET, Utility.TILE_SIZE - HITBOX_OFFSET);
        direction = DIRECTION.RIGHT;
    }

    /**
     * @return enemy name
     */
    abstract public String getEnemyName();
    /**
     * Returns the speed of the enemy.
     * The speed is determined by the difficulty level of the game.
     *
     * @return the speed of the enemy
     */
    public double getSpeed(){
        return Utility.getDifficultySpeedBoost();
    };
    /**
     * Loads the frames for the enemy's animation.
     */
    private void loadFrames(){
        leftFrames = Utility.getImageSet("/Enemies/" + getEnemyName() + "/left", NUM_FRAMES, Utility.TILE_SIZE);
        rightFrames = Utility.getImageSet("/Enemies/" + getEnemyName() + "/right", NUM_FRAMES, Utility.TILE_SIZE);
        curFrameDisplay = rightFrames;
    }
    /**
     * Checks if the enemy can move to the specified tile position.
     *
     * @param tileX the x-coordinate of the tile position
     * @param tileY the y-coordinate of the tile position
     * @return true if the enemy can move to the specified tile position, false otherwise
     */
    private boolean validMove(int tileX, int tileY){
        return !gp.gridManager.isSolid(tileX, tileY) && !gp.objectManager.isSolid(tileX, tileY) && !gp.enemyManager.isOccupied(tileX, tileY);
    }
    /**
     * Updates the enemy's position and animation.
     */
    public void update(){
        if(targetX == prevX && targetY == prevY){
            //move sideways first if x distance is greater than y distance, otherwise move up or down first
            if(Math.abs(targetX - gp.player.targetX) > Math.abs(targetY - gp.player.targetY)){
                if(targetX > gp.player.targetX && validMove(targetX-1, targetY)){
                    direction = DIRECTION.LEFT;
                    targetX--;
                }else
                if(targetX < gp.player.targetX && validMove(targetX+1, targetY)){
                    direction = DIRECTION.RIGHT;
                    targetX++;
                }else 
                if(targetY > gp.player.targetY && validMove(targetX, targetY-1)){
                    direction = DIRECTION.UP;
                    targetY--;
                }else
                if(targetY < gp.player.targetY && validMove(targetX, targetY+1)){
                    direction = DIRECTION.DOWN;
                    targetY++;
                }
            }else{
                if(targetY > gp.player.targetY && validMove(targetX, targetY-1)){
                    direction = DIRECTION.UP;
                    targetY--;
                }else
                if(targetY < gp.player.targetY && validMove(targetX, targetY+1)){
                    direction = DIRECTION.DOWN;
                    targetY++;
                }else 
                if(targetX > gp.player.targetX && validMove(targetX-1, targetY)){
                    direction = DIRECTION.LEFT;
                    targetX--;
                }else
                if(targetX < gp.player.targetX && validMove(targetX+1, targetY)){
                    direction = DIRECTION.RIGHT;
                    targetX++;
                }
            }
            curFrameDisplay = targetX > prevX ? rightFrames : leftFrames;
        }
        switch(direction){
            case UP:
                if(y-getSpeed() > targetY * Utility.TILE_SIZE){
                    y -= getSpeed();
                }else{
                    y = targetY * Utility.TILE_SIZE;
                    prevY = targetY;
                }
                break;
            case DOWN:
                if(y+getSpeed() < targetY * Utility.TILE_SIZE){
                    y += getSpeed();
                }else{
                    y = targetY * Utility.TILE_SIZE;
                    prevY = targetY;
                }
                break;
            case LEFT:
                if(x-getSpeed() > targetX * Utility.TILE_SIZE){
                    x -= getSpeed();
                }else{
                    x = targetX * Utility.TILE_SIZE;
                    prevX = targetX;
                }
                break;
            case RIGHT:
                if(x+getSpeed() < targetX * Utility.TILE_SIZE){
                    x += getSpeed();
                }else{
                    x = targetX * Utility.TILE_SIZE;
                    prevX = targetX;
                }
                break;
        }
        hitBox.x = (int)(x + HITBOX_OFFSET/2);
        hitBox.y = (int)(y + HITBOX_OFFSET);
        if(hitBox.intersects(gp.player.getHitBox())){
            gp.gameOver();
        }
    }

    /**
     * Animates the enemy.
     *
     * @return the current frame of the enemy's animation
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
     * Draws the enemy on the graphics context.
     *
     * @param g2d The Graphics2D object to draw on.
     */
    public void draw(java.awt.Graphics2D g2d){
        g2d.drawImage(animate(), (int)x, (int)y, null);
    }
}
