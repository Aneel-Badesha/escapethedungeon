package object;

import panels.GamePanel;

/**
 * A monster shaped trap
 * It extends the Trap class and overrides certain methods to provide specific behavior for monsters.
 */
public class Monster extends Trap {

    /**
     * Constructs a new Monster object with the specified coordinates and game panel.
     *
     * @param x  the x-coordinate of the monster
     * @param y  the y-coordinate of the monster
     * @param gp the game panel in which the monster exists
     */
    public Monster(int x, int y, GamePanel gp) {
        super(x, y, gp);
    }

    /**
     * Returns the type of the trap as a string.
     * 
     * @return the type of the trap ("monster")
     */
    @Override
    public String getType() {
        return "monster";
    }

    /**
     * Returns the number of frames for the monster animation.
     *
     * @return the number of frames for the monster animation
     */
    @Override
    public int getNumFrames() {
        return 4;
    }

    /**
     * Returns the damage inflicted by the monster.
     *
     * @return the damage inflicted by the monster
     */
    @Override
    public int getDamage() {
        return 1;
    }
}
