package model.environment;

public class SpaceFactoryImpl  implements SpaceFactory {

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public SpaceImpl createDefaultSpace() {
        return new SpaceImpl(10f, 10f, 0.5f);
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public SpaceImpl createcustomizedSpace(final float lenght, final float width, final float scale) {
        return new SpaceImpl(lenght, width, scale);
    }

}
