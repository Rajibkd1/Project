import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Text {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load the image using OpenCV
        Mat image = Imgcodecs.imread("your_image.jpg");

        // Convert the image to grayscale
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

        // Apply thresholding or other preprocessing techniques if necessary
        // (e.g., Imgproc.threshold(gray, gray, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU));

        // Apply text extraction using OpenCV
        MatOfRect textRegions = new MatOfRect();
        Imgproc.textDetection(gray, textRegions);

        // Iterate over the detected text regions and extract the text
        for (Rect rect : textRegions.toArray()) {
            Mat roi = gray.submat(rect);
            String text = extractTextFromRegion(roi);
            System.out.println("Extracted Text: " + text);
            Imgproc.rectangle(image, rect, new Scalar(0, 255, 0), 2);
        }

        // Display the image with text regions highlighted
        Imgcodecs.imwrite("output.jpg", image);
    }

    private static String extractTextFromRegion(Mat region) {
        // Threshold the region to convert it to a binary image
        Mat threshold = new Mat();
        Imgproc.threshold(region, threshold, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);

        // Find contours in the thresholded image
        Mat contours = new Mat();
        Imgproc.findContours(threshold, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Iterate over the contours and extract individual characters
        StringBuilder extractedText = new StringBuilder();
        for (int i = 0; i < contours.size().height; i++) {
            Mat contour = contours.get(i);
            Rect boundingRect = Imgproc.boundingRect(contour);

            // Filter out contours that are too small (likely noise) or too large (likely non-character regions)
            int minContourWidth = 10;
            int maxContourWidth = 200;
            int minContourHeight = 10;
            int maxContourHeight = 200;
            if (boundingRect.width < minContourWidth || boundingRect.width > maxContourWidth ||
                    boundingRect.height < minContourHeight || boundingRect.height > maxContourHeight) {
                continue;
            }

            // Extract the character region from the original grayscale region
            Mat characterRegion = region.submat(boundingRect);

            // Perform additional processing or recognition on the character region as needed
            String character = processCharacterRegion(characterRegion);
            extractedText.append(character);
        }

        return extractedText.toString();
    }

    private static String processCharacterRegion(Mat characterRegion) {
        // Resize the character region to a fixed size (e.g., 32x32) for consistent processing
        Mat resizedRegion = new Mat();
        Size newSize = new Size(32, 32);
        Imgproc.resize(characterRegion, resizedRegion, newSize);

        // Convert the resized region to grayscale
        Mat grayRegion = new Mat();
        Imgproc.cvtColor(resizedRegion, grayRegion, Imgproc.COLOR_BGR2GRAY);

        // Apply additional processing or recognition techniques as needed
        // For example, you can use a machine learning model to recognize the character

        // For this example, we'll simply convert the gray region to a string using ASCII art
        StringBuilder character = new StringBuilder();
        for (int y = 0; y < grayRegion.rows(); y++) {
            for (int x = 0; x < grayRegion.cols(); x++) {
                double[] pixel = grayRegion.get(y, x);
                int intensity = (int) pixel[0];
                char asciiChar = intensityToAscii(intensity);
                character.append(asciiChar);
            }
            character.append("\n");
        }

        return character.toString();
    }

    private static char intensityToAscii(int intensity) {
        // Map the intensity value to an ASCII character based on a predefined lookup table
        // You can define your own lookup table based on the desired character set or use a machine learning model

        // Example lookup table for ASCII characters from ' ' (32) to '~' (126)
        char[] asciiChars = {' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0',
                '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'};

        // Map the intensity value to the range of the ASCII characters
        int numChars = asciiChars.length;
        int index = (intensity * (numChars - 1)) / 255;

        return asciiChars[index];
    }
}
