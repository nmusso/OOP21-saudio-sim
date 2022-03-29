package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.AudioManager.AudioManager;
import model.buffer.Buffer;
import model.buffer.BufferImpl;
import model.environment.Environment;
import model.environment.EnvironmentImpl;
import model.listener.Listener;
import model.listener.ListenerImpl;
import model.source.Source;
import model.source.SourceImpl;

class EnvironmentTest {

    private final List<Source> sources = new LinkedList<>();
    private final Listener listener = new ListenerImpl();

    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }
    private Source genSource(final Source s) {
        final Buffer b = new BufferImpl("src/main/resources/InnoItalia.wav");
        try {
            s.generateSource(b.generateBuffer());
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Test
    void  testSimplePlayStop() {
        sources.add(genSource(new SourceImpl()));
        sources.add(genSource(new SourceImpl()));
        final Environment env = new EnvironmentImpl(sources.stream().collect(Collectors.toList()), listener);

        env.playAllSources();
        assertEquals(env.getPlayingSources(), sources);
        env.stopAllSources();
        assertNotEquals(env.getPlayingSources(), sources);
    }

    @Test
    void testAddSource() {
        sources.add(genSource(new SourceImpl()));
        sources.add(genSource(new SourceImpl()));
        final Environment env = new EnvironmentImpl(sources.stream().collect(Collectors.toList()), listener);

        assertEquals(env.getAllSources().size(), sources.size());
        env.addSource(genSource(new SourceImpl()));
        assertEquals(env.getAllSources().size(), sources.size() + 1);
    }

    @Test
    void testGetX() {
        sources.add(genSource(new SourceImpl()));
        sources.add(genSource(new SourceImpl()));
        sources.add(genSource(new SourceImpl()));
        final Environment env = new EnvironmentImpl(sources.stream().collect(Collectors.toList()), listener);

        assertEquals(env.getXSource(1), sources.get(1));
        assertEquals(env.getXSource(2), sources.get(2));
        assertNotEquals(env.getXSource(1), sources.get(2));
    }

    @Test
    void testSpace() {
        final Environment env = new EnvironmentImpl(sources.stream().collect(Collectors.toList()), listener);

        assertEquals(env.getSpace().getLenght(), 10.0f);
        assertNotEquals(env.getSpace().getWidth(), 2.0f);
    }

    @Test
    void testListener() {
        final Environment env = new EnvironmentImpl(sources.stream().collect(Collectors.toList()), listener);

        assertEquals(env.getListener(), listener);
    }
}
