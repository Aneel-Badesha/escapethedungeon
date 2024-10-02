package panels;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.Sound;
import Main.Utility;
/**
 * A JPanel class that serves as the main menu for a game application.
 * It provides buttons to play the game, access help, adjust difficulty, and quit the application.
 * Additionally, it handles the loading and display of a background image and controls background music playback.
 */
public class MenuPanel extends JPanel {
    private JButton playButton, helpButton, difficultiesButton, quitButton;
    private Image backgroundImage;
    private Sound music = new Sound();
    private boolean isMusicPlaying = false;
    private PanelManager panelManager;
    /**
     * Constructs a MenuPanel with a reference to a PanelManager.
     *
     * @param panelManager the PanelManager that manages game panels and interactions.
     */
    public MenuPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        initializeBackground();
        initializeButtons();
        setupLayout();
    }
     /**
     * Initializes the background image of the panel.
     * Displays an error message if the image fails to load.
     */
    private void initializeBackground() {
        try {
            backgroundImage = Utility.preScaleImage(ImageIO.read(getClass().getResource("/Main/main.jpg")), Utility.screenWidth, Utility.screenHeight);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load background image.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Initializes the buttons used in the menu panel.
     */
    private void initializeButtons() {
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        playButton = createButton("Play", Color.GRAY, buttonFont, new Dimension(60, 60), this::toggleMusicAndStartGame);
        helpButton = createButton("Help", Color.GRAY, buttonFont, new Dimension(60, 60), e -> panelManager.showHelp());
        difficultiesButton = createButton("Difficulty", Color.BLUE, buttonFont, new Dimension(120, 60), e -> panelManager.showDifficulties());
        quitButton = createButton("Quit", Color.PINK, buttonFont, new Dimension(60, 60), e -> System.exit(0));
    }
    /**
     * Creates a button with specified properties.
     *
     * @param text the text to display on the button.
     * @param background the background color of the button.
     * @param font the font of the button text.
     * @param size the size of the button.
     * @param actionListener the action listener associated with the button.
     * @return the newly created button.
     */
    private JButton createButton(String text, Color background, Font font, Dimension size, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setForeground(Color.WHITE);
        button.setBackground(background);
        button.setFont(font);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        return button;
    }
    /**
     * Sets up the layout of the menu panel using GridBagConstraints.
     */

    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(difficultiesButton, gbc);

        JPanel rightPanel = createRightPanel();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        add(rightPanel, gbc);
    }
    /**
     * Creates a panel for holding the main buttons on the right side of the MenuPanel.
     * 
     * @return the panel containing the buttons.
     */
    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.add(playButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(helpButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(quitButton);
        return panel;
    }
    /**
     * Toggles the music playback when starting the game.
     *
     * @param e the ActionEvent from the play button.
     */
    private void toggleMusicAndStartGame(ActionEvent e) {
        if (isMusicPlaying) {
            stopMusic();
        }
        panelManager.showGame();
    }
    /**
     * Plays the music when requireed
     */
    public void playMusic() {
        if (!isMusicPlaying) {
            isMusicPlaying = true;
            music.setFile(1);  // Assumes 1 is the ID for the track
            music.loop();
        }
    }
    /**
     * Stops the music when required
     */
    public void stopMusic() {
        if (isMusicPlaying) {
            isMusicPlaying = false;
            music.stop();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
