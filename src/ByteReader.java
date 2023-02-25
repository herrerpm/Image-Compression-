import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteReader {
    /**
     * This method handles the reading of an integer in a binary file
     *
     * @param buffer: A parameter with a bytebuffer of at least 4 bytes
     * @param input_stream: An input stream containing the file to read
     * @return The integer represented by the 4 bytes read
     * @throws IOException
     */
    public static int readInt(ByteBuffer buffer, FileInputStream input_stream) throws IOException {
        // Retrieve 4 bytes, the size of an int
        for (int i = 0; i < 4; i++) {
            buffer.put((byte) input_stream.read());
        }
        buffer.rewind();
        int result = (buffer.getInt());
        buffer.clear();
        return result;
    }

    /**
     * This method handles the reading of a byte in a binary file, and
     * returns the unsigned representation of said number.
     *
     * @param buffer: A ByteBuffer of at least 1 byte
     * @param input_stream: An input stream with the binary to read
     * @return An unsigned integer representing the byte read
     * @throws IOException
     */
    public static int readSignedByte(ByteBuffer buffer, FileInputStream input_stream) throws IOException {
        buffer.put((byte) input_stream.read());
        buffer.rewind();
        int result = (Byte.toUnsignedInt(buffer.get()));
        buffer.clear();
        return result;
    }
}
