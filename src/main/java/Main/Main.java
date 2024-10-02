package Main;

import javax.swing.JFrame;

import panels.PanelManager;

/**
 * Main class to start the game
 */
public class Main {
    /**
     * Main method to start the game
     * @param args string arguments
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Escape the Dungeon");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        PanelManager panelManager = PanelManager.getInstance();

        // Add the container to the window
        window.add(panelManager.getContainer());

        // Show the menu panel by default
        panelManager.getMenuPanel().playMusic();
        panelManager.showMainMenu();

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}