package model.buffer;

public final class ALFormatException extends RuntimeException {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 1L;
    private final String error;

    public ALFormatException(final String error) {
        super(error);
        this.error = error;
    }

    public ALFormatException(final String error, final Throwable cause) {
        super(error, cause);
        this.error = error;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return error + " The file is not a 8 or 16 bit mono audio file.";
    }
}
