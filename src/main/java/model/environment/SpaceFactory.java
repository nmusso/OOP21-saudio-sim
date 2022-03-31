package model.environment;

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
    SpaceImpl createcustomizedSpace(float lenght, float width, float scale);
}
