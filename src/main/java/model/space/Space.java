package model.space;

import java.util.Set;

import model.utility.Vec3f;

public interface Space {

     /**
     * Get Height.
     * @return max Y.
     */
     float getYmax();

     /**
      * Set Height.
      * @param y
      */
     void setYMax(float y);

     /**
     * Get Width.
     * @return max X.
     */
     float getXmax();

     /**
      * Set Width.
      * @param x
      */
     void setXMax(float x);

     /**
      * check pos if is outOfBorder or busy (CONSOLE).
      * @param pos
      * @param position
      * @return True if the pos is correct..
      */
     boolean isAvailable(Vec3f pos, Set<Vec3f> position);
}
