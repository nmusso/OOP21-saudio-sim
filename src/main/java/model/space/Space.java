package model.space;

import java.util.List;

import model.utility.Vec3f;

public interface Space {

     /**
     * Get Height.
     * @return max Y.
     */
     float getYmax();

     /**
      * Set Height.
      * @param y height
      */
     void setYMax(float y);

     /**
     * Get Width.
     * @return max X.
     */
     float getXmax();

     /**
      * Set Width.
      * @param x width
      */
     void setXMax(float x);

     /**
      * check pos if is outOfBorder or busy (CONSOLE).
      * @param pos possible position to occupy
      * @param position busy
      * @return True if the pos is correct..
      */
     boolean isAvailable(Vec3f pos, List<Vec3f> position);
}
