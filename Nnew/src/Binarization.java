import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Binarization {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Asus\\Downloads\\PIC\\feem_5b98bb0f9f4cb3bc_IMG_20230613_180044.jpg");
        BufferedImage originalImage = ImageIO.read(file);

        BufferedImage grayscaleImage = convertToGrayscale(originalImage);

        int threshold = 100; // Threshold value (adjust as needed)
        BufferedImage binarizedImage = binarizeImage(grayscaleImage, threshold);

        File outputImageFile = new File("C:\\Users\\Asus\\Downloads\\PIC\\Binary.jpg");
        ImageIO.write(binarizedImage, "jpg", outputImageFile);

        System.out.println("Image grayscale and binarization completed.");
    }

    private static BufferedImage convertToGrayscale(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = grayscaleImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();

        return grayscaleImage;
    }

    private static BufferedImage binarizeImage(BufferedImage image, int threshold) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage binarizedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color color = new Color(image.getRGB(col, row));
                int intensity = color.getRed(); // Assumes grayscale image

                if (intensity < threshold) {
                    binarizedImage.setRGB(col, row, Color.BLACK.getRGB());
                } else {
                    binarizedImage.setRGB(col, row, Color.WHITE.getRGB());
                }
            }
        }

        return binarizedImage;
    }
}
