import java.io.File;

import net.sourceforge.tess4j.*;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
 


public class up {
	public static void main(String[] args)
    {
	Tesseract tesseract = new Tesseract();
	 try {
		  
         tesseract.setDatapath("C:\\Users\\Asus\\eclipse-workspace\\Nnew\\tessdata");

         
         String text
             = tesseract.doOCR(new File("C:\\Users\\Asus\\Downloads\\download - Copy.jpg"));

         // path of your image file
         System.out.print(text);
     }
     catch (TesseractException e) {
         e.printStackTrace();
     }
 }
}

