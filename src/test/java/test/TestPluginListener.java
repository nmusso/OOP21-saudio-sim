package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.audiomanager.AudioManager;
import model.listener.Listener;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;
import model.listener.plugin.DropplerPlugin;
import model.listener.plugin.ParameterType;
import model.listener.plugin.PluginManager;
import model.listener.plugin.PluginType;
import model.listener.plugin.Parameters;

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

    @Test
    void testParameter() {
        final Listener lst = lsFactory.createListener(AudioManager.getContext());
        final PluginManager mng = new PluginManager(lst);
        final DropplerPlugin drp = new DropplerPlugin();
        final Parameters drpParam = new Parameters(ParameterType.DROPPLER_LV, 3.0f);

        mng.addPlugin(drp);
        mng.getPlugin(PluginType.DROPPLER_PLUGIN).ifPresent(p -> p.setParameters(drpParam));

        assertEquals(drp.getFloatValue(ParameterType.DROPPLER_LV).get(), drpParam.getFloatValue(ParameterType.DROPPLER_LV).get());
        assertEquals(drp.getFloatValue(ParameterType.DB_LV), Optional.empty());
        assertEquals(drpParam.getFloatValue(ParameterType.DB_LV), Optional.empty());


        /*assertThrows(NoSuchElementException.class, () -> mng.getPlugin(PluginType.DROPPLER_PLUGIN)
                                                            .orElseThrow()
                                                            .setParameters(drpParam));*/

    }
}














