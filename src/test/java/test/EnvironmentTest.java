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
        sources.add(genSource(sourceFac.createDefaultFRSource()));
        sources.add(genSource(sourceFac.createDefaultFRSource()));
        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        env.getSourcesHub().playAll();
        assertEquals(env.getSourcesHub().getPlaying(), sources);
        env.getSourcesHub().stopAll();
        assertNotEquals(env.getSourcesHub().getPlaying(), sources);
    }

    @Test
    void testAddSource() {
        sources.add(genSource(sourceFac.createDefaultFRSource()));
        sources.add(genSource(sourceFac.createDefaultFRSource()));

        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        assertEquals(env.getSourcesHub().getAll().size(), sources.size());
        env.getSourcesHub().addSource(genSource(sourceFac.createDefaultFRSource()));
        assertEquals(env.getSourcesHub().getAll().size(), sources.size() + 1);
    }

    @Test
    void testGetX() {
        final FRSource source1 = genSource(sourceFac.createDefaultFRSource());
        final FRSource source2 = genSource(sourceFac.createDefaultFRSource());
        final FRSource source3 = genSource(sourceFac.createDefaultFRSource());
        sources.add(source1);
        sources.add(source2);
        sources.add(source3);
        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        assertEquals(env.getSourcesHub().getSource(1), Optional.of(source1));
        assertEquals(env.getSourcesHub().getSource(2), Optional.of(source2));
        assertNotEquals(env.getSourcesHub().getSource(1), Optional.of(source2));
    }

    @Test
    void testListener() {
        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        assertEquals(env.getListener(), listener);
    }
}
