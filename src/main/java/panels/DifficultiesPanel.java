package panels;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import Main.Utility;

import java.awt.*;
import java.io.IOException;

/**
 * Difficulties is a JPanel that contains buttons to select the difficulty of the game.
 */

public class DifficultiesPanel extends JPanel{
    
    /**
     * for background image
     */
    private Image backgroundImage;

    /**
     * Constructor for Difficulties
     * creates the buttons for the different difficulties
     * @param panelManager the PanelManager instance.
     */
    public DifficultiesPanel(PanelManager panelManager) {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/Main/blurred.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());  // Set the layout manager

        // Create the "Back" button
        JButton backButton = new JButton("< Back");
        backButton.addActionListener(e -> panelManager.showMainMenu());
        backButton.setBounds(0,0,100,30);

        // Add the "Back" button to the top left corner of the panel
        add(backButton, BorderLayout.NORTH);

        // Create the buttons

        final int BUTTON_WIDTH = 100;
        final int BUTTON_HEIGHT = 30;

        int centerX = (Utility.screenWidth - BUTTON_WIDTH) / 2;
        int centerY = (Utility.screenHeight - 3 * BUTTON_HEIGHT) / 2;

        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");
        easyButton.addActionListener(e -> {
            Utility.setDifficulty(Utility.DIFFICULTY.EASY);
            repaint();
        });
        mediumButton.addActionListener(e -> {
            Utility.setDifficulty(Utility.DIFFICULTY.MEDIUM);
            repaint();
        });
        hardButton.addActionListener(e -> {
            Utility.setDifficulty(Utility.DIFFICULTY.HARD);
            repaint();
        });

        easyButton.setBounds(centerX, centerY, BUTTON_WIDTH, BUTTON_HEIGHT);
        mediumButton.setBounds(centerX, centerY + BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        hardButton.setBounds(centerX, centerY + 2 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

        setLayout(null);
        add(easyButton);
        add(mediumButton);
        add(hardButton);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        //Draw Escape the Dungeon text
        String difficultiesText = "";
        switch(Utility.getDifficulty()){
            case EASY:
                difficultiesText = "Select Difficulty: Easy";
                break;
            case MEDIUM:
                difficultiesText = "Select Difficulty: Medium";
                break;
            case HARD:
                difficultiesText = "Select Difficulty: Hard";
                break;
        }
        Font escapeFont = new Font("Arial", Font.BOLD, 36);
        g2d.setFont(escapeFont);

        //Calculate escape text width and height
        FontMetrics fm = g2d.getFontMetrics();
        int difficultiesTextTextWidth = fm.stringWidth(difficultiesText);
        int difficultiesTextTextHeight = fm.getHeight();

        //Calculate escape text position
        int escapeX = (getWidth() - difficultiesTextTextWidth) / 2;
        int escapeY = (getHeight() - difficultiesTextTextHeight) / 4 + fm.getAscent();


        //Draw gray shadow
        g2d.setColor(Color.GRAY);
        g2d.drawString(difficultiesText, escapeX + 2, escapeY + 2);

        //Draw white text with line breaks
        g2d.setColor(Color.WHITE);
        g2d.drawString(difficultiesText, escapeX, escapeY);

    }
}
