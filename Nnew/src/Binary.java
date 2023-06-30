import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Binary {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Asus\\Downloads\\PIC\\CamScanner 06-11-2023 19.56_5.jpg");
        BufferedImage originalImage = ImageIO.read(file);

        int threshold = 100; // Threshold value (adjust as needed)
        BufferedImage binarizedImage = binarizeImage(originalImage, threshold);

        File outputImageFile = new File("C:\\Users\\Asus\\Downloads\\PIC\\Binary.jpg");
        ImageIO.write(binarizedImage, "jpg", outputImageFile);

        System.out.println("Image binarization completed.");
    }

    private static BufferedImage binarizeImage(BufferedImage image, int threshold) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage binarizedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D graphics = binarizedImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color color = new Color(binarizedImage.getRGB(col, row));
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
