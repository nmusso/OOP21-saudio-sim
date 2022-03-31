package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

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


    private void checkListenerSpec(final Listener listener, final Optional<Vec3f> pos, final Optional<Vec3f> at, final Optional<Vec3f> up) {
        pos.ifPresent(val -> assertEquals(listener.getPosition(), val));
        at.ifPresent(val -> assertEquals(listener.getAtOrientation(), val));
        up.ifPresent(val -> assertEquals(listener.getUpOrientation(), val));
    }

    @Test
    void testDefaultPosition() {
        final Listener listener = lsFactory.createListener(AudioManager.getContext());
        checkListenerSpec(listener, Optional.of(new Vec3f(0.0f)), Optional.of(new Vec3f(0.0f, 0.0f, -1.0f)), Optional.of(new Vec3f(0.0f, 1.0f, 0.0f)));

        listener.setPosition(new Vec3f(2.0f));
        checkListenerSpec(listener, Optional.of(new Vec3f(2.0f)), Optional.empty(), Optional.empty());

    }

    @Test
    void testOrientation() {
        final Listener listener = lsFactory.createListener(AudioManager.getContext());

        /*TODO check real orientation value*/
        final Vec3f atLin = new Vec3f(1.0f, 0.0f, 0.0f);
        final Vec3f upLin = new Vec3f(0.0f, 0.0f, 1.0f);
        listener.setOrientation(atLin, upLin);

        checkListenerSpec(listener, Optional.empty(), Optional.of(atLin), Optional.of(upLin));


        /*at and up vector are not linearly independent*/
        final Vec3f atDip = new Vec3f(3.0f, 0.0f, 2.0f);
        final Vec3f upDip = new Vec3f(0.0f, 1.0f, 3.0f);
        listener.setOrientation(atDip, upDip);

        checkListenerSpec(listener, Optional.empty(), Optional.of(atLin), Optional.of(upLin));

    }

    @Test
    void testFactory() {
        /*Create listener in the same thread*/
        Listener listener = lsFactory.createListener(AudioManager.getContext());
        Listener listener2 = lsFactory.createListener(AudioManager.getContext());
        final Vec3f pos = new Vec3f(3.0f);

        assertEquals(listener, listener2);

        listener = lsFactory.createListenerWhitPos(AudioManager.getContext(), pos);
        checkListenerSpec(listener, Optional.of(pos), Optional.empty(), Optional.empty());

        final Vec3f at = new Vec3f(2.0f, 0.0f, 0.0f);
        final Vec3f up = new Vec3f(0.0f, 1.0f, 1.0f);
        listener2 = lsFactory.createListenerOriented(AudioManager.getContext(), pos, at, up);
        checkListenerSpec(listener2, Optional.of(pos), Optional.of(at), Optional.of(up));


    }

}








