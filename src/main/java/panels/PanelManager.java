package panels;

import javax.swing.JPanel;
import java.awt.*;


/**
 * PanelManager class
 * Manages the all panels
 * Uses a CardLayout to switch between the panels
 */
public class PanelManager {
    /**
     * variable init
     */
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private CardLayout cardLayout;
    private JPanel container;
    private GameEndPanel gameEndPanel;
    private HelpPanel helpPanel;
    private DifficultiesPanel difficultiesPanel;
    
    /**
     * variable init
     */
    public static PanelManager instance;
    private String currentPanelName = "menu";

    /**
     * getter for instance
     * @return instance
     */
    public static PanelManager getInstance() {
        if (instance == null) {
            instance = new PanelManager();
        }
        return instance;
    }
    /**
     * Constructor for PanelManager
     * Initializes the cardLayout and container
     * Adds the menuPanel and gamePanel to the container
     * Adds a KeyHandler to the container
     */
    private PanelManager() {
        cardLayout = new CardLayout();
        container = new JPanel();
        container.setLayout(cardLayout);
        container.setFocusable(true);
        KeyHandler keyHandler = new KeyHandler();
        container.addKeyListener(keyHandler);

        gamePanel = new GamePanel(keyHandler,container);
        menuPanel = new MenuPanel(this);
        gameEndPanel = new GameEndPanel(this);
        helpPanel = new HelpPanel(this);
        difficultiesPanel = new DifficultiesPanel(this);

//        winPanel = new WinPanel(this);

        container.add(gameEndPanel,"end");
        container.add(menuPanel, "menu");
        container.add(gamePanel, "game");
        container.add(helpPanel, "help");
        container.add(difficultiesPanel, "difficulties");


    }


    /**
     * Shows the main menu
     */
    public void showMainMenu() {
        currentPanelName = "menu";
        cardLayout.show(container, "menu");
    }
    /**
     * Shows the game
     */
    public void showGame() {
        currentPanelName = "game";
        gamePanel.startGame();
        cardLayout.show(container, currentPanelName);
    }

    /**
     * show win on panel
     * @param score score value
     */
    public void showWin(int score){
        currentPanelName = "win";
        cardLayout.show(container, currentPanelName);
        //winPanel.setScore(score);
    }
    /**
     * Show the Game End panel
     * @param text end of game text
     * @param score end of game score
     */
    public void showGameEnd(String text, int score){
        gameEndPanel.setScore(score);
        gameEndPanel.setText(text);
        currentPanelName = "end";
        cardLayout.show(container, currentPanelName);
    }

    /**
     *   Show the help menu
     */
    public void showHelp() {
        currentPanelName = "help";
        cardLayout.show(container, currentPanelName);
    }

    /**
     *   Show the difficulties menu
     */
    public void showDifficulties() {
        currentPanelName = "difficulties";
        cardLayout.show(container, currentPanelName);
    }

    /**
     * Returns the container
     * @return container
     */
    public JPanel getContainer() {
        return container;
    }
    /**
     * Returns the gamePanel
     * @return gamePanel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Returns the MenuPanel
     * @return menuPanel
     */
    public MenuPanel getMenuPanel(){
        return menuPanel;
    }
    /**
     * Returns the GameEndPanel
     * @return gameEndPanel
     */
    public GameEndPanel getGameEndPanel(){
        return gameEndPanel;
    }
    /**
     * Returns the HelpPanel
     * @return helpPanel
     */
    public HelpPanel getHelpPanel(){
        return helpPanel;
    }

    /**
     * get current showing panel
     * @return currentPanelName
     */
    public String getCurrentPanelName(){
        return currentPanelName;
    }
}