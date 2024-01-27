package filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Contrast {
	/**
	 * 
	 * @param file
	 * @param contrastDegree
	 * @return
	 * @throws IOException
	 */
	public BufferedImage contrastImage(File file, double contrastDegree) throws IOException {
		
		BufferedImage img = ImageIO.read(file);
		int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage contrastedImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
              int p = img.getRGB(x,y);

              int red = (p >> 16) &0xff;
              int green = (p >> 8)&0xff;
              int blue = p &0xff;

              double contrastRed = ((red - 128) * contrastDegree) + 128;
              double contrastGreen = ((green - 128) * contrastDegree) + 128;
           	  double contrastBlue = ((blue - 128) * contrastDegree) + 128;
           	  
              contrastRed = Math.min(255, Math.max(0, contrastRed)); // Ensure values are within the valid range
              contrastGreen = Math.min(255, Math.max(0, contrastGreen));
              contrastBlue = Math.min(255, Math.max(0, contrastBlue));


              p = (255 << 24) | ((int)contrastRed << 16) | ((int)contrastGreen << 8) | (int)contrastBlue;
              
              contrastedImage.setRGB(x, y, p); 
            }
          }
        
        return contrastedImage;
	}
}
