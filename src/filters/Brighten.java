package filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Brighten {
	/**
	 * 
	 * @param file
	 * @param brightenDegree
	 * @return
	 * @throws IOException
	 */
	public BufferedImage brightenImage(File file, int brightenDegree) throws IOException {
		
		BufferedImage img = ImageIO.read(file);
		int r;
		int g;
		int b;
		int RGB;
		int p;
		int brightInc = (int)(brightenDegree * 255 / 100); 
		
		BufferedImage brightenedImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		for (int y=0; y<img.getHeight(); y+=1) {
			for (int x=0; x<img.getWidth(); x+=1) {
				p = img.getRGB(x, y);
				
				r = ((p >> 16) & 0xFF) + brightInc;
				g = ((p >> 8) & 0xFF) + brightInc;
				b = (p & 0xFF) + brightInc;
				
				
				if (r > 255) {
					r = 255;
				}
				if (g > 255) {
					g = 255;
				}
				if (b > 255) {
					b = 255;
				}
				
				RGB = (255 << 24) | (r << 16) | (g << 8) | b;
				brightenedImage.setRGB(x, y, RGB);
			}
		}
		
		return brightenedImage;
	}
}
