package filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Blur {
	
	private BufferedImage img;
	  /**
	   * 
	   * @param file
	   * @param kernelRadius
	   * @return
	   * @throws IOException
	   */
	public BufferedImage blurConvert(File file, int kernelRadius) throws IOException{

          img = ImageIO.read(file);
          
          //get image width and height
          int width = img.getWidth();
          int height = img.getHeight();
          
          // make a copy 
          BufferedImage blurCopy = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          
          int beginningXLoc = (int) kernelRadius / 2;
          int beginningYLoc = (int) kernelRadius / 2;
          
          for (int y = beginningYLoc; y < height - beginningYLoc; y++) {
              for (int x = beginningXLoc; x < width - beginningXLoc; x++) {
            	  
            	  int sumOfR = 0;
            	  int sumOfG = 0;
            	  int sumOfB = 0;
            	  
                  for (int locY = y - beginningYLoc; locY <= y + beginningYLoc; locY++) {
                	  for (int locX = x - beginningXLoc; locX <= x + beginningXLoc; locX++) {
                		  
                          int p = img.getRGB(locX,locY);
                          
                          int r = (p>>16)&0xff;
                          int g = (p>>8)&0xff;
                          int b = p&0xff;
                          
                          sumOfR += r;
                          sumOfG += g;
                          sumOfB += b;
                      }
                  }
                  
                sumOfR /= kernelRadius*kernelRadius;
                sumOfG /= kernelRadius*kernelRadius;
                sumOfB /= kernelRadius*kernelRadius;
                  
				int setRGB = (255 << 24) | (sumOfR << 16) | (sumOfG << 8) | sumOfB;
                  blurCopy.setRGB(x, y, setRGB);
                  
              }
          }
		return blurCopy;
	
	}
}
