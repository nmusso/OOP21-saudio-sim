package model.buffer;

public class BufferFactoryImpl implements BufferFactory {

    private final BufferCache bc = BufferCache.getInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    public Buffer createBuffer(final String file) {
        return bc.contains(file) ? bc.getBuffer(file) : new BufferImpl(file);
    }
}
