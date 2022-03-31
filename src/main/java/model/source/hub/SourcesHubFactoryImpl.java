package model.source.hub;

import java.util.Set;

import model.source.Source;

public class SourcesHubFactoryImpl implements SourcesHubFactory {

    /**
     * @inheritDoc
     */
    @Override
    public SourcesHub createSourcesHub() {
        return new SourcesHubImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    public SourcesHub createSourceHubFromSet(final Set<Source> sources) {
        return new SourcesHubImpl(sources);
    }

}
