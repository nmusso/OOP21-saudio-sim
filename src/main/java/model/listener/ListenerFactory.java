package model.listener;

import model.audiomanager.Context;
import model.utility.Vec3f;

/**
 * This Factory helps to create listener with specified parameters
 * and keeps unique the listener in an execution context. 
 */
public interface ListenerFactory {

    /**
     * Create a Listener at position 0.0, 0.0, 0.0 in the context specified.
     * 
     * @param context where this listener is usable
     * @return new listener or if already present the instance of that context. 
     */
    Listener createListener(Context context);

    /**
     * Create a Listener with the position specified.
     * 
     * @param context where this listener is usable
     * @param position 
     * @return new listener or if already present the instance of that context.
     */
    Listener createListener(Context context, Vec3f position);

    /**
     * Create a Listener with position and orientation specified.
     * 
     * @param context  where this listener is usable
     * @param position of listener
     * @param at vector of listener
     * @param up vector of listener
     * @return new listener or if already present the instance of that context.
     */
    Listener createListener(Context context, Vec3f position, Vec3f up, Vec3f at);


}
