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
import model.buffer.BufferFactoryImpl;

class BufferTest {

    private static final String FILE_PATH = "src/main/resources/songs/";
    private static final String RES_PATH = "/songs/";
    private static final String ERROR_GENERATE = "Buffer not generated correctly";

    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }

    @Test
    void testCreateFromPath() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        final BufferFactory factory = new BufferFactoryImpl();

        Buffer buffer = factory.createBufferFromPath(FILE_PATH + "DriftMono.wav");
        assertNotEquals(0, buffer.getID(), ERROR_GENERATE);
        buffer = factory.createBufferFromPath(FILE_PATH + "InnoItalia.wav");
        assertNotEquals(0, buffer.getID(), ERROR_GENERATE);
        buffer = factory.createBufferFromPath(FILE_PATH + "DriftMono.wav");
        assertNotEquals(0, buffer.getID(), ERROR_GENERATE);
        buffer = factory.createBufferFromPath(FILE_PATH + "EyeTiger.wav");
        assertNotEquals(0, buffer.getID(), ERROR_GENERATE);
    }

    @Test
    void testCreateFromResource() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        final BufferFactory factory = new BufferFactoryImpl();

        Buffer buffer = factory.createBufferFromResource(RES_PATH + "DriftMono.wav");
        assertNotEquals(0, buffer.getID(), ERROR_GENERATE);
        buffer = factory.createBufferFromResource(RES_PATH + "InnoItalia.wav");
        assertNotEquals(0, buffer.getID(), ERROR_GENERATE);
        buffer = factory.createBufferFromResource(RES_PATH + "Battle.wav");
        assertNotEquals(0, buffer.getID(), ERROR_GENERATE);
        buffer = factory.createBufferFromResource(RES_PATH + "InnoItalia.wav");
        assertNotEquals(0, buffer.getID(), ERROR_GENERATE);
    }

    @Test
    void testGetFromCache() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        final BufferFactory factory = new BufferFactoryImpl();
        Buffer buffer;
        int same;

        buffer = factory.createBufferFromPath(FILE_PATH + "EyeTiger.wav");
        assertNotEquals(0, buffer.getID(), ERROR_GENERATE);
        same = buffer.getID();
        buffer = factory.createBufferFromPath(FILE_PATH + "EyeTiger.wav");
        assertEquals(same, buffer.getID());

        buffer = factory.createBufferFromPath(FILE_PATH + "Battle.wav");
        assertNotEquals(same, buffer.getID(), ERROR_GENERATE);
        same = buffer.getID();
        buffer = factory.createBufferFromPath(FILE_PATH + "Battle.wav");
        assertEquals(same, buffer.getID());
    }
}
