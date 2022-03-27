package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.buffer.Buffer;
import model.source.*;
import model.buffer.*;
import model.utility.*;

public class SourceTest {

    @Test
    public void testBasicPlayPauseStop() {
        final Source s = new SourceImpl();
        //final Buffer b = new BufferImpl();
        //s.generateSource(b.generateBuffer())

        s.play();
        assertTrue(s.isPlaying());
        s.pause();
        assertFalse(s.isPlaying());
        s.play();
        s.stop();
        assertFalse(s.isPlaying());
    }

    @Test
    public void testAdvancedPlayPauseStop() {
        final Source s = new SourceImpl();
        //final Buffer b = new BufferImpl();
        //s.generateSource(b.generateBuffer())

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
    public void testBasicChangePosition() {
        //TODO initialize (waiting for AudioManager class) 
        final Source s = new SourceImpl(new Vec3f(0.0f, 0.0f, 0.0f));

        assertEquals(s.getPosition(), new Vec3f(0.0f, 0.0f, 0.0f));
        Vec3f pos = new Vec3f(1.0f, -10.0f, 0.5f);
        s.setPosition(pos);
        assertEquals(s.getPosition(), pos);
    }
}
