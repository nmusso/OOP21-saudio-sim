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
import model.buffer.BufferImpl;
import model.environment.Environment;
import model.environment.EnvironmentFactory;
import model.environment.EnvironmentFactoryImpl;
import model.listener.Listener;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;
import model.source.FreqRangeSource;
import model.source.FreqRangeSourceImpl;
import model.source.Source;
import model.source.SourceFactory;
import model.source.SourceFactoryImpl;
import model.source.SourceImpl;
import model.source.SourceType;

class EnvironmentTest {

    private final Set<FreqRangeSource> sources = new HashSet<>();
    private final ListenerFactory listFac = new ListenerFactoryImpl();
    private final Listener listener = listFac.createListener(AudioManager.getContext());
    private final SourceFactory sourceFac = new SourceFactoryImpl();
    private final EnvironmentFactory envFac = new EnvironmentFactoryImpl();

    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }

    private FreqRangeSource genSource(final FreqRangeSource s) {
        final Buffer b = new BufferImpl("src/main/resources/songs/DriftMono.wav");
        s.generateSource(b.getID());
        return s;
    }

    @Test
    void  testSimplePlayStop() {
        sources.add(genSource(sourceFac.createFreqRangeSource()));
        sources.add(genSource(sourceFac.createFreqRangeSource()));
        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        env.getSourceHub().playAll();
        assertEquals(env.getSourceHub().getPalying(), sources);
        env.getSourceHub().stopAll();
        assertNotEquals(env.getSourceHub().getPalying(), sources);
    }

    @Test
    void testAddSource() {
        sources.add(genSource(sourceFac.createFreqRangeSource()));
        sources.add(genSource(sourceFac.createFreqRangeSource()));

        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        assertEquals(env.getSourceHub().getAll().size(), sources.size());
        env.getSourceHub().addSource((genSource(sourceFac.createFreqRangeSource())));
        assertEquals(env.getSourceHub().getAll().size(), sources.size() + 1);
    }

    @Test
    void testGetX() {
        final FreqRangeSource source1 = genSource(sourceFac.createFreqRangeSource());
        final FreqRangeSource source2 = genSource(sourceFac.createFreqRangeSource());
        final FreqRangeSource source3 = genSource(sourceFac.createFreqRangeSource());
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

        assertEquals(env.getSpace().getLenght(), 10.0f);
        assertNotEquals(env.getSpace().getWidth(), 2.0f);
    }

    //TODO test busy pos


    @Test
    void testListener() {
        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        assertEquals(env.getListener(), listener);
    }
}
