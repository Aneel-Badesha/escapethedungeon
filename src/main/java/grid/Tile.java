package grid;

import java.awt.image.BufferedImage;

/**
 * tile class description
 */
public class Tile {
    
    /**
     * image object
     */
    public BufferedImage image;
    /**
     * var isSolid
     */
    public boolean isSolid;
    /**
     * constructor
     * @param isSolid true or false characteristic
     */
    Tile(boolean isSolid){
        this.isSolid = isSolid;
    }
    /**
     * default constructor
     */
    Tile(){
        this.isSolid = false;
    }
}
