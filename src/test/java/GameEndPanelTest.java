
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import panels.GameEndPanel;
import panels.PanelManager;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

//this is a unit test for the GameEndPanel class
public class GameEndPanelTest {

    private GameEndPanel gameEndPanel;
    private PanelManager panelManager;

    //initialize by instantiating PanelManager
    @BeforeEach
    public void setUp() {
        panelManager = PanelManager.getInstance();
        gameEndPanel = new GameEndPanel(panelManager);  //new GameEndPanel object
    }

    //test button initialization
    @Test
    public void testButtonInitialization() {
        Component[] components = gameEndPanel.getComponents();
        assertEquals(3, components.length);
        assertTrue(components[0] instanceof JButton); //play again button
        assertTrue(components[1] instanceof JButton);  //back to menu button
        assertTrue(components[2] instanceof JPanel);  // group of play again, help, quit button

    }

    //test the PlayAgain button
    @Test
    public void testPlayAgainButtonAction() {
        JButton playButton = findButtonInPanel(gameEndPanel, "Play");
        assertNotNull(playButton);
        // Simulate a button click
        playButton.doClick();

        assertEquals("game", panelManager.getCurrentPanelName());
    }
    //test the help button
    @Test
    public void testHelpButtonAction() {
        JButton helpButton = findButtonInPanel(gameEndPanel, "Help");
        assertNotNull(helpButton);
        // Simulate a button click
        helpButton.doClick();

        assertEquals("help", panelManager.getCurrentPanelName());
    }
    //test the quit button
    @Test
    public void testQuitButtonAction() {
        JButton quitButton = findButtonInPanel(gameEndPanel, "Quit");
        assertNotNull(quitButton);
    }

    //test the BackToMenu button
    @Test
    public void testBackToMenuButtonAction() {
        JButton backToMenuButton = findButton(gameEndPanel, "< Back");
        assertNotNull(backToMenuButton);

        backToMenuButton.doClick();

        assertEquals("menu", panelManager.getCurrentPanelName());
    }

    //test the Difficulty button
    @Test
    public void testDifficultyButtonAction() {
        JButton backToMenuButton = findButton(gameEndPanel, "Difficulty");
        assertNotNull(backToMenuButton);

        backToMenuButton.doClick();

        assertEquals("difficulties", panelManager.getCurrentPanelName());
    }

    //find button
    private JButton findButton(Container container, String buttonText) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(buttonText)) {
                    return button;
                }
            }
        }
        return null;
    }

    private JButton findButtonInPanel(Container container, String buttonText) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton && ((JButton) component).getText().equals(buttonText)) {
                return (JButton) component;
            } else if (component instanceof Container) {
                JButton foundButton = findButtonInPanel((Container) component, buttonText);
                if (foundButton != null) {
                    return foundButton;
                }
            }
        }
        return null; // Button not found
    }
}