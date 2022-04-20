package model.source.hub;

import java.util.Set;

import model.source.FRSource;

/**
 * Factory of SourcesHub.
 * 
 */
public class SourcesHubFactoryImpl implements SourcesHubFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public SourcesHub createSourcesHub() {
        return new SourcesHubImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourcesHub createSourcesHub(final Set<FRSource> sources) {
        return new SourcesHubImpl(sources);
    }

}
