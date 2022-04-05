package model.buffer;

public interface Buffer {
    /**
     * Get the ID of the buffer.
     * @return the ID of the buffer
     */
    int getID();

    /**
     * Get the path of the file.
     * @return the path of the file from which the buffer was generated
     */
    String getFile();
}
