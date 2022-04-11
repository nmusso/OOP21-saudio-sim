package test;

import org.junit.jupiter.api.BeforeAll;
import model.audiomanager.AudioManager;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;

class TestPluginListener {
    private final ListenerFactory lsFactory = new ListenerFactoryImpl();

    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }


}













