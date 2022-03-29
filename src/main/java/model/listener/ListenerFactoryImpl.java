package model.listener;

import java.util.HashMap;
import java.util.Map;

import model.AudioManager.Context;
import model.utility.Vec3f;

public class ListenerFactoryImpl implements ListenerFactory {
    private final Map<Context, Listener> listeners = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    public Listener createListener(final Context context) {
        this.listeners.putIfAbsent(context, new ListenerImpl(context));
        return this.listeners.get(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener createListenerWhitPos(final Context context, final Vec3f position) {
        this.listeners.putIfAbsent(context, new ListenerImpl(context, position));
        return this.listeners.get(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener createListenerOriented(final Context context, final  Vec3f position, final  Vec3f at, final  Vec3f up) {
        this.listeners.putIfAbsent(context, new ListenerImpl(context));
        return this.listeners.get(context);
    }


}
