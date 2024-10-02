package moving;

import panels.GamePanel;

/**
 * class for the DOrc extends enemy
 */
public class DOrc extends Enemy{
    final int NUM_FRAMES = 6;


    /**
     * Represents a DOrc enemy in the game.
     * DOrc extends the Enemy class and inherits its properties and methods.
     * @param tileX xposition
     * @param tileY yposition
     * @param gp gamepanel object
     */
    public DOrc(int tileX, int tileY, GamePanel gp){
        super(tileX, tileY, gp);
        speed = 1;
    }

    /**
     * Returns the name of the enemy.
     *
     * @return the name of the DOrc enemy
     */
    @Override
    public String getEnemyName() {
        return "DOrc";
    }

    /**
     * Returns the speed of the enemy.
     * The speed is calculated by adding the speed of the DOrc to the speed of the superclass.
     *
     * @return the speed of the enemy
     */
    @Override
    public double getSpeed() {
        return (speed + super.getSpeed());
    }
}
