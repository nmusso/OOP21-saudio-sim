package model.environment;

import model.filters.FilterEffects;

public interface Space {

     FilterEffects getFilterEffects();
     
     void setFilterEffects(FilterEffects newfilter);
     
     float getLenght();
     
     float getWidth();
     
}
