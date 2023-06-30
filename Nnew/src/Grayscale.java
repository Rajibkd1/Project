import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Grayscale {
  public static void main(String[] args) throws IOException {
    BufferedImage img = null;
    File inputFile = null;

    // Read the input image path from the user
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the path of the input image: ");
    String imagePath = scanner.nextLine();

    // Read the input image
    try {
      inputFile = new File(imagePath);
      img = ImageIO.read(inputFile);
    } catch (IOException e) {
      System.out.println(e);
      return;
    }

    // Get image width and height
    int width = img.getWidth();
    int height = img.getHeight();

    // Convert to grayscale
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int p = img.getRGB(x, y);

        int a = (p >> 24) & 0xff;
        int r = (p >> 16) & 0xff;
        int g = (p >> 8) & 0xff;
        int b = p & 0xff;

        // Calculate average
        int avg = (r + g + b) / 3;

        // Replace RGB value with avg
        p = (a << 24) | (avg << 16) | (avg << 8) | avg;

        img.setRGB(x, y, p);
      }
    }

    // Save the grayscale image
    try {
      File outputDir = new File("C:\\Users\\Asus\\Downloads\\PIC");
      if (!outputDir.exists()) {
        outputDir.mkdirs();
      }

      File outputFile = new File(outputDir, "Output.jpg");
      ImageIO.write(img, "jpg", outputFile);
      System.out.println("Grayscale image saved successfully.");
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
