package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.lwjgl.openal.AL10.alGetError;
import static org.lwjgl.openal.AL10.AL_NO_ERROR;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import model.audiomanager.AudioManager;
import model.buffer.Buffer;
import model.buffer.BufferFactory;
import model.buffer.BufferFactoryImpl;
import model.extension.effect.ALEffects;
import model.extension.effect.Effect;
import model.extension.effect.EffectImpl;
import model.source.FRSource;
import model.source.SourceFactory;
import model.source.SourceFactoryImpl;
import model.source.SourceType;

class ExtensionsTest {

    private static final String PATH = "src/main/resources/songs/";
    private static final float OFFSET = 0.1f;
    private final BufferFactory bufferFactory = new BufferFactoryImpl();
    private final SourceFactory sourceFactory = new SourceFactoryImpl();
    private Buffer buffer;

    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }

    @Test
    void testLowPass() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        buffer = bufferFactory.createBufferFromPath(PATH + "InnoItalia.wav");

        FRSource source = sourceFactory.createFRSource(SourceType.LOW);
        source.generateSource(buffer.getID());

        assertEquals(alGetError(), AL_NO_ERROR);
        assertEquals(SourceType.LOW, source.getType());

        source = sourceFactory.createFRSource(SourceType.MID);
        source.generateSource(buffer.getID());

        assertEquals(alGetError(), AL_NO_ERROR);
        assertNotEquals(SourceType.LOW, source.getType());

        source = sourceFactory.createFRSource(SourceType.HIGH);
        source.generateSource(buffer.getID());

        assertEquals(alGetError(), AL_NO_ERROR);
        assertNotEquals(SourceType.LOW, source.getType());
    }

    @Test
    void testBandPass() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        buffer = bufferFactory.createBufferFromPath(PATH + "Battle.wav");

        FRSource source = sourceFactory.createFRSource(SourceType.LOW);
        source.generateSource(buffer.getID());

        assertEquals(alGetError(), AL_NO_ERROR);
        assertNotEquals(SourceType.MID, source.getType());

        source = sourceFactory.createFRSource(SourceType.MID);
        source.generateSource(buffer.getID());

        assertEquals(alGetError(), AL_NO_ERROR);
        assertEquals(SourceType.MID, source.getType());

        source = sourceFactory.createFRSource(SourceType.HIGH);
        source.generateSource(buffer.getID());

        assertEquals(alGetError(), AL_NO_ERROR);
        assertNotEquals(SourceType.MID, source.getType());
    }

    @Test
    void testHighPass() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        buffer = bufferFactory.createBufferFromPath(PATH + "DriftMono.wav");

        FRSource source = sourceFactory.createFRSource(SourceType.LOW);
        source.generateSource(buffer.getID());

        assertEquals(alGetError(), AL_NO_ERROR);
        assertNotEquals(SourceType.HIGH, source.getType());

        source = sourceFactory.createFRSource(SourceType.MID);
        source.generateSource(buffer.getID());

        assertEquals(alGetError(), AL_NO_ERROR);
        assertNotEquals(SourceType.HIGH, source.getType());

        source = sourceFactory.createFRSource(SourceType.HIGH);
        source.generateSource(buffer.getID());

        assertEquals(alGetError(), AL_NO_ERROR);
        assertEquals(SourceType.HIGH, source.getType());
    }

    @Test
    void testEffects() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        final Effect manager = new EffectImpl();
        final FRSource source = sourceFactory.createFRSource();

        ALEffects effect = ALEffects.AUTOWAH;

        manager.apply(effect, source, effect.getMinValue());
        assertEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMaxValue());
        assertEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMinValue() - OFFSET);
        assertNotEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMaxValue() + OFFSET);
        assertNotEquals(alGetError(), AL_NO_ERROR);

        effect = ALEffects.CHORUS;

        manager.apply(effect, source, effect.getMinValue());
        assertEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMaxValue());
        assertEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMinValue() - OFFSET);
        assertNotEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMaxValue() + OFFSET);
        assertNotEquals(alGetError(), AL_NO_ERROR);

        effect = ALEffects.REVERB;

        manager.apply(effect, source, effect.getMinValue());
        assertEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMaxValue());
        assertEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMinValue() - OFFSET);
        assertNotEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMaxValue() + OFFSET);
        assertNotEquals(alGetError(), AL_NO_ERROR);

        effect = ALEffects.FLANGER;

        manager.apply(effect, source, effect.getMinValue());
        assertEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMaxValue());
        assertEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMinValue() - OFFSET);
        assertNotEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMaxValue() + OFFSET);
        assertNotEquals(alGetError(), AL_NO_ERROR);

        effect = ALEffects.ECHO;

        manager.apply(effect, source, effect.getMinValue());
        assertEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMaxValue());
        assertEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMinValue() - OFFSET);
        assertNotEquals(alGetError(), AL_NO_ERROR);
        manager.apply(effect, source, effect.getMaxValue() + OFFSET);
        assertNotEquals(alGetError(), AL_NO_ERROR);
    }
}
