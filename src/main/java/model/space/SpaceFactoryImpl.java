package model.space;

/**
 * Implementation of SpaceFactory.
 *
 */
public class SpaceFactoryImpl  implements SpaceFactory {

    private static final float DIM = 10f; 

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public SpaceImpl createDefaultSpace() {
        return new SpaceImpl(DIM, DIM);
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public SpaceImpl createCustomizedSpace(final float x, final float y) {
        return new SpaceImpl(x, y);
    }

}
