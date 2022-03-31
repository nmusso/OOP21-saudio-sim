package model.environment;

import model.utility.Vec3f;

public interface Space {

     /**
     *
     * @return scale.
     */
     float getScale();
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
     * TODO.
     */
     boolean isAvailable(Vec3f pos);
}
