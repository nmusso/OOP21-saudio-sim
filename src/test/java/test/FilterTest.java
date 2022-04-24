package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import model.audiomanager.AudioManager;
import model.buffer.Buffer;
import model.buffer.BufferFactory;
import model.buffer.BufferFactoryWithCache;
import model.source.FRSource;
import model.source.SourceFactory;
import model.source.SourceFactoryImpl;
import model.source.SourceType;

class FilterTest {

    private static final String PATH = "/songs/";
    private final BufferFactory bufferFactory = new BufferFactoryWithCache();
    private final SourceFactory sourceFactory = new SourceFactoryImpl();
    private Buffer buffer;

    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }

    @Test
    void testLowPass() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        buffer = bufferFactory.createBufferFromResource(PATH + "InnoItalia.wav");

        FRSource source = sourceFactory.createFRSource(SourceType.LOW);
        source.generateSource(buffer.getID());

        assertEquals(SourceType.LOW, source.getType());

        source = sourceFactory.createFRSource(SourceType.MID);
        source.generateSource(buffer.getID());

        assertNotEquals(SourceType.LOW, source.getType());

        source = sourceFactory.createFRSource(SourceType.HIGH);
        source.generateSource(buffer.getID());

        assertNotEquals(SourceType.LOW, source.getType());
    }

    @Test
    void testBandPass() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        buffer = bufferFactory.createBufferFromResource(PATH + "Battle.wav");

        FRSource source = sourceFactory.createFRSource(SourceType.LOW);
        source.generateSource(buffer.getID());

        assertNotEquals(SourceType.MID, source.getType());

        source = sourceFactory.createFRSource(SourceType.MID);
        source.generateSource(buffer.getID());

        assertEquals(SourceType.MID, source.getType());

        source = sourceFactory.createFRSource(SourceType.HIGH);
        source.generateSource(buffer.getID());

        assertNotEquals(SourceType.MID, source.getType());
    }

    @Test
    void testHighPass() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        buffer = bufferFactory.createBufferFromResource(PATH + "DriftMono.wav");

        FRSource source = sourceFactory.createFRSource(SourceType.LOW);
        source.generateSource(buffer.getID());

        assertNotEquals(SourceType.HIGH, source.getType());

        source = sourceFactory.createFRSource(SourceType.MID);
        source.generateSource(buffer.getID());

        assertNotEquals(SourceType.HIGH, source.getType());

        source = sourceFactory.createFRSource(SourceType.HIGH);
        source.generateSource(buffer.getID());

        assertEquals(SourceType.HIGH, source.getType());
    }
}
