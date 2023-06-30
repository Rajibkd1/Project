import java.util.List;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Open {
    public static void main(String[] args) {
        // Load OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load the image
        Mat image = Imgcodecs.imread("C:\\Users\\Asus\\Downloads\\PIC\\CamScanner 06-11-2023 19.56_10.jpg");

        // Convert the image to grayscale
        Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        // Apply thresholding to convert to binary image
        Mat binaryImage = new Mat();
        Imgproc.threshold(grayImage, binaryImage, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);

        // Apply morphological operations to remove noise and improve text regions
        Mat morphImage = new Mat();
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Imgproc.morphologyEx(binaryImage, morphImage, Imgproc.MORPH_OPEN, kernel);

        // Find contours in the image
        Mat hierarchy = new Mat();
        MatOfPoint largestContour = null;
        double maxArea = 0.0;
        Mat contoursImage = new Mat();
        Imgproc.findContours(morphImage, (List<MatOfPoint>) contoursImage, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Find the contour with the largest area
        for (Point contour : ((MatOfPoint) contoursImage).toList()) {
            double area = Imgproc.contourArea(contour);
            if (area > maxArea) {
                maxArea = area;
                largestContour = contour;
            }
        }

        // Create a bounding rectangle around the largest contour
        Rect boundingRect = Imgproc.boundingRect(largestContour);

        // Crop the image to the bounding rectangle
        Mat croppedImage = new Mat(image, boundingRect);

        // Save or display the cropped image
        Imgcodecs.imwrite("C:\\Users\\Asus\\Downloads\\PIC\\cropped_image.jpg", croppedImage);
    }
}
