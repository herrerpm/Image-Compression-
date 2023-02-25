import java.awt.Color;

public class ColorHandler {
    /**
     * The colorSimilarity method analyzes the similarity between two
     * colors by getting the absolute difference between each of the channels.
     * After getting this values they are then averaged and an arbitrary threshold
     * is permitted. The threshold ranges from 0 to 1, and the lower the threshold
     * permitted the higher fidelity the compressed image will have. This is useful as
     * certain images don´t require such quality, and they can be encoded with fewer bytes
     * however, images with gradients for example would need more bytes to be
     * represented correctly. This function defaults the threshold to 0.0039, which is
     * guaranteed to give quality images while compressing the image.
     *
     * @param color_1: A java.awt.color
     * @param color_2: A java.awt.color
     * @return A boolean that represents if a color the colors are similar or not
     */
    public static boolean ColorSimilarity(Color color_1, Color color_2){
        float red_difference = Math.abs(color_1.getRed() -  color_2.getRed());
        float green_difference = Math.abs(color_1.getGreen() -  color_2.getGreen());
        float blue_difference = Math.abs(color_1.getBlue() -  color_2.getBlue());
        float average_difference = (red_difference + green_difference + blue_difference) / 3f;
        // Use Linear Conversion to turn the difference range from 0-255 to 0-1
        return (average_difference/255)<0.0039f;
    }

    /**
     *  This method overloads the ColorSimilarity method, it accepts an extra value for the
     *  threshold, as to define it however you want.
     * @param color_1: A java.awt.color
     * @param color_2: A java.awt.color
     * @param threshold: A float representing the allowed color similarity percentage,
     *                 ranges from 0 to 1, smaller values saving more information with
     *                 a higher file size, while higher values loosing quality and
     *                 returning smaller files
     * @return A boolean that represents if a color the colors are similar or not
     */
    public static boolean ColorSimilarity(Color color_1, Color color_2, float threshold){
        float red_difference = Math.abs(color_1.getRed() -  color_2.getRed());
        float green_difference = Math.abs(color_1.getGreen() -  color_2.getGreen());
        float blue_difference = Math.abs(color_1.getBlue() -  color_2.getBlue());
        float average_difference = (red_difference + green_difference + blue_difference) / 3f;
        // Use Linear Conversion to turn the difference range from 0-255 to 0-1
        return (average_difference/255)<threshold;
    }
}