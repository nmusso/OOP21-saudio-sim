package model.source;

import java.util.Set;

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
