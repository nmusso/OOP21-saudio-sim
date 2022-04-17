package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.audiomanager.AudioManager;
import model.buffer.Buffer;
import model.buffer.ResourceBuffer;
import model.environment.Environment;
import model.environment.EnvironmentFactory;
import model.environment.EnvironmentFactoryImpl;
import model.listener.Listener;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;
import model.source.FRSource;
import model.source.SourceFactory;
import model.source.SourceFactoryImpl;

class EnvironmentTest {

    private final Set<FRSource> sources = new HashSet<>();
    private final ListenerFactory listFac = new ListenerFactoryImpl();
    private final Listener listener = listFac.createListener(AudioManager.getContext());
    private final SourceFactory sourceFac = new SourceFactoryImpl();
    private final EnvironmentFactory envFac = new EnvironmentFactoryImpl();

    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }

    private FRSource genSource(final FRSource s) {
        Buffer b;
        try {
            b = new ResourceBuffer("/songs/DriftMono.wav");
            s.generateSource(b.getID());
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Test
    void  testSimplePlayStop() {
        sources.add(genSource(sourceFac.createFRSource()));
        sources.add(genSource(sourceFac.createFRSource()));
        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        env.getSourceHub().playAll();
        assertEquals(env.getSourceHub().getPlaying(), sources);
        env.getSourceHub().stopAll();
        assertNotEquals(env.getSourceHub().getPlaying(), sources);
    }

    @Test
    void testAddSource() {
        sources.add(genSource(sourceFac.createFRSource()));
        sources.add(genSource(sourceFac.createFRSource()));

        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        assertEquals(env.getSourceHub().getAll().size(), sources.size());
        env.getSourceHub().addSource(genSource(sourceFac.createFRSource()));
        assertEquals(env.getSourceHub().getAll().size(), sources.size() + 1);
    }

    @Test
    void testGetX() {
        final FRSource source1 = genSource(sourceFac.createFRSource());
        final FRSource source2 = genSource(sourceFac.createFRSource());
        final FRSource source3 = genSource(sourceFac.createFRSource());
        sources.add(source1);
        sources.add(source2);
        sources.add(source3);
        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        assertEquals(env.getSourceHub().getSource(1), source1);
        assertEquals(env.getSourceHub().getSource(2), source2);
        assertNotEquals(env.getSourceHub().getSource(1), source2);
    }

    @Test
    void testSpace() {
        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        assertEquals(env.getSpace().getXmax(), 10.0f);
        assertNotEquals(env.getSpace().getXmax(), 2.0f);
    }

    @Test
    void testListener() {
        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        assertEquals(env.getListener(), listener);
    }
}
