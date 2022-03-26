package model.buffer;

public interface BufferFactory {
    /**
     * 
     * @param file The path of the file which will be associated to the buffer
     * @return A new instance of Buffer
     */
    Buffer createBuffer(String file);
}
