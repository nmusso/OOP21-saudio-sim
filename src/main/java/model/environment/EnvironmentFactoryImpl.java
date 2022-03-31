package model.environment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.listener.Listener;
import model.source.Source;
import model.source.hub.SourcesHubFactory;
import model.source.hub.SourcesHubFactoryImpl;

public class EnvironmentFactoryImpl implements EnvironmentFactory {

    private final SourcesHubFactory sourceHubFac = new SourcesHubFactoryImpl();
    private SpaceFactory spaceFac = new SpaceFactoryImpl();

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createMonoEnvironment(final Source mono, final Listener listener, final Optional<Space> space) {
        return new EnvironmentImpl(sourceHubFac.createSourceHubFromSet(Collections.singletonList(mono).stream().collect(Collectors.toSet())), listener, space.isPresent() ? space.get() : spaceFac.createDefaultSpace());
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createStereoEnvironment(final Source left, final Source right, final Listener listener, final Optional<Space> space) {
        final Set<Source> sources = new HashSet<>();
        sources.add(left);
        sources.add(right);
        return new EnvironmentImpl(sourceHubFac.createSourceHubFromSet(sources), listener, space.isPresent() ? space.get() : spaceFac.createDefaultSpace());
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createNEnvironment(final Set<Source> sources, final Listener listener, final Optional<Space> space) {
        return new EnvironmentImpl(sourceHubFac.createSourceHubFromSet(sources), listener, space.isPresent() ? space.get() : spaceFac.createDefaultSpace());
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createCinemaEnvironment() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createConcertEnvironment() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createStadiumEnvironment() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createHIFIEnvironment() {
        // TODO Auto-generated method stub
        return null;
    }

}
