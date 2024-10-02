package object;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import panels.GamePanel;

/**
 * The ObjectManager class manages the objects and bonuses in the game.
 */
public class ObjectManager {
    private Object[] objects;
    private Bonus[] bonuses;
    GamePanel gp;
    final int MAX_BONUSES = 5;
    final int MAX_OBJECTS = 30;
    private int numKeys;
    private Door mainDoor;
    private int numObjects = 0;
    private int numBonuses = 0;

    /**
     * Constructs an ObjectManager object.
     * @param gp the GamePanel object
     */
    public ObjectManager(GamePanel gp){
        this.gp = gp;
    }

    /**
     * Loads the objects and bonuses from a file.
     * @param objNum the object number
     */
    public void loadObjects(int objNum){
        if(objNum <= 0){
            throw new IllegalArgumentException("Number must be 1 or above: " + objNum);
        }
        try{
            setMainDoor(null);
            numKeys = 0;

            //reset objects
            objects = new Object[MAX_OBJECTS];
            bonuses = new Bonus[MAX_BONUSES];
            numObjects = 0;
            numBonuses = 0;
            InputStream in = getClass().getResourceAsStream("/Maps/obj" + objNum + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            while(true){
                String line = br.readLine();
                if(line == null || numObjects >= MAX_OBJECTS){
                    break;
                }
                String[] tokens = line.split(" ");

                switch(tokens[0]){
                    case "key":
                        numKeys++;
                        numObjects++;
                        objects[numObjects] = new Key(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), gp);
                        break;
                    case "door":
                        numObjects++;
                        objects[numObjects] = new Door(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), gp);
                        setMainDoor((Door)objects[numObjects]);
                        break;
                    case "spikes":
                        numObjects++;
                        objects[numObjects] = new Spikes(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), gp);
                        break;
                    case "monster":
                        numObjects++;
                        objects[numObjects] = new Monster(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), gp);
                        break;
                    case "speed":
                        numObjects++;
                        numBonuses++;
                        objects[numObjects] = new Speed(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), gp);
                        bonuses[numBonuses] = (Speed)objects[numObjects];
                        break;
                    default:
                        break;
                } 
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets the main door object.
     * @return the main door object
     */
    public Door getMainDoor()   {
        return mainDoor;
    }
    
    /**
     * getter for numKeys
     * @return numKeys
     */
    public int getNumKeys() {
        return numKeys;
    }

    /**
     * setter for numKeys
     * @param numKeys value to set to
     */
    public void setNumKeys(int numKeys) {
        this.numKeys = numKeys;
    }

    /**
     * getter for numObjects
     * @return numObjects
     */
    public int getNumObjects() {
        return numObjects;
    }

    /**
     * getter for numBonuses
     * @return numBonuses
     */
    public int getNumBonuses() {
        return numBonuses;
    }

    /**
     * Sets the main door object.
     * @param value the main door object
     */
    public void setMainDoor(Door value) {
        mainDoor = value;
    }

    /**
     * Checks if the specified tile is solid.
     * @param tileX the x-coordinate of the tile
     * @param tileY the y-coordinate of the tile
     * @return true if the tile is solid, false otherwise
     */
    public boolean isSolid(int tileX, int tileY){
        for(int i = 0; i < objects.length; i++){
            if(objects[i] != null){
                if(objects[i].tileX == tileX && objects[i].tileY == tileY){
                    return objects[i].isSolid();
                }
            }
        }
        return false;
    }

    /**
     * Updates the bonuses in the game.
     */
    public void update(){
        //update bonuses
        for(int i = 0; i < bonuses.length; i++){
            if(bonuses[i] != null){
                bonuses[i].update();
                if(bonuses[i].isExpired()){
                    numBonuses--;
                    bonuses[i] = null;
                }
            }
        }
    }

    /**
     * Handles collision with an object at the specified tile.
     * @param tileX the x-coordinate of the tile
     * @param tileY the y-coordinate of the tile
     */
    public void collideWithObject(int tileX, int tileY){
        //currently a linear search, could be optimized via hashmap and pairs
        for(int i = 0; i < objects.length; i++){
            if(objects[i] != null){
                if(objects[i].tileX == tileX && objects[i].tileY == tileY){
                    objects[i].collision();
                    if(objects[i].deleteOnCollision()){
                        numObjects--;
                        objects[i] = null;
                    }
                }
            }
        }
    }

    /**
     * Draws the objects on the screen.
     * @param g2d the Graphics2D object
     */
    public void draw(Graphics2D g2d){
        for(int i = 0; i < objects.length; i++){
            if(objects[i] != null)
                objects[i].draw(g2d);
        }
    }
}
