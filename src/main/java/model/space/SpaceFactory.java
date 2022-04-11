package model.space;

public interface SpaceFactory {

    /**
    *
    * TODO.
    */
    SpaceImpl createDefaultSpace();
    /**
    *
    * TODO.
    */
    SpaceImpl createCustomizedSpace(float lenght, float width, float scale);
}
