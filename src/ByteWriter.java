import java.awt.Color;
import java.nio.ByteBuffer;

public class ByteWriter {
    /**
     * This method handles the writing of a color in the binary
     * @param buffer: Takes a buffer representing the size needed for saving the
     *              image given each color is saved individually.
     * @param runoff_color: It takes the color to be saved
     * @param color_repetition: It takes the times a color is repeated
     */
    public static void saveColorBytes(ByteBuffer buffer, Color runoff_color, int color_repetition){
        buffer.put((byte) color_repetition);
        buffer.put((byte) runoff_color.getRed());
        buffer.put((byte) runoff_color.getGreen());
        buffer.put((byte) runoff_color.getBlue());
    }
}
