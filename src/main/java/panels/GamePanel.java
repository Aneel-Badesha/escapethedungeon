package panels;
import grid.GridManager;

import javax.swing.*;

import Main.Sound;
import Main.UI;
import Main.Utility;
import moving.Player;
import object.ObjectManager;
import moving.EnemyManager;

import java.awt.*;

/**
 * The GamePanel class represents the main panel of the game where the game is played.
 * It extends JPanel and implements Runnable.
 */
public class GamePanel extends JPanel implements Runnable{

    /**
     * new gridManager object
     */
    public final GridManager gridManager = new GridManager(this);

    /**
     * new objectManager object
     */
    public final ObjectManager objectManager = new ObjectManager(this);
    /**
     * new enemyManager object
     */
    public final EnemyManager enemyManager = new EnemyManager(this);
    /**
     * new soundEffects object
     */
    private final Sound soundEffects = new Sound();
    /**
     * new sound object for music
     */
    private final Sound music = new Sound();
    /**
     * new ui object
     */
    private final UI ui = new UI(this);
    /**
     * new thread object
     */
    Thread gameThread;
    /**
     * new keyhandler object
     */
    KeyHandler keyHandler;
    
    /**
     * public player object
     */
    public Player player;

    /**
     * variable to indicate current map
     */
    private int currentMap;
    /**
     * variable to indicate if paused
     */
    private boolean paused = false;
     /**
     * variable to indicate game start time
     */
    private int gameStartTime;

    /**
     * Returns the time elapsed since the game started.
     * @return the time elapsed since the game started.
     */
    public int getTime(){
        return (int)(System.currentTimeMillis() - gameStartTime) / 1000;
    }
    /**
     * public score variable
     */
    private int score = 0;
    private final Object scoreLock = new Object();

    /**
     * Returns the score of the player.
     * @return the score of the player.
     */
    public int getScore(){
        return score;
    }

    /**
     * Sets the score of the player.
     * @param value the score of the player.
     */
    public void setScore(int value){
        synchronized (scoreLock) {
            score = value;
        }
    }
    /**
     * variable to indicate if level is won or not
     */
    private boolean isLevelWon = false;

    /**
     * Returns the score of the player.
     * @return the score of the player.
     */
    public boolean levelWon(){
        return isLevelWon = true;
    }

    /**
     * The GamePanel class represents the main panel of the game.
     * It is responsible for initializing the player, setting the panel's size and background color,
     * enabling double buffering, and handling key events.
     *
     * @param keyHandler The KeyHandler object used to handle key events
     * @param container the Jpanel object used for the game panel
     */
    public GamePanel(KeyHandler keyHandler,JPanel container) {
        this.keyHandler = keyHandler;
        player = new Player(this, this.keyHandler);
        this.setPreferredSize(new Dimension(Utility.screenWidth, Utility.screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(this.keyHandler);

    }

    /**
     * Sets the current map number.
     * @param value the map number.
     */
    public void setCurrentMap(int value)    {
        currentMap = value;
    }

    /**
     * Returns the current map number.
     * @return the current map number.
     */
    public int getCurrentMap()  {
        return currentMap;
    }

    /**
     * Starts the game
     * loads the first map
     * starts the background music
     * sets the game start time
     * sets the score to 0
     * unpauses the game
     * if game thread is not running, starts a new game thread
     */
    public void startGame() {
        if (gameThread == null) {
            this.requestFocusInWindow();
            gameThread = new Thread(this);
        }
        paused = true;
        //setCurrentMap(0);
        setCurrentMap(0);
        gameStartTime = (int)System.currentTimeMillis();
        playMusic(0);
        loadNextMap();
        score = 0;
        paused = false;
        if(gameThread != null && !gameThread.isAlive()){
            gameThread.start();
        }
    }

    /**
     * Handles the game over event.
     * Stops the game thread, stops the background music, and shows the game over screen.
     */
    public void gameOver(){
        if(gameThread != null){
            stopMusic();
            playSound(7);
            player.stopSpeedBuff();
            PanelManager panelManager = PanelManager.getInstance();
            paused = true;
            panelManager.showGameEnd("You Lose!",score);
        }
    }
    /**
     * Handles the victory event.
     * Stops the game thread, stops the background music, and shows the victory screen.
     */
    public void victory(){
        if(gameThread != null){
            paused = true;
            player.stopSpeedBuff();
            PanelManager panelManager = PanelManager.getInstance();
            stopMusic();
            playSound(6);
            panelManager.showGameEnd("You Win!",score);
        }
    }

    /**
     * Loads the next map in the game.
     * If the current map is the last map, triggers the victory event.
     */
    public void loadNextMap(){
        paused = true;
        setCurrentMap(getCurrentMap()+1);

        if(getCurrentMap() > Utility.NUM_MAPS){
            victory();
            return;
        }
        gridManager.loadMap(getCurrentMap());
        objectManager.loadObjects(getCurrentMap());
        enemyManager.loadEnemies(getCurrentMap());
        paused = false;
    }


    /**
     * The main game loop.
     * Updates the game state and repaints the game at a fixed interval.
     */
    @Override
    public void run(){
        double drawInterval = 1000000000 / Utility.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                if(!paused){
                    update();
                    repaint();
                }
                delta--;
            }
        }
    }
    /**
     * Updates the game state.
     * Updates the player, enemy manager, and object manager.
     * If the level is won, loads the next map.
     */
    public void update() {
        if(isLevelWon){
            isLevelWon = false;
            loadNextMap();
        }else{
            player.update();
            enemyManager.update();
            objectManager.update();
        }
    }

    /**
     * Draws the game.
     * Draws the grid manager, object manager, enemy manager, player, and UI.
     * @param g the Graphics object.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        gridManager.draw(g2d);
        objectManager.draw(g2d);
        enemyManager.draw(g2d);
        player.draw(g2d);
        ui.draw(g2d);

        g2d.dispose();
    }


    /**
     * Plays the background music.
     * @param index the index of the music file.
     */
    public void playMusic(int index){
        music.setFile(index);
        music.play();
        music.loop();
    }
    /**
     * Stops the background music.
     */
    public void stopMusic(){
        music.stop();
    }
    /**
     * Plays a sound effect.
     * @param index the index of the sound effect file.
     */
    public void playSound(int index){
        soundEffects.setFile(index);
        soundEffects.play();
    }
    /**
     * Stops the sound effect.
     */
    public void stopSound(){
        soundEffects.stop();
    }

    /**
     * @return true if the game is paused, false otherwise.
     */
    public boolean isPaused(){
        return paused;
    }
}
