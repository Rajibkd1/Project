import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CannyEdgeDetection {
    public static void main(String[] args) {
        // Load the OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Read input image
        Mat image = Imgcodecs.imread("C:\\Users\\Asus\\Downloads\\PIC\\Output.jpg", Imgcodecs.IMREAD_GRAYSCALE);

        // Apply Gaussian blur to reduce noise
        Mat blurredImage = new Mat();
        Imgproc.GaussianBlur(image, blurredImage, new Size(5, 5), 0);

        // Perform Canny edge detection
        Mat edges = new Mat();
        double threshold1 = 50;  // lower threshold for hysteresis procedure
        double threshold2 = 150; // higher threshold for hysteresis procedure
        Imgproc.Canny(blurredImage, edges, threshold1, threshold2);

        // Save the result
        Imgcodecs.imwrite("C:\\Users\\Asus\\Downloads\\PIC\\output1.jpg", edges);
    }
}
