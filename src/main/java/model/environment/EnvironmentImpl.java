package model.environment;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import model.listener.SimpleListener;
import model.source.Source;

public class EnvironmentImpl implements Environment {
    private List<Source> sources;
    private SimpleListener listener;
    private Space space;

    /**
    *
    * 
    *
    */
    public EnvironmentImpl(final List<Source> sources, final SimpleListener listener, final Space space) {
        super();
        this.sources = sources;
        this.listener = listener;
        this.space = space;
    }
    /**
    *
    * 
    *
    */
    public EnvironmentImpl(final List<Source> sources, final SimpleListener listener) {
        super();
        this.sources = sources;
        this.listener = listener;
        this.space = new SpaceImpl(10, 10);
    }

    /**
    *
    * 
    *
    */
    @Override
    public List<Source> getAllSources() {
        return Collections.unmodifiableList(this.sources);
    }
    /**
    *
    * 
    *
    */
    @Override
    public Source getXSource(final int x) {
        return this.sources.get(x);
    }
    /**
    *
    * 
    *
    */
    @Override
    public List<Source> getPlayingSources() {
        return this.sources.stream().filter(s -> s.isPlaying()).collect(Collectors.toList());
    }
    /**
    *
    * 
    *
    */
    @Override
    public SimpleListener getListener() {
        return this.listener;
    }
    /**
    *
    * 
    *
    */
    @Override
    public Space getSpace() {
       return this.space;
    }
}
