package filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sharpen {
	/**
	 * 
	 * @param file
	 * @param sharpenDegree
	 * @return
	 * @throws IOException
	 */
	public BufferedImage sharpenImage(File file, double sharpenDegree) throws IOException {
		
		BufferedImage img = ImageIO.read(file);
		
		 // Get image width and height
        int width = img.getWidth();
        int height = img.getHeight();

        // Make a copy of the original image
        BufferedImage blurCopy = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        // Blur the image
        int beginningXLoc = (int) (sharpenDegree / 2);
        int beginningYLoc = (int) (sharpenDegree / 2);

        for (int y = beginningYLoc; y < height - beginningYLoc; y++) {
            for (int x = beginningXLoc; x < width - beginningXLoc; x++) {
                int sumOfR = 0;
                int sumOfG = 0;
                int sumOfB = 0;

                for (int locY = y - beginningYLoc; locY <= y + beginningYLoc; locY++) {
                    for (int locX = x - beginningXLoc; locX <= x + beginningXLoc; locX++) {
                        int p = img.getRGB(locX, locY);
                        int r = (p >> 16) & 0xFF;	
                        int g = (p >> 8) & 0xFF;
                        int b = p & 0xFF;

                        sumOfR += r;
                        sumOfG += g;
                        sumOfB += b;
                    }
                }

                sumOfR /= sharpenDegree * sharpenDegree;
                sumOfG /= sharpenDegree * sharpenDegree;
                sumOfB /= sharpenDegree * sharpenDegree;

                int setRGB = (255 << 24) | (sumOfR << 16) | (sumOfG << 8) | sumOfB;
                blurCopy.setRGB(x, y, setRGB);
            }
        }

        // Create a new BufferedImage object for the sharpened image
        BufferedImage sharpenedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Subtract blurred image from the original image and add details to make it sharp
        for (int y = beginningYLoc; y < height - beginningYLoc; y++) {
            for (int x = beginningXLoc; x < width - beginningXLoc; x++) {
                int originalRGB = img.getRGB(x, y);
                int blurredRGB = blurCopy.getRGB(x, y);

                int originalR = (originalRGB >> 16) & 0xFF;
                int originalG = (originalRGB >> 8) & 0xFF;
                int originalB = originalRGB & 0xFF;

                int blurredR = (blurredRGB >> 16) & 0xFF;
                int blurredG = (blurredRGB >> 8) & 0xFF;
                int blurredB = blurredRGB & 0xFF;

                int sharpenedR = originalR + (originalR - blurredR);
                int sharpenedG = originalG + (originalG - blurredG);
                int sharpenedB = originalB + (originalB - blurredB);

                sharpenedR = Math.min(255, Math.max(0, sharpenedR)); // Ensure values are within the valid range
                sharpenedG = Math.min(255, Math.max(0, sharpenedG));
                sharpenedB = Math.min(255, Math.max(0, sharpenedB));

                int sharpenedRGB = (255 << 24) | (sharpenedR << 16) | (sharpenedG << 8) | sharpenedB;
                sharpenedImg.setRGB(x, y, sharpenedRGB);
            }
        }
        return sharpenedImg;
	}

}
