package object;

import panels.GamePanel;

/**
 * The Spikes class represents a type of trap called "Spikes" in the game.
 * It extends the Trap class and inherits its properties and methods.
 */
public class Spikes extends Trap {

    /**
     * Constructs a new Spikes object with the specified coordinates and game panel.
     *
     * @param x  the x-coordinate of the Spikes object
     * @param y  the y-coordinate of the Spikes object
     * @param gp the GamePanel object associated with the Spikes object
     */
    public Spikes(int x, int y, GamePanel gp) {
        super(x, y, gp);
    }

    /**
     * Returns the type of the trap as a string.
     *
     * @return the type of the trap ("Spikes")
     */
    @Override
    public String getType() {
        return "Spikes";
    }

    /**
     * Returns the number of frames for the trap animation.
     *
     * @return the number of frames for the trap animation (5)
     */
    @Override
    public int getNumFrames() {
        return 5;
    }

    /**
     * Returns the damage caused by the trap.
     *
     * @return the damage caused by the trap (1)
     */
    @Override
    public int getDamage() {
        return 1;
    }
}
