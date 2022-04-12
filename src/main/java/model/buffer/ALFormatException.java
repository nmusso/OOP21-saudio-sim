package model.buffer;

/**
 * Exception for wrong format during import.
 *
 */
public final class ALFormatException extends RuntimeException {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 1L;
    private final String error;

    /**
     * Constructor for the exception.
     * 
     * @param error the message error
     */
    public ALFormatException(final String error) {
        super(error);
        this.error = error;
    }

    /**
     * Constructor for the exception.
     * 
     * @param error the message error
     * @param cause the exception
     */
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
