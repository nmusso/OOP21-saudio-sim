package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.audiomanager.AudioManager;
import model.listener.Listener;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;
import model.listener.plugin.DropplerPlugin;
import model.listener.plugin.PluginManager;
import model.listener.plugin.PluginType;

class TestPluginListener {
    private final ListenerFactory lsFactory = new ListenerFactoryImpl();

    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }

    @Test
    void testManager() {
        final Listener lst = lsFactory.createListener(AudioManager.getContext());
        final PluginManager mng = new PluginManager(lst);
        final DropplerPlugin drp = new DropplerPlugin();

        mng.addPlugin(drp);

        assertEquals(lst, mng.getListener());
        assertNotEquals(mng.getPlugin(PluginType.DROPPLER_PLUGIN), Optional.empty());
        assertEquals(mng.getPlugin(PluginType.SOUNDMETER_PLUGIN), Optional.empty());
        assertEquals(mng.getPlugin(drp.getType()).get(), drp);

    }
}
