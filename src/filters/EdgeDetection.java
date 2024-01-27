package filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class EdgeDetection {
/**
 * 
 * @param file
 * @return
 * @throws IOException
 */
	public BufferedImage EdgeDetector(File file) throws IOException{
		
		Grayscale grayCon = new Grayscale();
		EdgeHelperBlur blurCon = new EdgeHelperBlur();
		BufferedImage grayImage = grayCon.grayscaleConvert(file, 100); 
		BufferedImage blurredImage = blurCon.blurConvert(grayImage,3);
		
		
		int width = grayImage.getWidth();
        int height = grayImage.getHeight();
        
        BufferedImage edgeDetectedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
            	
                int xDerivative = - calculateDerivative(blurredImage, x - 1, y - 1) - 2*calculateDerivative(blurredImage, x - 1, y) - calculateDerivative(blurredImage, x - 1, y + 1) + calculateDerivative(blurredImage, x + 1, y - 1) + 2*calculateDerivative(blurredImage, x + 1, y) + calculateDerivative(blurredImage, x + 1, y + 1);
                int yDerivative = - calculateDerivative(blurredImage, x - 1, y - 1) - 2*calculateDerivative(blurredImage, x, y - 1) - calculateDerivative(blurredImage, x + 1, y - 1) + calculateDerivative(blurredImage, x - 1, y + 1) + 2*calculateDerivative(blurredImage, x, y + 1) + calculateDerivative(blurredImage, x + 1, y + 1);
                
            	int magnitude = (int) Math.sqrt(Math.pow(xDerivative, 2) + Math.pow(yDerivative, 2));
            
            	if (magnitude < 0) {
            	    magnitude = 0;
            	} 
            	else if (magnitude > 255) {
            	    magnitude = 255;
            	}

            	int p = (255 << 24) | (magnitude << 16) | (magnitude << 8) | magnitude;
                
                edgeDetectedImage.setRGB(x, y, p);
            }
        }
        return edgeDetectedImage;
	}
	
	 private int calculateDerivative(BufferedImage image, int x, int y) {
		    
		 int pixel = image.getRGB(x, y);
		 int r = (pixel >> 16) & 0xFF;
	     int g = (pixel >> 8) & 0xFF;
         int b = pixel & 0xFF;	
         
 	    // Compute the intensity as the average of the RGB values
 	     int intensity = (r + g + b) / 3;
		 
		 return intensity;
		}
}
