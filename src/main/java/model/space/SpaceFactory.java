package model.space;

/**
 * Interface for SpaceFactory.
 *
 */
public interface SpaceFactory {

    /**
     * Create a space 10X10.
     * @return space with default size.
     */
    SpaceImpl createDefaultSpace();
    /**
     * Create Space XxY.
     * @param x width
     * @param y height
     * @return space with this size.
     */
    SpaceImpl createCustomizedSpace(float x, float y);
}
