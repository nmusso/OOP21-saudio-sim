package model.space;

public interface SpaceFactory {

    /**
     * 
     * @return space with default size.
     */
    SpaceImpl createDefaultSpace();
    /**
     * 
     * @param x
     * @param y
     * @return space with this size.
     */
    SpaceImpl createCustomizedSpace(float x, float y);
}
