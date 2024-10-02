import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import panels.PanelManager;
import panels.MenuPanel;


import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;

public class MenuPanelTest {
    
    private MenuPanel menuPanel;
    private PanelManager panelManager;

    @BeforeEach
    public void setUp() {
        panelManager = PanelManager.getInstance(); // Assume there's a singleton instance for the sake of this example.
        menuPanel = new MenuPanel(panelManager);   // Initialize MenuPanel with the mocked manager.
    }

    // Test if all components (buttons) are initialized correctly
    @Test
    public void testButtonInitialization() {
        

        JButton playButton = findButton(menuPanel, "Play");
        assertNotNull(playButton, "Play button should be initialized");

        JButton helpButton = findButton(menuPanel, "Help");
        assertNotNull(helpButton, "Help button should be initialized");

        JButton difficultiesButton = findButton(menuPanel, "Difficulty");
        assertNotNull(difficultiesButton, "Difficulties button should be initialized");

        JButton quitButton = findButton(menuPanel, "Quit");
        assertNotNull(quitButton, "Quit button should be initialized");
    }

    // Test play button action
    @Test
    public void testPlayButtonAction() {
        JButton playButton = findButton(menuPanel, "Play");
        assertNotNull(playButton);
        playButton.doClick();
        // Assume there's a method to check the currently displayed panel
        assertEquals("game", panelManager.getCurrentPanelName(), "Should switch to game panel");
    }

    // Test help button action
    @Test
    public void testHelpButtonAction() {
        JButton helpButton = findButton(menuPanel, "Help");
        assertNotNull(helpButton);
        helpButton.doClick();
        assertEquals("help", panelManager.getCurrentPanelName(), "Should switch to help panel");
    }

    // Test difficulties button action
    @Test
    public void testDifficultiesButtonAction() {
        JButton difficultiesButton = findButton(menuPanel, "Difficulty");
        assertNotNull(difficultiesButton);
        difficultiesButton.doClick();
        assertEquals("difficulties", panelManager.getCurrentPanelName(), "Should switch to difficulties panel");
    }

    // Test quit button action
    @Test
    public void testQuitButtonAction() {
        JButton quitButton = findButton(menuPanel, "Quit");
        assertNotNull(quitButton);
    }

    // Helper method to find a JButton by text
    private JButton findButton(Container container, String buttonText) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(buttonText)) {
                    return button;
                }
            } else if (component instanceof Container) {
                JButton foundButton = findButton((Container) component, buttonText);
                if (foundButton != null) {
                    return foundButton;
                }
            }
        }
        return null; // Button not found
    }
}
