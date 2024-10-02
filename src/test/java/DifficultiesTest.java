import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.JButton;
import java.awt.*;

import Main.Utility;
import panels.DifficultiesPanel;
import panels.PanelManager;

import static org.junit.jupiter.api.Assertions.*;

//this is a unit test to test the difficulites buttons
public class DifficultiesTest {

    private DifficultiesPanel difficultiesPanel;
    private PanelManager panelManager;

    //set up by instantiating PanelManager
    @BeforeEach
    public void setUp() {
        panelManager = PanelManager.getInstance();
        difficultiesPanel = new DifficultiesPanel(panelManager);
    }

    //initialize to test the buttons:
    @Test
    public void testButtonInitialization() {
        Component[] components = difficultiesPanel.getComponents();

        assertEquals(4, components.length);
        assertTrue(components[0] instanceof JButton); //back button
        assertTrue(components[1] instanceof JButton); //easy difficulty button
        assertTrue(components[2] instanceof JButton); //medium difficulty button
        assertTrue(components[3] instanceof JButton); //hard difficulty button
    }

    //testing easy button
    @Test
    public void testEasyButtonAction() {
        JButton easyButton = findButton(difficultiesPanel, "Easy");
        assertNotNull(easyButton);

        easyButton.doClick();

        assertEquals(Utility.DIFFICULTY.EASY, Utility.getDifficulty());
    }

    //testing medium button
    @Test
    public void testMediumButtonAction() {
        JButton mediumButton = findButton(difficultiesPanel, "Medium");
        assertNotNull(mediumButton);
            
        mediumButton.doClick();

        assertEquals(Utility.DIFFICULTY.MEDIUM, Utility.getDifficulty());
    }

    //testing hard button
    @Test
    public void testHardButtonAction() {
        JButton hardButton = findButton(difficultiesPanel, "Hard");
        assertNotNull(hardButton);

        hardButton.doClick();

        assertEquals(Utility.DIFFICULTY.HARD, Utility.getDifficulty());
    }

    //finds the buttons using getComponents
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