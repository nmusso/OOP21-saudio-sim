package model.space;

public interface SpaceFactory {

    /**
     * Create a space 10X10.
     * @return space with default size.
     */
    SpaceImpl createDefaultSpace();
    /**
     * Create Space XxY.
     * @param x
     * @param y
     * @return space with this size.
     */
    SpaceImpl createCustomizedSpace(float x, float y);
}
