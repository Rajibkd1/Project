import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class EX2 {
    public static void main(String[] args) {
        // Load the OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("C:\\Users\\Asus\\eclipse-workspace\\Nnew\\tessdata");
            tesseract.setLanguage("ben");

            File imageFile = new File("C:\\Users\\Asus\\Downloads\\Compressed\\Bangla-OCR-Thesis-Project-master\\Binirization\\grayscale2.jpg");

            // Read the image using OpenCV
            Mat image = Imgcodecs.imread(imageFile.getAbsolutePath());
            
            // Perform any necessary image processing using OpenCV
            // For example, you can apply filters, resize the image, etc.
            // imgProcessing(image);
            
            // Convert the OpenCV image to a BufferedImage
            BufferedImage bufferedImage = matToBufferedImage(image);
            
            // Perform OCR using Tesseract on the BufferedImage
            String extractedText = tesseract.doOCR(bufferedImage);
            
            // Specify the path and filename of the output text file
            String outputFilePath = "C:\\Users\\Asus\\Documents\\output.txt";
            
            // Write the extracted text to the output file
            try (FileWriter writer = new FileWriter(outputFilePath)) {
                writer.write(extractedText);
            }
            
            System.out.println("Text extracted and stored in file: " + outputFilePath);
        } catch (TesseractException | IOException e) {
            e.printStackTrace();
        }
    }
    
    // Example image processing method using OpenCV
    // Uncomment and modify this method according to your image processing requirements
    /*
    private static void imgProcessing(Mat image) {
        // Apply image processing operations using OpenCV
        // For example, you can apply filters, resize the image, etc.
        // Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
        // Imgproc.GaussianBlur(image, image, new Size(3, 3), 0);
    }
    */
    
    // Convert OpenCV Mat to BufferedImage
    private static BufferedImage matToBufferedImage(Mat matrix) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, matOfByte);
        String byteArray = matOfByte.toArray();
        
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(byteArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return bufferedImage;
    }
}
