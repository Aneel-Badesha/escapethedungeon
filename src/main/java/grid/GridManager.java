package grid;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;

import Main.Utility;
import panels.GamePanel;


/**
 * The GridManager class manages the grid system for the game.
 * It handles loading tiles, loading maps, and drawing the grid.
 */
public class GridManager {
    GamePanel gp;
    Tile[] tiles;
    int map[][];
    int NUM_TILE_TEXTURES = 36;

    /**
     * Constructor for the GridManager class.
     * @param gp the GamePanel object
     */
    public GridManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[NUM_TILE_TEXTURES];

        map = new int[Utility.maxScreenRow][Utility.maxScreenCol];
        loadTiles();
    }

    /**
     * Loads the tiles for the game.
     */
    public void loadTiles(){
        int i = 0;

        //core first
        tiles[i++] = loadTile("brick1", true); // 0
        tiles[i++] = loadTile("floor1", false); // 1
        tiles[i++] = loadTile("water1", true); // 2
        tiles[i++] = loadTile("lava1", true); // 3 etc...

        tiles[i++] = loadTile("brick2", true);
        tiles[i++] = loadTile("brick3", true); // 5

        tiles[i++] = loadTile("floor2", false);
        tiles[i++] = loadTile("floor3", false);

        tiles[i++] = loadTile("lava2", true);
        tiles[i++] = loadTile("lava3", true);

        tiles[i++] = loadTile("water2", true); // 10
        tiles[i++] = loadTile("water3", true);

        tiles[i++] = loadTile("brick_blood1", true);
        tiles[i++] = loadTile("brick_blood2", true);
        tiles[i++] = loadTile("brick_blood3", true);

        tiles[i++] = loadTile("brick_vines1", true); // 15
        tiles[i++] = loadTile("brick_vines2", true);
        tiles[i++] = loadTile("brick_vines3", true);

        tiles[i++] = loadTile("gravel_grass1", false);
        tiles[i++] = loadTile("gravel_grass2", false);
        tiles[i++] = loadTile("gravel_grass3", false); // 20

        tiles[i++] = loadTile("gravel_lava1", false);
        tiles[i++] = loadTile("gravel_lava2", false);
        tiles[i++] = loadTile("gravel_lava3", false);

        tiles[i++] = loadTile("gravel_more_lava1", false);
        tiles[i++] = loadTile("gravel_more_lava2", false); // 25
        tiles[i++] = loadTile("gravel_more_lava3", false);

        tiles[i++] = loadTile("gravel1", false);
        tiles[i++] = loadTile("gravel2", false);
        tiles[i++] = loadTile("gravel3", false);

        tiles[i++] = loadTile("lava_green1", true); // 30
        tiles[i++] = loadTile("lava_green2", true);
        tiles[i++] = loadTile("lava_green3", true);

        tiles[i++] = loadTile("lava_red1", true);
        tiles[i++] = loadTile("lava_red2", true);
        tiles[i++] = loadTile("lava_red3", true); // 35
    }

    /**
     * Loads a tile for the game.
     * @param name the name of the tile in the resources/Tiles directory
     * @param isSolid true if player cannot walk on the tile, false otherwise
     * @return the Tile object
     */
    public Tile loadTile(String name, boolean isSolid){
        Tile tile = new Tile(isSolid);
        try{
            tile.image = Utility.preScaleImage(ImageIO.read(getClass().getResource("/Tiles/" + name + ".png")), Utility.TILE_SIZE, Utility.TILE_SIZE);
        }catch(IOException e){
            e.printStackTrace();
        }
        return tile;
    }

    /**
     * Loads a map for the game.
     * @param mapNum the number of the map to load
     */
    public void loadMap(int mapNum){
        if(mapNum <= 0){
            throw new IllegalArgumentException("Number must be 1 or above: " + mapNum);
        }
        try{
            InputStream in = getClass().getResourceAsStream("/Maps/map" + mapNum + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            //get player position:
            String line = br.readLine();
            if(line == null){
                throw new IOException("No player position found in map" + mapNum + ".txt");
            }
            String[] playerPos = line.split(" ");
            if(playerPos[0].equals("playerstart") == false){
                throw new IOException("No player position found in map" + mapNum + ".txt");
            }
            gp.player.setPos(Integer.parseInt(playerPos[1]), Integer.parseInt(playerPos[2]));
            for(int i = 0; i < Utility.maxScreenRow; i++){
                line = br.readLine();
                String[] tokens = null;
                if(line != null){
                    tokens = line.split(" ");
                }
                for(int j = 0; j < Utility.maxScreenCol; j++){
                    if(tokens == null || j >= tokens.length){
                        map[i][j] = 0;//default tile
                    }else{
                        map[i][j] = Integer.parseInt(tokens[j]);
                    }
                }
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Draws the grid on the graphics context.
     * @param g2d The Graphics2D object to draw on.
     */
    public void draw(Graphics2D g2d){
        for(int i = 0; i < Utility.maxScreenRow; i++){
            for(int j = 0; j < Utility.maxScreenCol; j++){
                g2d.drawImage(tiles[map[i][j]].image, j * Utility.TILE_SIZE, i * Utility.TILE_SIZE, null);
            }
        }
    }

    /**
     * checks if the tile at the given position is solid.
     * @param x the x position of the tile
     * @param y the y position of the tile
     * @return boolean true if the tile is solid, false otherwise
     */
    public boolean isSolid(int x, int y) {

        return y < 0 || x < 0 || y >= Utility.maxScreenRow || x >= Utility.maxScreenCol || tiles[map[y][x]].isSolid;
    }

    /**
     * Retrieves the tile at the specified index.
     *
     * @param index the index of the tile to retrieve
     * @return the tile at the specified index
     */
    public Tile getTile(int index){
        return tiles[index];
    }

    /**
     * Returns the number of tile textures.
     *
     * @return the number of tile textures
     */
    public int getNumTileTextures(){
        return NUM_TILE_TEXTURES;
    }
}
