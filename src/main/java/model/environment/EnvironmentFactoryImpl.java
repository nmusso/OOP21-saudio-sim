package model.environment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.audiomanager.AudioManager;
import model.listener.Listener;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;
import model.source.FRSource;
import model.source.Source;
import model.source.SourceFactory;
import model.source.SourceFactoryImpl;
import model.source.SourceType;
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
    private final SourceFactory sourceFac = new SourceFactoryImpl();
    private final ListenerFactory listenerFac = new ListenerFactoryImpl();

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createMonoEnvironment() {
        final FRSource source = sourceFac.createFreqRangeSource();
        final SourcesHub sh = sourceHubFac.createSourceHubFromSet(Collections.singleton(source));
        final Space sp = spaceFac.createCustomizedSpace(5, 5);
        return new EnvironmentImpl(sh, listenerFac.createListener(AudioManager.getContext()), sp);
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createStereoEnvironment() {
        final SourcesHub sh = sourceHubFac.createSourcesHub();
        sh.addSource(sourceFac.createFreqRangeSourceWithPos(new Vec3f(2f, 4f, 0f), SourceType.FULL));
        sh.addSource(sourceFac.createFreqRangeSourceWithPos(new Vec3f(4f, 4f, 0f), SourceType.FULL));
        final Space sp = spaceFac.createCustomizedSpace(5, 5);
        return new EnvironmentImpl(sh, listenerFac.createListenerWhitPos(AudioManager.getContext(), new Vec3f(3f, 4f, 0f)), sp);
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createCinemaEnvironment() {
        final SourcesHub sh = sourceHubFac.createSourcesHub();

        sh.addSource(sourceFac.createFreqRangeSourceWithPos(new Vec3f(0, 15, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFreqRangeSourceWithPos(new Vec3f(0, 10, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFreqRangeSourceWithPos(new Vec3f(0, 5, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFreqRangeSourceWithPos(new Vec3f(25, 15, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFreqRangeSourceWithPos(new Vec3f(25, 10, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFreqRangeSourceWithPos(new Vec3f(25, 5, 0), SourceType.FULL));

        //TODO utilizzare space per aggiornare i limiti dei preset.
        return new EnvironmentImpl(sh, listenerFac.createListener(AudioManager.getContext()), spaceFac.createCustomizedSpace(25f, 15f));
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

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createVoidEnvironment() {
        return new EnvironmentImpl(sourceHubFac.createSourcesHub(), listenerFac.createListener(AudioManager.getContext()), spaceFac.createDefaultSpace());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Environment createNEnvironment(final Set<FRSource> sources, final Listener listener, final Optional<Space> space) {
       return new EnvironmentImpl(sourceHubFac.createSourceHubFromSet(sources), listener, space.get());
    }

}
