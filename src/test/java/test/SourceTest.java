package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import model.buffer.Buffer;
import model.buffer.ResourceBuffer;
import model.source.FRSource;
import model.source.Source;
import model.source.SourceFactory;
import model.source.SourceFactoryImpl;
import model.source.SourceImpl;
import model.source.SourceType;
import model.source.hub.SourcesHub;
import model.source.hub.SourcesHubFactory;
import model.source.hub.SourcesHubFactoryImpl;
import model.utility.Vec3f;
import model.audiomanager.AudioManager;

class SourceTest {

    private final SourceFactory sFactory = new SourceFactoryImpl();
    private final SourcesHubFactory sHubFactory = new SourcesHubFactoryImpl();
    private final Source s = sFactory.createSource();
    private final FRSource frs = sFactory.createFRSource();

    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }

    private void genSource(final Source s) {
        Buffer b;
        try {
            b = new ResourceBuffer("/songs/InnoItalia.wav");
            s.generateSource(b.getID());
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testBasicPlayPauseStop() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        genSource(this.s);

        s.play();
        assertTrue(s.isPlaying());
        s.pause();
        assertFalse(s.isPlaying());
        s.play();
        s.stop();
        assertFalse(s.isPlaying());
    }

    @Test
    void testAdvancedPlayPauseStop() {
        genSource(this.s);

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
    void testBasicChangePosition() {
        genSource(this.s);
        final float x = 1.0f;
        final float y = -10.0f;
        final float z = 0.5f;

        final Source s = new SourceImpl(new Vec3f(0.0f));
        assertEquals(s.getPosition(), new Vec3f(0.0f));
        final Vec3f pos = new Vec3f(x, y, z);
        s.setPosition(pos);
        assertEquals(s.getPosition(), pos);
    }

    @Test
    void testBasicFRSource() {
        genSource(this.frs);

        assertEquals(frs.getType(), SourceType.FULL);
        frs.setType(SourceType.HIGH);
        assertEquals(frs.getType(), SourceType.HIGH);
        frs.setType(SourceType.MID);
        assertEquals(frs.getType(), SourceType.MID);
        frs.setType(SourceType.LOW);
        assertEquals(frs.getType(), SourceType.LOW);
    }

    @Test
    void testAdvancedFRSource() {
        genSource(this.frs);

        frs.setType(SourceType.HIGH);
        assertEquals(frs.getType(), SourceType.HIGH);
        frs.setType(SourceType.FULL);
        assertEquals(frs.getType(), SourceType.FULL);
        frs.setType(SourceType.LOW);
        assertEquals(frs.getType(), SourceType.LOW);
        frs.setType(SourceType.LOW);
        assertEquals(frs.getType(), SourceType.LOW);
        frs.setType(SourceType.FULL);
        assertEquals(frs.getType(), SourceType.FULL);
    }

    @Test
    void testSourcesHub() {
        final SourcesHub sHub = sHubFactory.createSourcesHub();
        final FRSource s1 = sFactory.createFRSource();
        final FRSource s2 = sFactory.createFRSource();
        final FRSource s3 = sFactory.createFRSource();
        final Vec3f origin = new Vec3f(0.0f); 
        final Vec3f fiveV = new Vec3f(5.0f);

        sHub.addSource(s1);
        sHub.addSource(s2);
        sHub.addSource(s3);

        assertEquals(sHub.getAll(), Set.of(s1, s2, s3));
        assertEquals(sHub.getAllPositions(), List.of(origin, origin, origin));

        sHub.playAll();
        assertEquals(sHub.getPlaying(), Set.of(s1, s2, s3));
        sHub.pauseAll();
        assertEquals(sHub.getPlaying(), Collections.EMPTY_SET);
        sHub.playAll();
        assertEquals(sHub.getPlaying(), Set.of(s1, s2, s3));
        sHub.stopAll();
        assertEquals(sHub.getPlaying(), Collections.EMPTY_SET);

        s3.setPosition(fiveV);
        assertEquals(sHub.getSourceFromPos(fiveV).get(), s3);
        assertEquals(sHub.getSource(s3.getId()).get(), s3);

        sHub.removeSource(s3);
        assertEquals(sHub.getSourceFromPos(fiveV).isPresent(), false);
        assertEquals(sHub.getSource(s3.getId()).isPresent(), false);

        sHub.deleteAll();
        assertEquals(sHub.getAll(), Collections.EMPTY_SET);
    }
}
