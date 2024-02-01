package methods;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Methods {

	public static boolean isNumerical(String str) {
	    try {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public static ImageIcon resizeImage(ImageIcon image, int width, int height) {
		Image o_image = image.getImage();
		Image n_image = o_image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resultImageIcon = new ImageIcon(n_image);
		return resultImageIcon;
	}
	
}
