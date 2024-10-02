package moving;

import panels.GamePanel;

/**
 * The KnightOrc class represents a type of enemy called KnightOrc in the game.
 * It extends the Enemy class and overrides some of its methods.
 */
public class KnightOrc extends Enemy {
    final int NUM_FRAMES = 6;

    /**
     * Constructs a new KnightOrc object with the specified tile position and GamePanel.
     *
     * @param tileX The x-coordinate of the tile position.
     * @param tileY The y-coordinate of the tile position.
     * @param gp The GamePanel object.
     */
    public KnightOrc(int tileX, int tileY, GamePanel gp) {
        super(tileX, tileY, gp);
        speed = 2;
    }

    /**
     * Returns the name of the enemy.
     *
     * @return The name of the enemy.
     */
    @Override
    public String getEnemyName() {
        return "KnightOrc";
    }

    /**
     * Returns the speed of the enemy.
     * The speed is calculated by adding the speed of the KnightOrc to the speed of the superclass.
     *
     * @return The speed of the enemy.
     */
    @Override
    public double getSpeed() {
        return (speed + super.getSpeed());
    }
}
