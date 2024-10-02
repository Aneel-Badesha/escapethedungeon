package panels;

import javax.imageio.ImageIO;
import javax.swing.*;

import Main.Sound;

import java.awt.*;
import java.io.IOException;

import Main.Utility;
import object.Key;

/**
 * MenuPanel is a JPanel that contains a button to start the game.
 */
public class GameEndPanel extends JPanel {
    
    /**
     * new button objects
     */
    private JButton playButton, helpButton, difficultiesButton, quitButton,backButton;
    private Image backgroundImage;
    /**
     * new music object
     */
    private boolean isMusicPlaying = false;
    public boolean isMusicPlaying() {
        return isMusicPlaying;
    }
    public JButton getPlayButton() {
        return playButton;
    }
    public JButton getHelpButton() {
        return helpButton;
    }
    public JButton getDifficultiesButton() {
        return difficultiesButton;
    }
    public JButton getQuitButton() {
        return quitButton;
    }
    public JButton getBackButton() {
        return backButton;
    }
    Sound music = new Sound();

    /**
     * new key object
     */
    Key key;

    /**
     * new string text var
     */
    private String text = "";
    
    /**
     * var for score
     */
    private int score = 0;
    
    /**
     * Set the text to be displayed on the menu panel
     * @param text text for menu panel
     */
    public void setText(String text) {
        this.text = text;
    }
    /**
     * Set the score to be displayed on the menu panel
     * @param score score for the menu panel
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * Get the text displayed on the menu panel
     * @return the text displayed on the menu panel
     */
    public String getText() {
        return text;
    }
    /**
     * Get the score displayed on the menu panel
     * @return the score displayed on the menu panel
     */
    public int getScore(){
        return score;
    }


    /**
     * Constructor for MenuPanel
     *
     * @param panelManager the PanelManager instance.
     */
    public GameEndPanel(PanelManager panelManager) {
        try{
            backgroundImage = Utility.preScaleImage(ImageIO.read(getClass().getResource("/Main/blurred.jpg")), Utility.screenWidth, Utility.screenHeight);
        }catch(IOException e){
            e.printStackTrace();
        }

        // Create the "Back" button
        backButton = new JButton("< Back");
        backButton.addActionListener(e -> {
            panelManager.getMenuPanel().playMusic();
            panelManager.showMainMenu();
        });


        // Add the "Back" button to the top left corner of the panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.add(backButton);


        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // button settings
        playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            if(isMusicPlaying() == true){
                stopMusic();
            }
            panelManager.showGame();
        });
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(Color.GRAY);
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        playButton.setFont(buttonFont);
        playButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        helpButton = new JButton("Help");
        helpButton.addActionListener(e -> {
            panelManager.getMenuPanel().playMusic();
            panelManager.showHelp();
        });
        helpButton.setForeground(Color.WHITE);
        helpButton.setBackground(Color.GRAY);
        helpButton.setFont(buttonFont);
        helpButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        difficultiesButton = new JButton("Difficulty");
        difficultiesButton.addActionListener(e -> {
            panelManager.getMenuPanel().playMusic();
            panelManager.showDifficulties();
        });
        difficultiesButton.setForeground(Color.WHITE);
        difficultiesButton.setBackground(Color.BLUE);
        difficultiesButton.setFont(buttonFont);
        difficultiesButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
        quitButton.setForeground(Color.WHITE);
        quitButton.setBackground(Color.PINK);
        quitButton.setFont(buttonFont);
        quitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        // Help button at bottom left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(difficultiesButton, gbc);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(backButton,gbc);


        // Create a new panel for the buttons on the right
        Dimension buttonSize = new Dimension(60, 60);
        Dimension largeButtonSize = new Dimension(120, 60);
        playButton.setPreferredSize(buttonSize);
        playButton.setMaximumSize(buttonSize);
        difficultiesButton.setPreferredSize(largeButtonSize);
        difficultiesButton.setMaximumSize(largeButtonSize);
        quitButton.setPreferredSize(buttonSize);
        quitButton.setMaximumSize(buttonSize);
        helpButton.setPreferredSize(buttonSize);
        helpButton.setMaximumSize(buttonSize);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.add(playButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        rightPanel.add(helpButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        rightPanel.add(quitButton);

        // Add the right panel to the main panel
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        add(rightPanel, gbc);




    }

    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    drawBackground(g2d);
    drawText(g2d);
}

private void drawBackground(Graphics2D g2d) {
    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
}

private void drawText(Graphics2D g2d) {
    // Settings for title text
    Font titleFont = new Font("Arial", Font.BOLD, 75);
    g2d.setFont(titleFont);
    drawShadowedText(g2d, text, getWidth() / 2, getHeight() / 4, titleFont);

    // Settings for score text
    Font scoreFont = new Font("Arial", Font.BOLD, 39);
    g2d.setFont(scoreFont);
    int scoreY = getHeight() / 4 + g2d.getFontMetrics().getHeight() * 2; // Adjust position based on title text
    drawShadowedText(g2d, "Score: " + score, getWidth() / 2, scoreY, scoreFont);
}

private void drawShadowedText(Graphics2D g2d, String text, int centerX, int centerY, Font font) {
    FontMetrics fm = g2d.getFontMetrics(font);
    int textWidth = fm.stringWidth(text);
    int textHeight = fm.getHeight();
    int textX = centerX - textWidth / 2;
    int textY = centerY + fm.getAscent() - textHeight / 2;

    g2d.setColor(Color.GRAY);
    g2d.drawString(text, textX + 2, textY + 2); // Shadow
    g2d.setColor(Color.WHITE);
    g2d.drawString(text, textX, textY); // Actual text
}
    /**
     * Plays the background music.
     */
    public void playMusic() {
        isMusicPlaying = true;
        music.setFile(1);
        music.loop();
    }
    /**
     * Stops the background music.
     */
    public void stopMusic() {
        isMusicPlaying = false;
        music.stop();
    }
}
