package model.environment;

import java.util.List;

import model.listener.Listener;
import model.source.Source;

public class EnvironmentFactoryImpl implements EnvironmentFactory {

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public Environment createMonoEnvironment(final Source mono, final Listener listener, final Space space) {
     // TODO create a monoEnvironmentImpl?????
        return null;
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public Environment createStereoEnvironment(final Source left, final Source right, final Listener listener, final Space space) {
     // TODO create a steroEnvironmentImpl?????
        return null;
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public Environment createNEnvironment(final List<Source> sources, final Listener listener, final Space space) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public Environment createEnvironmentDefaultSpace(final List<Source> sources, final Listener listener) {
        // TODO Auto-generated method stub
        return null;
    }

}
