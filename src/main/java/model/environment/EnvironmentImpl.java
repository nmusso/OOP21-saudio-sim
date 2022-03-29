package model.environment;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.listener.Listener;
import model.listener.ListenerImpl;
import model.source.Source;

public class EnvironmentImpl implements Environment {
    private final List<Source> sources;
    private final Listener listener;
    private final Space space;

    public EnvironmentImpl(final List<Source> sources, final Listener listener, final Space space) {
        super();
        this.sources = sources;
        this.listener = listener;
        this.space = space;
    }

    public EnvironmentImpl(final List<Source> sources, final Listener listener) {
        super();
        this.sources = sources;
        this.listener = listener;
        this.space = new SpaceImpl(10, 10);
    }

    public EnvironmentImpl() {
        super();
        this.sources = new LinkedList<Source>();
        this.listener = new ListenerImpl();
        this.space = new SpaceImpl(10, 10);
    }

    /**
    * 
    *{@inheritDoc}
    */
    @Override
    public List<Source> getAllSources() {
        return Collections.unmodifiableList(this.sources);
    }

    /**
    * 
    *{@inheritDoc}
    */
    @Override
    public Source getXSource(final int x) {
        return this.sources.get(x);
    }

    /**
    * 
    *{@inheritDoc}
    */
    @Override
    public List<Source> getPlayingSources() {
        return this.sources.stream().filter(s -> s.isPlaying()).collect(Collectors.toList());
    }

    /**
    * 
    *{@inheritDoc}
    */
    @Override
    public Listener getListener() {
        return this.listener;
    }

    /**
    * 
    *{@inheritDoc}
    */
    @Override
    public Space getSpace() {
       return this.space;
    }

    /**
    * 
    *{@inheritDoc}
    */
    @Override
    public void playAllSources() {
        this.sources.forEach(s -> s.play());
    }

    /**
    * 
    *{@inheritDoc}
    */
    @Override
    public void stopAllSources() {
        this.sources.forEach(s -> s.stop());
    }

    /**
    * 
    *{@inheritDoc}
    */
    @Override
    public void addSource(final Source s) {
        this.sources.add(s);
    }
}
