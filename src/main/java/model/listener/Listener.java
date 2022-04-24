package model.listener;

import model.audiomanager.Context;
import model.utility.Vec3f;

/**
 * The listener represent the listening point in the virtual plan.
 * 
 */
public interface Listener {
    /**
     * @return context where this listener is usable.
     */
    Context getCurrentContext();

    /**
     * Set the position (x,y,z) of the listener.
     * @param pos vector of 3 float that representing x,y,z value.
     */
    void setPosition(Vec3f pos);

    /**
     * @return vector of 3 float that representing x,y,z value.
     */
    Vec3f getPosition();

    /**
     * Set the orientation of listener in 3d plan.
     * @param at vector represents the right or left rotation. 
     * @param up vector represents tilt up or down.
     */
    void setOrientation(Vec3f up, Vec3f at);

    /**
     * Set the orientation of listener in 2d plan.
     * @param at vector represents the right or left rotation.
     */
    void setAtOrientation(Vec3f at);

    /**
     * @return at vector
     */
    Vec3f getAtOrientation();

    /**
     * @return up vector
     */
    Vec3f getUpOrientation();


}
