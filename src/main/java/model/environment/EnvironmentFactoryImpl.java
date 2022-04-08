package model.environment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.listener.Listener;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;
import model.source.FreqRangeSource;
import model.source.Source;
import model.source.SourceFactory;
import model.source.SourceFactoryImpl;
import model.source.hub.SourcesHub;
import model.source.hub.SourcesHubFactory;
import model.source.hub.SourcesHubFactoryImpl;
import model.space.Space;
import model.space.SpaceFactory;
import model.space.SpaceFactoryImpl;
import model.utility.Vec3f;

public class EnvironmentFactoryImpl implements EnvironmentFactory {

    private final SourcesHubFactory sourceHubFac = new SourcesHubFactoryImpl();
    private final SpaceFactory spaceFac = new SpaceFactoryImpl();
    //private final SourceFactory sourceFac = new SourceFactoryImpl();
    //private final ListenerFactory listenerFac = new ListenerFactoryImpl();

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createMonoEnvironment(final FreqRangeSource mono, final Listener listener, final Optional<Space> space) {
        return new EnvironmentImpl(sourceHubFac.createSourceHubFromSet(Collections.singletonList(mono).stream().collect(Collectors.toSet())), listener, space.isPresent() ? space.get() : spaceFac.createDefaultSpace());
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createStereoEnvironment(final FreqRangeSource left, final FreqRangeSource right, final Listener listener, final Optional<Space> space) {
        final Set<FreqRangeSource> sources = new HashSet<>();
        sources.add(left);
        sources.add(right);
        return new EnvironmentImpl(sourceHubFac.createSourceHubFromSet(sources), listener, space.isPresent() ? space.get() : spaceFac.createDefaultSpace());
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createNEnvironment(final Set<FreqRangeSource> sources, final Listener listener, final Optional<Space> space) {
        return new EnvironmentImpl(sourceHubFac.createSourceHubFromSet(sources), listener, space.isPresent() ? space.get() : spaceFac.createDefaultSpace());
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createCinemaEnvironment() {
        // TODO
//        final SourcesHub sh = sourceHubFac.createSourcesHub();        
//        sh.addSource(sourceFac.createSourceWithPos(new Vec3f(0, 15, 0)));
//        sh.addSource(sourceFac.createSourceWithPos(new Vec3f(0, 10, 0)));
//        sh.addSource(sourceFac.createSourceWithPos(new Vec3f(0, 5, 0)));
//        sh.addSource(sourceFac.createSourceWithPos(new Vec3f(25, 15, 0)));
//        sh.addSource(sourceFac.createSourceWithPos(new Vec3f(25, 10, 0)));
//        sh.addSource(sourceFac.createSourceWithPos(new Vec3f(25, 5, 0)));
//
//        return new EnvironmentImpl(sh, listenerFac.createListener(AudioManager.getContext()), spaceFac.createcustomizedSpace(25f, 15f, 1f));
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
