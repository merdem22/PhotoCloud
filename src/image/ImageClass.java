package image;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import samplecodes.ImageMatrix;
import samplecodes.ImageSecretary;

//this class represents an image in the photocloud platform 
//each image has an ImageIcon which we use to display images on the gui 
// an arraylist of comments
// an integer value of like count, a string of file name and extension name
// a boolean value determining whether if it is public or not. 
// and a duplicate of itself when it is first initalized(this helps in discarding filters from image).

public class ImageClass {

	public ImageIcon image;
	ArrayList<Comment> comments = new ArrayList<Comment>();
	public int likes;
	public String description;
	public String file_name;
	public String file_name_dup;
	public String extension_name;
	public ImageIcon icon_dup;
	public boolean is_public;
	
	
	//two seperate constructors (one is without like argument, if it is not specified like is set to 0.)
	
	public ImageClass(ImageIcon image, String file_name, String extension_name, boolean is_public) {

		this.image = image;
		likes = 0; 
		this.file_name = file_name;
		this.extension_name = extension_name;
		icon_dup = image;
		this.file_name_dup = file_name;
		this.is_public = is_public;
		
	}

	public ImageClass(ImageIcon image, int likes, String file_name, String extension_name, boolean is_public) {
		this.image = image;
		this.likes = likes;
		this.file_name = file_name;
		this.extension_name = extension_name;
		this.is_public = is_public;
		icon_dup = image;
		
	}
	
	public ArrayList<Comment> getComments() {
		return comments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	
	
	
}
