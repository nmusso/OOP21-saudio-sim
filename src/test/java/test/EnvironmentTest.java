package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.json.JSONArray;
import org.json.JSONObject;
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

        assertEquals(env.getSourceHub().getSource(1), Optional.of(source1));
        assertEquals(env.getSourceHub().getSource(2), Optional.of(source2));
        assertNotEquals(env.getSourceHub().getSource(1), Optional.of(source2));
    }

    @Test
    void testListener() {
        final Environment env = envFac.createNEnvironment(sources.stream().collect(Collectors.toSet()), listener, Optional.empty());

        assertEquals(env.getListener(), listener);
        
        

    }
    
    @Test
    void testesempio() {
        System.out.println("test Json");
        final JSONObject obj = new JSONObject(getClass().getResource("/json/data.json"));
        assertNotNull(obj);
        final JSONArray pageName = obj.getJSONArray("Cinema");
        System.out.println(pageName.get(1));

    }
}
