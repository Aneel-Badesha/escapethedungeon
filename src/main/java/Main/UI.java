package Main;

import java.awt.Graphics2D;

import panels.GamePanel;

import java.awt.Font;
import java.awt.Color;
/**
 * The UI class is responsible for drawing the user interface of the game.
 */
public class UI {
    GamePanel gp;
    Font font;

    /**
     * for UI
     * @param gp gamepanel
     */
    public UI(GamePanel gp){
        this.gp = gp;
        font = new Font("Arial", Font.BOLD, 40);
    }

    /**
     * Draws the user interface of the game. Currently the score and time are drawn.
     * @param g2d the Graphics2D object
     */
    public void draw(Graphics2D g2d){
    
        //Draw shadow
        g2d.setFont(font);
        g2d.setColor(Color.GRAY);
        g2d.drawString("Score: " + gp.getScore(), 12, 52);
        
        //Draw actual text
        g2d.setColor(Color.WHITE);
        g2d.drawString("Score: " + gp.getScore(), 10, 50);

        //Draw shadow
        g2d.setFont(font);
        g2d.setColor(Color.GRAY);
        g2d.drawString("Time: " + gp.getTime(), 12, 94);
        
        //Draw actual text
        g2d.setColor(Color.WHITE);
        g2d.drawString("Time: " + gp.getTime(), 10, 92);
    }

    
}