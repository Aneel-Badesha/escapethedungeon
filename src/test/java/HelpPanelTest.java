import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import panels.HelpPanel;
import panels.PanelManager;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

//test for HelpPanel class
public class HelpPanelTest {

    private HelpPanel helpPanel;
    private PanelManager panelManager;

    //setup for test by instantiating objects
    @BeforeEach
    public void setUp() {
        panelManager = PanelManager.getInstance();
        helpPanel = panelManager.getHelpPanel();
    }

    //init for buttons
    @Test
    public void testButtonInitialization() {
        Component[] components = helpPanel.getComponents();

        assertEquals(1, components.length); //1 button
        assertTrue(components[0] instanceof JPanel); //top panel containing the "Back" button
        JPanel topPanel = (JPanel) components[0];
        Component[] topPanelComponents = topPanel.getComponents();
        assertEquals(1, topPanelComponents.length); //1 button inside top panel
        assertTrue(topPanelComponents[0] instanceof JButton); //back button
    }

    //testing back button
    @Test
    public void testBackButtonAction() {
        JButton backButton = helpPanel.getBackButton();
        assertNotNull(backButton);

        backButton.doClick();

        assertEquals("menu", panelManager.getCurrentPanelName());
    }
}
