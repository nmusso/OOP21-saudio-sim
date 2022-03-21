package model.listener;

import model.utility.Vec3f;

public interface SimpleListener {
    
    /**
     * Set the position (x,y,z) of the listener.
     * @param pos vector of 3 float that representing x,y,z value.
     */
    void setPosition(final Vec3f pos);
    
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
    void setOrientation(final Vec3f at, final Vec3f up);
    
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
