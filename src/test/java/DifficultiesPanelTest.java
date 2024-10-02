
import Main.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import panels.DifficultiesPanel;
import panels.PanelManager;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

//this is a unit test for the GameEndPanel class
public class DifficultiesPanelTest {

    private DifficultiesPanel difficultiesPanel;
    private PanelManager panelManager;

    //initialize by instantiating PanelManager
    @BeforeEach
    public void setUp() {
        panelManager = PanelManager.getInstance();
        difficultiesPanel = new DifficultiesPanel(panelManager);  //new GameEndPanel object
    }

    //test button initialization
    @Test
    public void testButtonInitialization() {
        Component[] components = difficultiesPanel.getComponents();
        assertEquals(4, components.length);
        assertTrue(components[0] instanceof JButton); //back button
        assertTrue(components[1] instanceof JButton);  //easy button
        assertTrue(components[2] instanceof JButton);  //medium button
        assertTrue(components[2] instanceof JButton); //hard button
    }



    //test the BackToMenu button
    @Test
    public void testBackToMenuButtonAction() {
        JButton backToMenuButton = findButton(difficultiesPanel, "< Back");
        assertNotNull(backToMenuButton);

        backToMenuButton.doClick();

        assertEquals("menu", panelManager.getCurrentPanelName());
    }

    //test the easy button
    @Test
    public void testEasyButtonAction() {
        JButton EasyButton = findButton(difficultiesPanel, "Easy");
        assertNotNull(EasyButton);

        EasyButton.doClick();

        assertEquals(Utility.DIFFICULTY.EASY,Utility.getDifficulty());
    }

    //test the easy button
    @Test
    public void testMediumButtonAction() {
        JButton MediumButton = findButton(difficultiesPanel, "Medium");
        assertNotNull(MediumButton);

        MediumButton.doClick();

        assertEquals(Utility.DIFFICULTY.MEDIUM,Utility.getDifficulty());
    }

    //test the easy button
    @Test
    public void testHardButtonAction() {
        JButton hardButton = findButton(difficultiesPanel, "Hard");
        assertNotNull(hardButton);

        hardButton.doClick();

        assertEquals(Utility.DIFFICULTY.HARD,Utility.getDifficulty());
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
}