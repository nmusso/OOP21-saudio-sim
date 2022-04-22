package model.environment;

import java.io.FileReader;
import java.util.Optional;
import java.util.Set;

import org.json.JSONArray;
import org.json.*;
import org.json.JSONObject;

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

    private static final Vec3f CENTER = new Vec3f(2f, 2f, 0f);

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
        final FRSource source = sourceFac.createFRSource(new Vec3f(CENTER.getX(), CENTER.getY() - 1.5f, 0), SourceType.FULL);
        final SourcesHub sh = sourceHubFac.createSourcesHub();
        sh.addSource(source);
        final Space sp = spaceFac.createCustomizedSpace(5, 5);
        return new EnvironmentImpl(sh, listenerFac.createListenerWithPos(AudioManager.getContext(), CENTER), sp);
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createStereoEnvironment() {
        final SourcesHub sh = sourceHubFac.createSourcesHub();
        sh.addSource(sourceFac.createFRSource(new Vec3f(1f, 0.5f, 0f), SourceType.FULL));
        sh.addSource(sourceFac.createFRSource(new Vec3f(3f, 0.5f, 0f), SourceType.FULL));
        final Space sp = spaceFac.createCustomizedSpace(5, 5);
        return new EnvironmentImpl(sh, listenerFac.createListenerWithPos(AudioManager.getContext(), CENTER), sp);
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createCinemaEnvironment() {
        final SourcesHub sh = sourceHubFac.createSourcesHub();

        sh.addSource(sourceFac.createFRSource(new Vec3f(0, 15, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFRSource(new Vec3f(0, 10, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFRSource(new Vec3f(0, 5, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFRSource(new Vec3f(25, 15, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFRSource(new Vec3f(25, 10, 0), SourceType.FULL));
        sh.addSource(sourceFac.createFRSource(new Vec3f(25, 5, 0), SourceType.FULL));

        return new EnvironmentImpl(sh, listenerFac.createListenerWithPos(AudioManager.getContext(), new Vec3f(12.5f, 7.5f, 0f)), spaceFac.createCustomizedSpace(25f, 15f));
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createHIFIEnvironment() {
        final SourcesHub sh = sourceHubFac.createSourcesHub();

        sh.addSource(sourceFac.createFRSource(new Vec3f(1.1f, 0.3f, 0f), SourceType.MID));
        sh.addSource(sourceFac.createFRSource(new Vec3f(1.4f, 0f, 0f), SourceType.MID));

        sh.addSource(sourceFac.createFRSource(new Vec3f(2.0f, 0f, 0f), SourceType.HIGH));
        sh.addSource(sourceFac.createFRSource(new Vec3f(2.3f, 0f, 0f), SourceType.HIGH));
        sh.addSource(sourceFac.createFRSource(new Vec3f(2.6f, 0f, 0f), SourceType.HIGH));
        sh.addSource(sourceFac.createFRSource(new Vec3f(2.9f, 0f, 0f), SourceType.HIGH));

        sh.addSource(sourceFac.createFRSource(new Vec3f(3.5f, 0f, 0f), SourceType.MID));
        sh.addSource(sourceFac.createFRSource(new Vec3f(3.8f, 0.3f, 0f), SourceType.MID));

        sh.addSource(sourceFac.createFRSource(new Vec3f(0f, 1.5f, 0f), SourceType.HIGH));
        sh.addSource(sourceFac.createFRSource(new Vec3f(0f, 1.8f, 0f), SourceType.MID));
        sh.addSource(sourceFac.createFRSource(new Vec3f(0f, 2.1f, 0f), SourceType.LOW));

        sh.addSource(sourceFac.createFRSource(new Vec3f(5f, 1.5f, 0f), SourceType.HIGH));
        sh.addSource(sourceFac.createFRSource(new Vec3f(5f, 1.8f, 0f), SourceType.MID));
        sh.addSource(sourceFac.createFRSource(new Vec3f(5f, 2.1f, 0f), SourceType.LOW));

        sh.addSource(sourceFac.createFRSource(new Vec3f(0.1f, 3.5f, 0f), SourceType.HIGH));
        sh.addSource(sourceFac.createFRSource(new Vec3f(0.3f, 3.8f, 0f), SourceType.MID));
        sh.addSource(sourceFac.createFRSource(new Vec3f(0.6f, 4.4f, 0f), SourceType.LOW));
    
        sh.addSource(sourceFac.createFRSource(new Vec3f(4.5f, 3.5f, 0f), SourceType.HIGH));
        sh.addSource(sourceFac.createFRSource(new Vec3f(4.3f, 3.8f, 0f), SourceType.MID));
        sh.addSource(sourceFac.createFRSource(new Vec3f(4.0f, 4.4f, 0f), SourceType.LOW));

        sh.addSource(sourceFac.createFRSource(new Vec3f(2.0f, 5f, 0f), SourceType.LOW));
        sh.addSource(sourceFac.createFRSource(new Vec3f(2.3f, 5f, 0f), SourceType.LOW));
        sh.addSource(sourceFac.createFRSource(new Vec3f(2.6f, 5f, 0f), SourceType.LOW));
        sh.addSource(sourceFac.createFRSource(new Vec3f(2.9f, 5f, 0f), SourceType.LOW));
        final Space sp = spaceFac.createCustomizedSpace(5, 5);

        return new EnvironmentImpl(sh, listenerFac.createListenerWithPos(AudioManager.getContext(), new Vec3f(2.2f, 3.03f, 0f)), sp);
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createVoidEnvironment() {
        return new EnvironmentImpl(sourceHubFac.createSourcesHub(), listenerFac.createListenerWithPos(AudioManager.getContext(), new Vec3f(0f)), spaceFac.createDefaultSpace());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Environment createNEnvironment(final Set<FRSource> sources, final Listener listener, final Optional<Space> space) {
       return new EnvironmentImpl(sourceHubFac.createSourcesHub(sources), listener, space.isPresent() ? space.get() : spaceFac.createDefaultSpace());
    }

}
