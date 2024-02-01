package samplecodes;

import java.awt.image.RescaleOp;
import java.lang.ProcessBuilder.Redirect;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

// this is a class i have created to implement the filters that are used in the system


public class FilterMethods {

	// the argument is the image that we want to apply the filter on. 
	public static ImageMatrix BlurFilter(ImageMatrix image) {
		
		int width = image.getWidth();
		int height = image.getHeight();
		// a blank image matrix is created . 
		ImageMatrix res = new ImageMatrix(width, height);
		
		
		//iterating over all the pixels of the image.
		for(int x =0; x < width; x++) {
			for(int y = 0; y<height; y++) {
				
				
				int red_sum = 0;
				int green_sum = 0; 
				int blue_sum = 0;
				
				int count = 0;
				//getting the average of the pixels by checking the neighboring pixels.
				for(int i = x-1; i<=x+1; i++) {
					for(int j = y-1; j<=y+1;j++) {
						//making sure the neighboring pixels are within the image boundaries.
						if(((i>=0)&&(i<width)) && ((j>=0) && (j<height))) {
							red_sum+= image.getRed(i, j);
							green_sum+= image.getGreen(i, j);
							blue_sum+= image.getBlue(i, j);
							count++;
							
							//counting the number of neighboring pixels, because in the borders
							// it is not always 9
							
							
						}
					}
				}
				//changing the red green blue values to the averages of the neighboring pixels. 
				int rgb = ImageMatrix.convertRGB(red_sum/count, green_sum/count, blue_sum/count);
				res.setRGB(x, y, rgb);
				
				
				
			}
		}
		
		return res;
		
	}
	
	
	public static ImageMatrix SharpenFilter(ImageMatrix image) {
		//blank imagematrix is created.
		int width = image.getWidth();
		int height = image.getHeight();
		
		ImageMatrix res = new ImageMatrix(width, height);
		
		//create the blurred version of it
		// subtract it from the regular version = details
		ImageMatrix blurred_image = BlurFilter(BlurFilter(BlurFilter(image)));
		
		// detail + regular version = sharpened version 
		// do this for every pixel in the imagematrix.
		for(int x =0; x<width; x++) {
			for(int y=0; y<height; y++) {
				int red = image.getRed(x, y)  + (image.getRed(x, y) - blurred_image.getRed(x, y));
				int green = image.getGreen(x, y) + (image.getGreen(x, y) - blurred_image.getGreen(x, y));
				int blue = image.getBlue(x, y)+ (image.getBlue(x, y) - blurred_image.getBlue(x, y));
				
				// Clamp values to the range [0, 255]
				// this prevents white pixels which occur 
				//when the rgb values are outside the normal range.
	            red = Math.max(0, Math.min(red, 255));
	            green = Math.max(0, Math.min(green, 255));
	            blue = Math.max(0, Math.min(blue, 255));
				
	            //set the rgb value we get for a certain pixel to the blank matrix we created. 
				int rgb = ImageMatrix.convertRGB(red, green, blue);
				res.setRGB(x, y, rgb);
				
			}
		}
			
		return res;
	}
	
	
	public static ImageMatrix GrayscaleFilter(ImageMatrix image) {
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		
		ImageMatrix res = new ImageMatrix(width, height);
		
		//get rgb values for each pixel
		// for a single pixel combine all of them and divide it by 3. 
		// assign the same value for all rgb values.  
		// do this for all pixels of the image. 
		for(int x =0; x<width; x++) {
			for(int y=0; y<height; y++) {
				int red = image.getRed(x, y);
				int green = image.getGreen(x, y);
				int blue = image.getBlue(x, y);
				int gray = (red + green + blue)/3;
				int gray_rgb = ImageMatrix.convertRGB(gray, gray, gray);
				res.setRGB(x, y, gray_rgb);
				
			}
		}
		
	
	
	
		return res;
	
	}
	
	public static ImageMatrix EdgeDetectionFilter(ImageMatrix image) {
		
		int width = image.getWidth();
		int height = image.getHeight();
		// applying sobel method. 
		
		ImageMatrix res = new ImageMatrix(width, height);
		
		//we have to first convert the image to a grayscale filter. 
		ImageMatrix grayscale_image = GrayscaleFilter(image);
		
		
		int[][] sobelHorizontalKernel =  {{-1, 0, 1},{-2, 0, 2}, {-1, 0, 1}};
		int[][] sobelVerticalKernel = {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};
		
		
		//performing convolution(we start from 1 and width, height -1 because we cant perform on border pixels)
		
		for (int x = 1; x < width - 1; x++) {
	        for (int y = 1; y < height - 1; y++) {
	            int gradientXRed = 0;
	            int gradientYRed = 0;
	            int gradientXGreen = 0;
	            int gradientYGreen = 0;
	            int gradientXBlue = 0;
	            int gradientYBlue = 0;
	            
	            for (int i = -1; i <= 1; i++) {
	                for (int j = -1; j <= 1; j++) {
	                    int RGB = image.getRGB(x + i, y + j);
	                    int sobelX = sobelHorizontalKernel[i + 1][j + 1];
	                    int sobelY= sobelVerticalKernel[i + 1][j + 1];
	                    
	                    gradientXRed += image.getRed(x+i, y+j) *sobelX;
	                    gradientYRed += image.getRed(x+i, y+j)*sobelY;
	                    gradientXGreen += image.getGreen(x+i, y+j)*sobelX;
	                    gradientYGreen += image.getGreen(x+i, y+j)*sobelY;
	                    gradientXBlue += image.getBlue(x+i, y+j)*sobelX;
	                    gradientYBlue += image.getBlue(x+i, y+j)*sobelY;
	            
	            		
	            		
	            	}
	            }
	            
	            
	            
	            //caluclating the gradient
	            int red = (int) Math.sqrt(gradientXRed* gradientXRed + gradientYRed * gradientYRed);
	            int green = (int) Math.sqrt(gradientXGreen* gradientXGreen + gradientYGreen * gradientYGreen);
	            int blue = (int) Math.sqrt(gradientXBlue* gradientXBlue + gradientYBlue * gradientYBlue);
	            
	            //normalizing the gradient. 
	            
	            int max_gradient = Math.max(Math.max(red, blue), green);
	            int normalized_gradient = (int) (255.0 * max_gradient / 255.0);
	            int rgb = ImageMatrix.convertRGB(normalized_gradient, normalized_gradient, normalized_gradient);
	            res.setRGB(x, y, rgb);

	            
	            
	            
	        }        
	   
		}
		return res;
	}
	
	
	public static ImageMatrix BrightnessFilter(ImageMatrix image, int intensity) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		
		ImageMatrix res = new ImageMatrix(width, height);
		
		//iterating over all the pixels of the image and increasing the rgb values by a certain amount. 
		for(int x=0; x<width; x++ ) {
			for(int y =0; y<height; y++) {
				int red = image.getRed(x, y);
				int blue = image.getBlue(x, y);
				int green = image.getGreen(x, y);
				
				red += intensity;
				blue += intensity;
				green += intensity;
				
				//we also have to make sure r g b values are within 0 and 255 otherwise pixel overflow will happen.
				red = Math.max(0, Math.min(255, red));
	            green = Math.max(0, Math.min(255, green));
	            blue = Math.max(0, Math.min(255, blue));
				
				
				int rgb = ImageMatrix.convertRGB(red, green, blue);
				res.setRGB(x, y, rgb);
				
				
				
			}
		}
		
		return res;
	}
	
	
	public static ImageMatrix ContrastFilter(ImageMatrix image, double intensity) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		
		ImageMatrix res = new ImageMatrix(width, height);
		//Iterating over all the pixels in the matrix. 
		
		for(int x=0; x<width; x++ ) {
			for(int y =0; y<height; y++) {
				
				int red = image.getRed(x, y);
				int blue = image.getBlue(x, y);
				int green = image.getGreen(x, y);
			
				red = (int) ((red - 128) * intensity + 128);
				green = (int) ((green - 128) * intensity + 128);
				blue = (int) ((blue - 128) * intensity + 128);
	
				
				// Again, ensure the new values stay within the range of 0,255
				red = Math.max(0, Math.min(255, red));
				green = Math.max(0, Math.min(255, green));
				blue = Math.max(0, Math.min(255, blue));
				
				int rgb = ImageMatrix.convertRGB(red, green, blue);
				
				res.setRGB(x, y, rgb);
				
				
			}
		}
		
		
		return res;
		
	}
	
	
}
