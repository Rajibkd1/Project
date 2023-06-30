import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Experiment {
    public static void main(String[] args) {
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("C:\\Users\\Asus\\eclipse-workspace\\Nnew\\tessdata");
            tesseract.setLanguage("ben");

            File imageFile = new File("C:\\Users\\Asus\\Downloads\\PIC\\Binary.jpg");

            String extractedText = tesseract.doOCR(imageFile);
            
            
            String outputFilePath = "C:\\Users\\Asus\\Downloads\\PIC\\Output.txt";
            
            
            try (FileWriter writer = new FileWriter(outputFilePath)) {
                writer.write(extractedText);
            }
            
            System.out.println("Text extracted and stored in file: " + outputFilePath);
        } catch (TesseractException | IOException e) {
            e.printStackTrace();
        }
    }
}
