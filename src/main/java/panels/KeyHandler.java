package panels;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyHandler is a KeyListener that keeps track of the arrow keys.
 * up, down, left, and right are set to true when the corresponding arrow key is pressed.
 */
public class KeyHandler implements KeyListener{
    private boolean up, down, left, right;

    /**
     * Returns true if the up arrow key is pressed.
     * @return true if the up arrow key is pressed
     */
    public boolean isUpPressed() {
        return up;
    }

    /**
     * Returns true if the down arrow key is pressed.
     * @return true if the down arrow key is pressed
     */
    public boolean isDownPressed() {
        return down;
    }

    /**
     * Returns true if the left arrow key is pressed.
     * @return true if the left arrow key is pressed
     */
    public boolean isLeftPressed() {
        return left;
    }

    /**
     * Returns true if the right arrow key is pressed.
     * @return true if the right arrow key is pressed
     */
    public boolean isRightPressed() {
        return right;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        //pass
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            up = true;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            down = true;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            left = true;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            up = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            down = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            left = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            right = false;
        }
    }
    
}
