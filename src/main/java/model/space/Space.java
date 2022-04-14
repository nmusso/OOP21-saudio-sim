package model.space;

import java.util.Set;

import model.utility.Vec3f;

public interface Space {

     /**
     *
     * @return max lenght.
     */
     float getLenght();

     /**
     *
     * @return max width.
     */
     float getWidth();

     /**
      * 
      * @param pos
      * @param position
      * @return TODO
      */
     boolean isAvailable(Vec3f pos, Set<Vec3f> position);

}
