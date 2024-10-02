package moving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import panels.GamePanel;

/**
 * The EnemyManager class manages the enemies in the game.
 */
public class EnemyManager {
    Enemy[] enemies;
    final int MAX_ENEMIES = 10;
    GamePanel gp;

    /**
     * Constructs an EnemyManager object.
     * 
     * @param gp the GamePanel object
     */
    public EnemyManager(GamePanel gp){
        enemies = new Enemy[MAX_ENEMIES];
        this.gp = gp;
    }

    /**
     * Loads enemies from a text file.
     * 
     * @param enemyNum the number of the enemy file to load
     */
    public void loadEnemies(int enemyNum){
        if(enemyNum <= 0){
            throw new IllegalArgumentException("Number must be 1 or above: " + enemyNum);
        }
        try{
            enemies = new Enemy[MAX_ENEMIES];
            InputStream in = getClass().getResourceAsStream("/Maps/enemies" + enemyNum + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int i = 0;
            while(true){
                String line = br.readLine();
                if(line == null || i >= MAX_ENEMIES){
                    break;
                }
                String[] tokens = line.split(" ");
                switch(tokens[0]){
                    case "DOrc":
                        enemies[i++] = new DOrc(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),gp);
                        break;
                    case "KnightOrc":
                        enemies[i++] = new KnightOrc(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),gp);
                        break;
                }
            }
            br.close();
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Checks if a tile is occupied by an enemy.
     * 
     * @param tileX the x-coordinate of the tile
     * @param tileY the y-coordinate of the tile
     * @return true if the tile is occupied by an enemy, false otherwise
     */
    public boolean isOccupied(int tileX, int tileY){
        for(Enemy e : enemies){
            if(e != null){
                if(e.targetX == tileX && e.targetY == tileY){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates the enemies.
     */
    public void update(){
        for(Enemy e : enemies){
            if(e != null){
                e.update();
            }
        }
    }

    /**
     * Draws the enemies on the screen.
     * 
     * @param g the Graphics2D object
     */
    public void draw(java.awt.Graphics2D g){
        for(Enemy e : enemies){
            if(e != null){
                e.draw(g);
            }
        }
    }
}
