package model.environment;

import java.util.Optional;
import java.util.Set;

import model.audiomanager.AudioManager;
import model.listener.Listener;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;
import model.source.FRSource;
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
        final FRSource source = sourceFac.createFRSourceWithPos(new Vec3f(1.5f, 1.5f, 0), SourceType.FULL);
        final SourcesHub sh = sourceHubFac.createSourcesHub();
        sh.addSource(source);
        final Space sp = spaceFac.createCustomizedSpace(5, 5);
        return new EnvironmentImpl(sh, listenerFac.createListenerWhitPos(AudioManager.getContext(), new Vec3f(1.5f, 3f, 0f)), sp);
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createStereoEnvironment() {
        final SourcesHub sh = sourceHubFac.createSourcesHub();
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(1f, 0.5f, 0f), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(3f, 0.5f, 0f), SourceType.FULL));
        final Space sp = spaceFac.createCustomizedSpace(5, 5);
        return new EnvironmentImpl(sh, listenerFac.createListenerWhitPos(AudioManager.getContext(), new Vec3f(2f, 2f, 0f)), sp);
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createCinemaEnvironment() {
        final SourcesHub sh = sourceHubFac.createSourcesHub();

        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(0, 15, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(0, 10, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(0, 5, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(25, 15, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(25, 10, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(25, 5, 0), SourceType.FULL));

        return new EnvironmentImpl(sh, listenerFac.createListenerWhitPos(AudioManager.getContext(), new Vec3f(12.5f, 7.5f, 0f)), spaceFac.createCustomizedSpace(25f, 15f));
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createHIFIEnvironment() {
        final SourcesHub sh = sourceHubFac.createSourcesHub();

        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(1.1f, 0f, 0f), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(2.3f, 0f, 0f), SourceType.LOW));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(3.5f, 0f, 0f), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(0f, 1.8f, 0f), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(5f, 1.8f, 0f), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(0.3f, 3.8f, 0f), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(4.3f, 3.8f, 0f), SourceType.FULL));
        sh.addSource(sourceFac.createFRSourceWithPos(new Vec3f(2.3f, 5f, 0f), SourceType.FULL));
        final Space sp = spaceFac.createCustomizedSpace(5, 5);

        return new EnvironmentImpl(sh, listenerFac.createListenerWhitPos(AudioManager.getContext(), new Vec3f(2.2f, 3.03f, 0f)), sp);
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createVoidEnvironment() {
        return new EnvironmentImpl(sourceHubFac.createSourcesHub(), listenerFac.createListenerWhitPos(AudioManager.getContext(), new Vec3f(0f)), spaceFac.createDefaultSpace());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Environment createNEnvironment(final Set<FRSource> sources, final Listener listener, final Optional<Space> space) {
       return new EnvironmentImpl(sourceHubFac.createSourceHubFromSet(sources), listener, space.isPresent() ? space.get() : spaceFac.createDefaultSpace());
    }

}
