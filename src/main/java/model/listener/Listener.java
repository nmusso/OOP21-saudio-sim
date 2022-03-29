package model.listener;

import model.audiomanager.Context;
import model.utility.Vec3f;

public interface Listener {


    /**
     * 
     * @return context where this listener is usable
     */
    Context getCurrentContext();

    /**
     * Set the position (x,y,z) of the listener.
     * @param pos vector of 3 float that representing x,y,z value.
     */
    void setPosition(Vec3f pos);

    /**
     * 
     * @return vector of 3 float that representing x,y,z value.
     */
    Vec3f getPosition();

    /**
     * 
     * @param at
     * @param up
     */
    void setOrientation(Vec3f at, Vec3f up);

    /**
     * 
     * @return at vector 
     */
    Vec3f getAtOrientation();

    /**
     * 
     * @return up vector
     */
    Vec3f getUpOrientation();


}
