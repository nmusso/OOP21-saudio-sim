package model.listener;

import java.util.HashMap;
import java.util.Map;

import model.audiomanager.Context;
import model.utility.Vec3f;

/**
 * 
 * Implementation of ListenerFactory where the listener is kept unique in an execution context. 
 *
 */
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
    public Listener createListenerWithPos(final Context context, final Vec3f position) {
        if (this.listeners.containsKey(context)) {
            this.listeners.get(context).setPosition(position);
        }
        this.listeners.putIfAbsent(context, new ListenerImpl(context, position));
        return this.listeners.get(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener createListenerOriented(final Context context, final  Vec3f position, final  Vec3f up, final  Vec3f at) {
        if (this.listeners.containsKey(context)) {
            this.listeners.get(context).setPosition(position);
            this.listeners.get(context).setOrientation(up, at);
        }
        this.listeners.putIfAbsent(context, new ListenerImpl(context));
        return this.listeners.get(context);
    }


}
