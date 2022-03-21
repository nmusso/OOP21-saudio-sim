package model.environment;

import model.filters.FilterEffects;

public interface Space {

     FilterEffects getFilterEffects();
     
     void setFilterEffects(FilterEffects filter);
     
     float getLenght();
     
     float getWidth();
     
}
