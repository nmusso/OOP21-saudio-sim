package model.environment;

import java.util.Collections;
import java.util.List;

import model.listener.SimpleListener;
import model.source.Source;

public class EnvironmentImpl implements Environment {
    private int id;
    private List<Source> sources;
    private SimpleListener listener;
    private Space space;

    /**
    *
    * @inheritDoc
    *
    */
    public EnvironmentImpl(final int id, final List<Source> sources, final SimpleListener listener, final Space space) {
        super();
        this.id = id;
        this.sources = sources;
        this.listener = listener;
        this.space = space;
    }
    /**
    *
    * @inheritDoc
    *
    */
    @Override
    public int getIdEnvironment() {
        return this.id;
    }
    /**
    *
    * @inheritDoc
    *
    */
    @Override
    public List<Source> getAllSources() {
        return Collections.unmodifiableList(this.sources);
    }
    /**
    *
    * @inheritDoc
    *
    */
    @Override
    public Source getXSource(final int x) {
        return this.sources.get(x);
    }
    /**
    *
    * @inheritDoc
    *
    */
    @Override
    public List<Source> getPlayingSources() {
        return null; //this.sources.stream().filter(s -> s.isPlaying())
    }
    /**
    *
    * @inheritDoc
    *
    */
    @Override
    public SimpleListener getListener() {
        return this.listener;
    }
}
