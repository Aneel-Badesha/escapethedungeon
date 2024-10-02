package panels;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import Main.Utility;

import java.awt.*;
import java.io.IOException;

/**
 * HelpPanel is a JPanel that contains the instructions on how to play the game.
 */
public class HelpPanel extends JPanel{
    /**
     * variable init
     */
    private Image backgroundImage;
    /**
     * variable init
     */
    private JButton backButton;
    /**
     * Constructor for HelpPanel
     * @param panelManager the PanelManager instance.
     */
    public HelpPanel(PanelManager panelManager) {
        try{
            backgroundImage = Utility.preScaleImage(ImageIO.read(getClass().getResource("/Main/blurred.jpg")), Utility.screenWidth, Utility.screenHeight);
        }catch(IOException e){
            e.printStackTrace();
        }

        setLayout(new BorderLayout());  // Set the layout manager

        // Create the "Back" button
        backButton = new JButton("< Back");
        backButton.addActionListener(e -> {
            panelManager.showMainMenu();
        });


        // Add the "Back" button to the top left corner of the panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);
    }

    /**
     * Get the "Back" button
     * @return the "Back" button
     */
    public JButton getBackButton() {
        return backButton;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        //Draw Escape the Dungeon text
        String helpTitleText = "How To Play?";
        Font escapeFont = new Font("Arial", Font.BOLD, 36);
        g2d.setFont(escapeFont);

        //Calculate escape text width and height
        FontMetrics fm = g2d.getFontMetrics();
        int helpTitleTextWidth = fm.stringWidth(helpTitleText);
        int helpTitleTextHeight = fm.getHeight();

        //Calculate escape text position
        int escapeX = (getWidth() - helpTitleTextWidth) / 2;
        int escapeY = (getHeight() - helpTitleTextHeight) / 4 + fm.getAscent();


        //Draw gray shadow
        g2d.setColor(Color.GRAY);
        g2d.drawString(helpTitleText, escapeX + 2, escapeY + 2);

        //Draw white text with line breaks
        g2d.setColor(Color.WHITE);
        g2d.drawString(helpTitleText, escapeX, escapeY);

        String helpText = "Press the W A S D keys to move\n" +
                "Walk over rewards to collect them\n" +
                "Avoid enemies to survive\n" +
                "Avoid traps to not lose your score\n" +
                "Collect potions for a speed boost\n" +
                "Collect all keys to open the door\n";

        Font helpFont = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(helpFont);
        fm = g2d.getFontMetrics();

        // Split the helpText into lines
        String[] lines = helpText.split("\n");

        // Calculate the total height of all lines
        int totalHeight = lines.length * fm.getHeight();

        // Calculate the starting Y position
        escapeY = (getHeight() - totalHeight) / 2 + fm.getAscent();

        // Draw each line separately
        for (String line : lines) {
            int lineWidth = fm.stringWidth(line);
            escapeX = (getWidth() - lineWidth) / 2;
            g2d.drawString(line, escapeX, escapeY);
            escapeY += fm.getHeight();  // Move to the next line
        }


    }
 }
