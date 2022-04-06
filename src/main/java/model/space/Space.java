package model.space;

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
     * TODO .
     * if yes -> add the position to log
     */
     boolean isAvailable(Vec3f pos);

     /**
     * remove source.
     * @param pos to delete
     */
     void removeSourcePos(Vec3f pos);
}
