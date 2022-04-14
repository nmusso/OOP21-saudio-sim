package model.space;

import java.util.Set;

import model.utility.Vec3f;

public interface Space {

     /**
     *
     * @return max Y.
     */
     float getYmax();

     /**
     *
     * @return max X.
     */
     float getXmax();

     /**
      * check pos if is outOfBorder or busy.
      * @param pos
      * @param position
      * @return TTrue if is a valid pos.
      */
     boolean isAvailable(Vec3f pos, Set<Vec3f> position);
}
