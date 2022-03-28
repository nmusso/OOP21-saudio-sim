package model.environment;

import model.filters.FilterEffects;

public interface Space {

    /**
    *
    * @return filterEffect in the space
    */
     FilterEffects getFilterEffect();

     /**
     *
     * modify or ad filterEffects.
     *
     */
     void setFilterEffect(FilterEffects newfilter);

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
