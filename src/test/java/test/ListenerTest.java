package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import model.audiomanager.AudioManager;
import model.listener.Listener;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;
import model.utility.Vec3f;

class ListenerTest {

    private final ListenerFactory lsFactory = new ListenerFactoryImpl();


    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }

    @Test
    void testDefaultPosition() {
        final Listener listener = lsFactory.createListener(AudioManager.getContext());

        assertEquals(listener.getPosition(), new Vec3f(0.0f));
        assertEquals(listener.getAtOrientation(), new Vec3f(0.0f, 0.0f, -1.0f));
        assertEquals(listener.getUpOrientation(), new Vec3f(0.0f, 1.0f, 0.0f));

        listener.setPosition(new Vec3f(2.0f));
        assertEquals(listener.getPosition(), new Vec3f(2.0f));

    }

    @Test
    void testOrientation() {
        final Listener listener = lsFactory.createListener(AudioManager.getContext());

        /*TODO check real orientation value*/
        final Vec3f atLin = new Vec3f(1.0f, 0.0f, 0.0f);
        final Vec3f upLin = new Vec3f(0.0f, 0.0f, 1.0f);
        listener.setOrientation(atLin, upLin);

        assertEquals(listener.getAtOrientation(), atLin);
        assertEquals(listener.getUpOrientation(), upLin);

        /*at and up vector are not linearly independent*/
        listener.setOrientation(new Vec3f(3.0f, 0.0f, 2.0f), new Vec3f(0.0f, 1.0f, 3.0f));
        assertEquals(listener.getAtOrientation(), atLin);
        assertEquals(listener.getUpOrientation(), upLin);
    }

    @Test
    void testFactory() {
        final Listener listener = lsFactory.createListener(AudioManager.getContext());
        final Listener listener2 = lsFactory.createListener(AudioManager.getContext());

        assertEquals(listener, listener2);

    }
}
