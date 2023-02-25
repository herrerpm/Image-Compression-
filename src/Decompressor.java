import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Decompressor {
    /**
     * The decompression algorithm interprets a .compressed binary and returns a .bmp image of the
     * compressed file. The decompressor works by taking the first 8 bytes to interpret the height
     * and width of the image. All the bytes remaining are grouped every 4 bytes where they
     * represent the number of times a color is repeated, the red, green and blue values of said
     * color.
     *
     * @param compressed_file: A string representing the path to the compressed file
     * @param decompressed_file: A string representing the name of the decompressed file
     * @throws IOException
     */
    public static void decompress(String compressed_file, String decompressed_file) throws IOException {
        FileInputStream inputStream = new FileInputStream(compressed_file);
        // Allocate the byte buffer to read the max data type (Int)
        ByteBuffer byte_buffer = ByteBuffer.allocate(4);
        // Set the bytes to read as the file bytes - the width and height
        int bytes_to_read = inputStream.available() - 8;
        int height = ByteReader.readInt(byte_buffer,inputStream);
        int width = ByteReader.readInt(byte_buffer,inputStream);
        BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        int red, green, blue;
        int read_colors = 0;
        int current_color = 0;
        int color_repetition;
        for (int i = 0; i < bytes_to_read/4; i++) {
            // Retrieve color from the binary, the first byte is the number the color
            // repeats and the next three bytes belong to red, green and blue
            color_repetition = ByteReader.readSignedByte(byte_buffer,inputStream);
            red = ByteReader.readSignedByte(byte_buffer,inputStream);
            green = ByteReader.readSignedByte(byte_buffer,inputStream);
            blue = ByteReader.readSignedByte(byte_buffer,inputStream);
            // Add the retrieved colors to read_colors
            read_colors += color_repetition;
            for (int pixel = current_color; pixel < read_colors; pixel++) {
                // Using % in the current pixel we can know the value of x
                // while the pixel / width gives us the y value
                image.setRGB(pixel%width, pixel/width, new Color(red,green,blue).getRGB());
            }
            current_color=read_colors;
        }
            inputStream.close();
            File outputfile = new File(decompressed_file);
            ImageIO.write(image, "bmp", outputfile);
    }
}
