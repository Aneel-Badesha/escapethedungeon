package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.net.URL;
import javax.sound.sampled.AudioSystem;

/**
 * The Sound class represents a sound player in the game.
 * It provides methods to load, play, loop, and stop sounds.
 */
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    /**
     * getter method
     * @return clip value
     */
    public Clip getClip() {
        return clip;
    }

    /**
     * Constructs a Sound object and initializes the sound URLs.
     */
    public Sound(){
        soundURL[0] = getClass().getResource("/Sounds/Music/gameplay.wav");
        soundURL[1] = getClass().getResource("/Sounds/Music/Dungeon.wav");
        soundURL[2] = getClass().getResource("/Sounds/Effects/coin.wav");
        soundURL[3] = getClass().getResource("/Sounds/Effects/unlock.wav");
        soundURL[4] = getClass().getResource("/Sounds/Effects/powerup.wav");
        soundURL[5] = getClass().getResource("/Sounds/Effects/stairs.wav");
        soundURL[6] = getClass().getResource("/Sounds/Effects/fanfare.wav");
        soundURL[7] = getClass().getResource("/Sounds/Effects/gameover.wav");
        soundURL[8] = getClass().getResource("/Sounds/Effects/hitmonster.wav");
    }

    /**
     * Sets the sound file to be played based on the given index.
     * 
     * @param index the index of the sound file to be played
     */
    public void setFile(int index){
        if(index < 0 || index >= soundURL.length)
            throw new ArrayIndexOutOfBoundsException("Invalid index");

        try{
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(sound);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Plays the sound from the beginning.
     */
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Loops the sound continuously.
     */
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the sound.
     */
    public void stop(){
        clip.stop();
        clip.flush();
    }
}
