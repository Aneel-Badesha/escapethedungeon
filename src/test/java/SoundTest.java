import Main.Sound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//unit test for sound class
class SoundTest {

    private Sound sound;

    //setup for test
    @BeforeEach
    void setUp() {
        sound = new Sound();
    }

    //testing the sound file loads
    @Test
    void testSetFile() {
        sound.setFile(0);
        assertNotNull(sound.getClip());
    }

    //testing the sound plays
    @Test
    void testPlay() {
        sound.setFile(0);
        sound.play();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(sound.getClip().isRunning());
    }

    //testing the sound loops
    @Test
    void testLoop() {
        sound.setFile(1);
        sound.loop();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(sound.getClip().isRunning());
    }

    //testing the sound stops
    @Test
    void testStop() {
        sound.setFile(2);
        sound.loop();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(sound.getClip().isActive());

        sound.stop();
        assertFalse(sound.getClip().isActive());
    }

    //testing invalid index
    @Test
    void testInvalidIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            sound.setFile(30);
        });
    }

    //testing for exceptions
    @Test
    void testExceptionInSetFile() {
        assertDoesNotThrow(() -> {
            sound.setFile(20);
        });
        assertNull(sound.getClip());
    }
}