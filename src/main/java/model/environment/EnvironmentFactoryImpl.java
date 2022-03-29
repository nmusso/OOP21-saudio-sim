package model.environment;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.listener.Listener;
import model.source.Source;

public class EnvironmentFactoryImpl implements EnvironmentFactory {

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public Environment createMonoEnvironment(final Source mono, final Listener listener, final Optional<Space> space) {
        return new EnvironmentImpl(Collections.singletonList(mono), listener, space.get());
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public Environment createStereoEnvironment(final Source left, final Source right, final Listener listener, final Optional<Space> space) {
        final List<Source> sources = new LinkedList<Source>();
        sources.add(left);
        sources.add(right);
        return new EnvironmentImpl(sources, listener, space.get());
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public Environment createNEnvironment(final List<Source> sources, final Listener listener, final Optional<Space> space) {
        return new EnvironmentImpl(sources, listener, space.get());
    }
}
