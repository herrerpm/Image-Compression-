# Image Compression

For my multimedia and graphics class I had to develop an algorithm to compress a given bitmap image. I programmed a version of the RLE
algorithm that looks for color repetitions. The algorithm then count how many times a color is repeated, and saves the number of repetitions
along with the color. For this I use 4 bytes, the first one stores the repetitions, ranging from 0 to 255, and the next three bytes store
the red, green and blue components of said color.

Now, for the headers I only save the width and height, and it is also worth noting that the color repetitions have a max of 255, if it is 
bigger it would get cut off into two different chains. Alongside the RLE, I implemented a function that determines wether colors are similar
to each other or not, and if they are below a certain percentage of likeness they would be interpreted as the same color. For this I provide
the ColorHandler ColorSimilarity method, where by default the threshold allowed is <0.39% of likeness. Nonetheless, you could overload the method
to try and play with this parameter. The higher the threshold the lower the size of the file, and the lower fidelity the decompressed image will
have. On the other hand, the lower the value, the greater the size will be and the better resemblance the decompressed image will be.

I recommend you to play with this parameter, I have found that 0.0039 is a great value for both detailed and non detailed images, however images
without great detail can get away with a treshold of 1% or 0.5%. To overload this function just add the parameter at the Compressor class, in the 
call of the ColorSimilarity method.
