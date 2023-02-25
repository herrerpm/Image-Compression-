import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;



public class Compressor {
    /**
     * The compress function takes each color in the image and calculates its similarity
     * using the ColorHandler.colorSimilarity method. The colors are read from each row, and
     * then the number of times a color is repeated is encoded as the first byte belonging
     * to the color.
     *
     * The next three bytes encode the information for the Red, Green and Blue components of
     * the color. It is worth noting that each color chain has a maximum size of 255, as to
     * describe the runoff using only one byte. This means that if a color is repeated
     * 500 times it would be saved as two 255 chains.
     *
     * Additionally, the first two bytes of the file represent the values for the height,
     * width.
     *
     * @param image
     * @throws IOException
     */
    public static void compress(Color[][] image, String filename) throws IOException {
        // Get the image dimensions
        int height = image[0].length;
        int width = image.length;
        // Set the used bytes to 8, as width and height are integers
        int used_bytes = 8;
        int color_repetition = 0;
        Color previous_color = image[0][0];
        Color current_color;
        Color repeated_color = previous_color;
        // Set the output stream and the byte buffer the size of the image
        FileOutputStream outputStream = new FileOutputStream(filename);
        ByteBuffer byte_buffer = ByteBuffer.allocate((4*width*height)+8);
        byte_buffer.putInt(height);
        byte_buffer.putInt(width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                current_color = image[x][y];
                if (ColorHandler.ColorSimilarity(previous_color,current_color)& color_repetition < 255) {
                    color_repetition++;
                }
                else {
                    if (color_repetition > 0) {
                        // Save the colors when the repeated color chains, while the repetition is > 0
                        ByteWriter.saveColorBytes(byte_buffer, repeated_color, color_repetition);
                        used_bytes+=4;
                    }
                    // Reset the color repetition
                    color_repetition = 1;
                    repeated_color = current_color;
                }
                previous_color = current_color;
            }
        }
        // Because of the for, the last condition is not saved, so I save the last color repetition
        ByteWriter.saveColorBytes(byte_buffer, repeated_color, color_repetition);
        used_bytes+=4;
        byte[] result = byte_buffer.array();
        outputStream.write(result,0,used_bytes);
        outputStream.close();
    }
}
