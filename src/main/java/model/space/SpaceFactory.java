package model.space;

public interface SpaceFactory {

    /**
     * 
     * @return TODO
     */
    SpaceImpl createDefaultSpace();
    /**
     * 
     * @param lenght
     * @param width
     * @return TODO
     */
    SpaceImpl createCustomizedSpace(float lenght, float width);
}
