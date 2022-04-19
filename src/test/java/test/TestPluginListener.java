package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.audiomanager.AudioManager;
import model.listener.Listener;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;
import model.listener.plugin.PluginManager;
import model.source.FRSource;
import model.source.SourceFactory;
import model.source.SourceFactoryImpl;
import model.source.SourceType;
import model.source.hub.SourcesHub;
import model.source.hub.SourcesHubFactory;
import model.source.hub.SourcesHubFactoryImpl;
import model.utility.Vec3f;
import plugin.listener.model.DopplerPlugin;
import plugin.listener.model.SoundLevelMeterPlugin;

class TestPluginListener {
    private final ListenerFactory lsFactory = new ListenerFactoryImpl();

    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }

    @Test
    void testAddRemovePlugin() {
        final Listener listener = lsFactory.createListener(AudioManager.getContext());
        final PluginManager mng = new PluginManager();
        final DopplerPlugin doppler = new DopplerPlugin();
        final SoundLevelMeterPlugin soundL = new SoundLevelMeterPlugin(listener);

        mng.addPlugin(doppler);
        assertTrue(mng.getPlugins().contains(doppler));

        mng.removePlugin(doppler.getClassName());
        assertTrue(mng.getPlugins().isEmpty());

        mng.addPlugin(doppler);
        mng.addPlugin(soundL);
        assertEquals(mng.getPlugins().size(), 2);

    }

    @Test
    void testEnableDisablePlugin() {
        final Listener listener = lsFactory.createListener(AudioManager.getContext());
        final PluginManager mng = new PluginManager();
        final DopplerPlugin doppler = new DopplerPlugin();
        final SoundLevelMeterPlugin soundL = new SoundLevelMeterPlugin(listener);

        mng.addPlugin(doppler);
        mng.addPlugin(soundL);

        mng.disableAll();

        mng.getPlugins().forEach(p -> assertFalse(p.isEnabled()));
        doppler.enable();
        assertTrue(mng.getPlugins().stream().filter(p -> p.getClassName().equals(doppler.getClassName()))
                                            .findAny()
                                            .get()
                                            .isEnabled());
        assertFalse(mng.getPlugins().stream().filter(p -> p.getClassName().equals(soundL.getClassName()))
                                             .findAny()
                                             .get()
                                             .isEnabled());
    }

    @Test
    void testDopplerPlugin() {
        final DopplerPlugin plugin = new DopplerPlugin();
        final Vec3f velocity = new Vec3f(2.0f, 1.0f, 0.0f);

        assertTrue(plugin.isEnabled());
        plugin.setVelocity(velocity);
        assertEquals(plugin.getVelocity(), velocity);

        plugin.disable();
        assertEquals(plugin.getVelocity(), new Vec3f(0.0f));

        plugin.setVelocity(velocity);
        assertEquals(plugin.getVelocity(), new Vec3f(0.0f));

        plugin.enable();
        plugin.setDropplerLv(3.0f);
        assertEquals(plugin.getDropplerLv(), 3.0f);

        plugin.disable();
        assertNotEquals(plugin.getDropplerLv(), 3.0f);

        plugin.enable();
        assertEquals(plugin.getDropplerLv(), 3.0f);
    }

    @Test
    void testSoundLevelMeterPlugin() {
        final int maxBit = 255;
        final Listener listener = lsFactory.createListener(AudioManager.getContext());
        final SoundLevelMeterPlugin plugin = new SoundLevelMeterPlugin(listener);
        final SourceFactory sourceFac = new SourceFactoryImpl();
        final SourcesHubFactory sourcesFac = new SourcesHubFactoryImpl();
        final SourcesHub sources = sourcesFac.createSourcesHub();

        assertTrue(plugin.isEnabled());
        assertFalse(plugin.sourceHubPresent());
        assertEquals(plugin.getRgbColor(), new Vec3f(maxBit));

        listener.setPosition(new Vec3f(2.0f));
        final FRSource source = sourceFac.createFRSource(new Vec3f(2.0f), SourceType.FULL);
        sources.addSource(source);

        plugin.setSourceHub(sources);
        assertTrue(plugin.sourceHubPresent());
        assertEquals(plugin.getRgbColor(), new Vec3f(maxBit, 0.0f, 0.0f));

        plugin.disable();
        assertFalse(plugin.isEnabled());
        assertEquals(plugin.getRgbColor(), new Vec3f(maxBit));
    }

}













