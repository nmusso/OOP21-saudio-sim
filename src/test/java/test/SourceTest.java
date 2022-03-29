package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import model.buffer.Buffer;
import model.environment.Environment;
import model.environment.EnvironmentImpl;
import model.listener.Listener;
import model.listener.ListenerImpl;
import model.source.*;
import model.audiomanager.AudioManager;
import model.buffer.*;
import model.utility.*;

class SourceTest {

    private final Source s = new SourceImpl();

    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }

    private void genSource() {
        final Buffer b = new BufferImpl("src/main/resources/InnoItalia.wav");
        try {
            s.generateSource(b.generateBuffer());
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testBasicPlayPauseStop() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        genSource();

        s.play();
        assertTrue(s.isPlaying());
        s.pause();
        assertFalse(s.isPlaying());
        s.play();
        s.stop();
        assertFalse(s.isPlaying());
    }

    @Test
    void testAdvancedPlayPauseStop() {
        genSource();

        s.play();
        s.pause();
        s.play();
        assertTrue(s.isPlaying());
        s.stop();
        s.play();
        assertTrue(s.isPlaying());
        s.pause();
        s.stop();
        s.play();
        assertTrue(s.isPlaying());
    }

    @Test
    void testBasicChangePosition() {
        genSource();
        final float x = 1.0f;
        final float y = -10.0f;
        final float z = 0.5f;

        final Source s = new SourceImpl(new Vec3f(0.0f));
        assertEquals(s.getPosition(), new Vec3f(0.0f));
        final Vec3f pos = new Vec3f(x, y, z);
        s.setPosition(pos);
        assertEquals(s.getPosition(), pos);
    }
}
