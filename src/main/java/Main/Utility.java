package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
/**
 * Utility class for general purpose methods
 */
public class Utility {
    /**
     * gets a number of frames from a given path and scales them to the given tile size
     * path should look like "/Objects/Key/key"
     * this function will look for key0.png, key1.png, key2.png, etc.
     * @param path the path to the image set
     * @param numFrames the number of frames in the image set
     * @param tileSize the size to scale the images to
     * @return imageSet
     */
    public static BufferedImage[] getImageSet(String path, int numFrames, int tileSize){
        BufferedImage[] imageSet = new BufferedImage[numFrames];
        try{
            for(int i = 0; i < numFrames; i++){
                imageSet[i] = preScaleImage(ImageIO.read(Utility.class.getResource(path + i + ".png")), tileSize, tileSize);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return imageSet;
    }
    /**
     * Pre-scales an image to the given width and height
     * @param image buffered image
     * @param width of image
     * @param height of image
     * @return new image
     */
    public static BufferedImage preScaleImage(BufferedImage image, int width, int height){
        BufferedImage newImage = new BufferedImage(width, height, image.getType());
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }
    /**
     * Enum for the difficulty of the game
     */
    public enum DIFFICULTY  {
        /**
         * easy
         */
        EASY, 
        /**
         * medium
         */
        MEDIUM,
        /**
         * hard
         */
        HARD
    }
    private static DIFFICULTY difficulty = DIFFICULTY.EASY;

    /**
     * Gets the speed boost for the enemies based on the difficulty
     * @return speed boost value
     */
    public static double getDifficultySpeedBoost(){
        switch(difficulty){
            case EASY:
                return 0;
            case MEDIUM:
                return 0.5;
            case HARD:
                return 0.75;
        }
        throw new RuntimeException("Invalid difficulty");
    }

    /**
     * Gets the difficulty of the game
     * @return the difficulty of the game
     */
    public static DIFFICULTY getDifficulty(){
        return difficulty;
    }
    /**
     * Sets the difficulty of the game
     * @param d the difficulty to set
     */
    public static void setDifficulty(DIFFICULTY d){
        difficulty = d;
    }

    /**
     * var for original tile size
     */
    private static final int originalTileSize = 16;
    /**
     * var for scale
     */
    private static final int scale = 2;
    /**
     * var for tile size
     */
    public static final int TILE_SIZE = originalTileSize * scale;
    /**
     * var for FPS
     */
    public static final int FPS = 60;
    /**
     * var for tile size
     */
    private final static int tileSize = Utility.TILE_SIZE;
    /**
     * var for max screen column for array
     */
    public static final int maxScreenCol = 30;
    /**
     * var for max screen row for array
     */
    public static final int maxScreenRow = 20;
    /**
     * var for screen width
     */
    final static public int screenWidth = maxScreenCol * tileSize;
    /**
     * var for screen height
     */
    final static public int screenHeight = maxScreenRow * tileSize;
    /**
     * var for number of maps
     */
    public static final int NUM_MAPS = 4;
}
