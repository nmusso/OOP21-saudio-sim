package model.environment;

import model.extension.Extension;

public interface Space {

    /**
    *
    * @return filterEffect in the space
    */
     Extension getFilterEffect();

     /**
     *
     * modify or ad filterEffects.
     *
     */
     void setFilterEffect(Extension newfilter);

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
}
