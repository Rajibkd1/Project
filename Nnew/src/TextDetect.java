import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.jai.*;

public class TextDetect {

    public static void main(String[] args) {
        try {
            // Load the original image
            File inputFile = new File("input.jpg");
            RenderedImage originalImage = JAI.create("fileload", inputFile.getAbsolutePath());

            // Perform text detection and obtain the minimum bounding rectangle
            Rectangle textBounds = performTextDetection(originalImage);

            // Crop the image using the text bounding rectangle
            RenderedImage croppedImage = cropImage(originalImage, textBounds);

            // Save the cropped image
            File outputFile = new File("output.jpg");
            ImageIO.write(croppedImage, "jpg", outputFile);

            System.out.println("Image cropping completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Rectangle performTextDetection(RenderedImage image) {
        // Implement text detection algorithm here
        // This step should identify the text regions and calculate the minimum bounding rectangle
        // Return the bounding rectangle of the text areas
        return new Rectangle(100, 100, 300, 200); // Example rectangle
    }

    private static RenderedImage cropImage(RenderedImage image, Rectangle bounds) {
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(image);
        pb.add(bounds.x);
        pb.add(bounds.y);
        pb.add(bounds.width);
        pb.add(bounds.height);

        RenderedImage croppedImage = JAI.create("crop", pb);
        return croppedImage;
    }
}
