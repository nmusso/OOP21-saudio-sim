package model.buffer;

public final class ALFormatException extends RuntimeException {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 1L;

    public ALFormatException(final String error) {
        super(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Error: the file is not a 8 or 16 bit mono/stereo audio file";
    }
}
