package filters;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Grayscale{
	
	private BufferedImage img;
  /**
   * 
   * @param file
   * @param percentage
   * @return
   * @throws IOException
   */
	public BufferedImage grayscaleConvert(File file, int percentage) throws IOException{

          img = ImageIO.read(file);

          //get image width and height
          int width = img.getWidth();
          int height = img.getHeight();
                  
          //convert to grayscale
          for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
              int p = img.getRGB(x,y);

              int a = (p>>24)&0xff;
              int r = (p>>16)&0xff;
              int g = (p>>8)&0xff;
              int b = p&0xff;

              // Calculate new RGB values based on the grayscale percentage
              int newR = (int) (r * (percentage / 100.0));
              int newG = (int) (g * (percentage / 100.0));
              int newB = (int) (b * (percentage / 100.0));

              // Calculate new grayscale value based on the new RGB values
              int newAvg = (newR + newG + newB) / 3;

              // Replace RGB value with new grayscale value
              p = (a << 24) | (newAvg << 16) | (newAvg << 8) | newAvg;
              
              img.setRGB(x, y, p); 
            }
          }
	return img;
  }
}

