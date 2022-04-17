package model.listener;

import model.audiomanager.Context;
import model.utility.Vec3f;

public interface ListenerFactory {

    /**
     * Create a Listener at position 0.0, 0.0, 0.0 in the context specified.
     * 
     * @param context where this listener is usable
     * @return listener 
     */
    Listener createListener(Context context);

    /**
     * Create a Listener with the position specified.
     * 
     * @param context where this listener is usable
     * @param position 
     * @return listener.
     */
    Listener createListener(Context context, Vec3f position);

    /**
     * Create a Listener with position and orientation specified.
     * 
     * @param context
     * @param position
     * @param at (TODO add Description)
     * @param up (TODO add Description)
     * @return listener.
     */
    Listener createListener(Context context, Vec3f position, Vec3f at, Vec3f up);


}
