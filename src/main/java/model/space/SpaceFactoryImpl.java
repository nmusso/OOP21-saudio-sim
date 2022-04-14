package model.space;

public class SpaceFactoryImpl  implements SpaceFactory {

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public SpaceImpl createDefaultSpace() {
        return new SpaceImpl(10f, 10f);
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public SpaceImpl createCustomizedSpace(final float lenght, final float width) {
        return new SpaceImpl(lenght, width);
    }

}
