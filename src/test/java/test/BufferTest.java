package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.buffer.BufferFactory;
import model.buffer.BufferFactoryImpl;
import model.AudioManager.AudioManager;

class BufferTest {

    private static final String PATH = "src/main/resources/";
    private static final String ERROR_GENERATE = "Buffer not generated correctly";

    @BeforeAll
    static void init() {
        final AudioManager am = new AudioManager();
    }

    @Test
    void testGenerate() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        final BufferFactory factory = new BufferFactoryImpl();

        int id = factory.createBuffer(PATH + "DriftMono.wav");
        assertNotEquals(0, id, ERROR_GENERATE);
        id = factory.createBuffer(PATH + "InnoItalia.wav");
        assertNotEquals(0, id, ERROR_GENERATE);
        id = factory.createBuffer(PATH + "Battle.wav");
        assertNotEquals(0, id, ERROR_GENERATE);
        id = factory.createBuffer(PATH + "EyeTiger.wav");
        assertNotEquals(0, id, ERROR_GENERATE);
        id = factory.createBuffer(PATH + "DriftMono.wav");
        assertNotEquals(0, id, ERROR_GENERATE);
    }

    @Test
    void testGenerateFromCache() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        final BufferFactory factory = new BufferFactoryImpl();
        int same, id;

        id = factory.createBuffer(PATH + "DriftMono.wav");
        assertNotEquals(0, id, ERROR_GENERATE);
        same = id;
        id = factory.createBuffer(PATH + "DriftMono.wav");
        assertEquals(same, id);

        id = factory.createBuffer(PATH + "Battle.wav");
        assertNotEquals(same, id, ERROR_GENERATE);
        same = id;
        id = factory.createBuffer(PATH + "Battle.wav");
        assertEquals(same, id);
    }
}
