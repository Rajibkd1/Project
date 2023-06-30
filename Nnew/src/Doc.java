import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Doc {
    public static void main(String[] args) {
        // Specify the path to the text file
        String filePath = "C:\\Users\\Asus\\Downloads\\PIC\\Output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder text = new StringBuilder();

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
            String extractedText = text.toString();
            if (extractedText.contains("সংগ্রাম")) {
                System.out.println("IN this sentance there is a word 'War'");
            } else {
                System.out.println("Find Nothing");
            }
           
            // Print the extracted text
//            System.out.println(text.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
