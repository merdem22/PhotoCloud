package gui.imagepage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Users.User;
import gui.discoverpage.DiscoverPageFrame;
import gui.profilepage.LocalUserProfilePageFrame;
import image.ImageClass;
import logger.BaseLogger;
import methods.ImageFilter;
import methods.Methods;
import samplecodes.FilterMethods;
import samplecodes.ImageMatrix;
import samplecodes.ImageSecretary;

public class LocalUserImageFrame extends ImageFrame{

	
	// this class is a subclass of the ImageFrame
	// this class represents the image which belongs to a local user
	// this class has additional attributes such as saving, applying filters and removing images. 

	//declaring the fields.

	JButton removeButton;
	JButton description_edit;
	JButton makePublicButton;
	
	
	
	JButton blurButton;
	JButton sharpenButton;
	JButton grayscaleButton;
	JButton edgeDetectionButton;
	JButton brightnessButton;
	JButton contrastButton;
	JButton discardButton;
	JButton saveButton;
	
	
	JSlider blurSlider;
	JSlider sharpenSlider;
	JSlider grayscaleSlider;
	JSlider edgeDetectionSlider;
	JSlider brightnessSlider;
	JSlider contrastSlider;
	
	int blur_int;
	int sharpen_int;
	int grayscale_int;
	int edgeDetection_int;
	int brightness_int;
	int contrast_int;
	ImageMatrix image_Matrix;
	
	
	
	
	
	
	
	public LocalUserImageFrame(ImageClass image, User user, User viewer) {
		
		
		
		super(image, user, viewer);
		this.user = user;
		this.viewer = viewer;
		
		
		
		removeButton = new JButton("Remove");
		removeButton.setBounds(10, 570, 80, 40);
		leftPanel.add(removeButton);
		removeButton.addActionListener(this);
		
		
		//reading the image from from its name in the imageclass and creating an imagematrix object
		// in order to apply filters to it. 
		try {
			image_Matrix = ImageSecretary.readResourceImage(image.file_name, image.extension_name);
		} catch (IOException e1) {
			e1.printStackTrace();
			BaseLogger.error().log(e1.getMessage()).writeToLog();
		}
		
		
		//settting up a jbutton which allows us to edit the description of an image. 
		description_edit = new JButton("edit");
		description_edit.setBounds(375, 0, 20, 10);
		description_edit.setFont(new Font("Comic Sans", Font.PLAIN, 5));
		description_edit.addActionListener(this);
		super.description_panel.add(description_edit);
		
		
		
		//setting up the slider and button for blur. 
		
		blurButton = new JButton("Apply Blur");
		blurButton.setBounds(10, 10, 80, 40);
		rightPanel.add(blurButton);
		blurButton.addActionListener(this);
		
		
		
		
		blurSlider = new JSlider(0, 5, 1);
		blurSlider.setBounds(5, 50, 90, 40);
		blurSlider.setMajorTickSpacing(1);
		blurSlider.setMinorTickSpacing(1);
		blurSlider.setPaintTicks(true);
		blurSlider.setPaintLabels(true);
		blurSlider.addChangeListener(new ChangeListener() {
		
			
			@Override
			// implementing this method allows us to read the value of the slider tick.
			// and we will assing it to integer variable, and we will use this value to determine 
			// the intensity of the filter(applies for all filters.)
			public void stateChanged(ChangeEvent e) {
				blur_int = blurSlider.getValue();
				
			}
		});
		rightPanel.add(blurSlider);
		
		
		
		
		//setting up the slider and button for sharpen filter.
		sharpenButton = new JButton("Sharpen");
		sharpenButton.setBounds(10, 100, 80, 40);
		rightPanel.add(sharpenButton);
		sharpenButton.addActionListener(this);
		
		
		
		
		sharpenSlider = new JSlider(0, 5, 1);
		sharpenSlider.setBounds(5, 140, 90, 40);
		sharpenSlider.setMajorTickSpacing(1);
		sharpenSlider.setMinorTickSpacing(1);
		sharpenSlider.setPaintTicks(true);
		sharpenSlider.setPaintLabels(true);
		sharpenSlider.addChangeListener(new ChangeListener() {
		
			
			@Override
			public void stateChanged(ChangeEvent e) {
				sharpen_int = sharpenSlider.getValue();
			}
		});
		rightPanel.add(sharpenSlider);
		
		
		
		
		//setting up the slider and button for grayscale filter. 
		grayscaleButton = new JButton("Grayscale");
		grayscaleButton.setBounds(10, 190, 80, 40);
		rightPanel.add(grayscaleButton);
		grayscaleButton.addActionListener(this);
		System.out.println("viewer of image inside local is"  + viewer.nickname + viewer.tier);
		// If the user tier is free or hobbyist the button will be disabled.
		if(viewer.tier.equals("Free")) {
			grayscaleButton.setEnabled(false);
		}
		else if(viewer.tier.equals("Hobbyist")){
			grayscaleButton.setEnabled(false);
		}
		
		
		
		
		grayscaleSlider = new JSlider(0, 5, 1);
		grayscaleSlider.setBounds(5, 230, 90, 40);
		grayscaleSlider.setMajorTickSpacing(1);
		grayscaleSlider.setMinorTickSpacing(1);
		grayscaleSlider.setPaintTicks(true);
		grayscaleSlider.setPaintLabels(true);
		grayscaleSlider.addChangeListener(new ChangeListener() {
		
			
			@Override
			public void stateChanged(ChangeEvent e) {
				grayscale_int = grayscaleSlider.getValue();
			}
		});
		rightPanel.add(grayscaleSlider);
		
		
		
		edgeDetectionButton = new JButton("Edge Detection");
		edgeDetectionButton.setBounds(10, 280, 80, 40);
		rightPanel.add(edgeDetectionButton);
		edgeDetectionButton.addActionListener(this);
		// if the user tier is free or hobbyist the EdgeDetection button will be disabled.
		if(viewer.tier.equals("Free")) {
			edgeDetectionButton.setEnabled(false);
		}
		else if(viewer.tier.equals("Hobbyist")) {
			edgeDetectionButton.setEnabled(false);
		}
		
		
		
		
		
		edgeDetectionSlider = new JSlider(0, 5, 1);
		edgeDetectionSlider.setBounds(5, 320, 90, 40);
		edgeDetectionSlider.setMajorTickSpacing(1);
		edgeDetectionSlider.setMinorTickSpacing(1);
		edgeDetectionSlider.setPaintTicks(true);
		edgeDetectionSlider.setPaintLabels(true);
		edgeDetectionSlider.addChangeListener(new ChangeListener() {
		
			
			@Override
			public void stateChanged(ChangeEvent e) {
				edgeDetection_int = edgeDetectionSlider.getValue();
			}
		});
		rightPanel.add(edgeDetectionSlider);
		
		
		
		
		brightnessButton = new JButton("Brightness");
		brightnessButton.setBounds(10, 370, 80, 40);
		rightPanel.add(brightnessButton);
		brightnessButton.addActionListener(this);
		if(viewer.tier.equals("Free")){
			brightnessButton.setEnabled(false);
		}
		
		
		
		
		brightnessSlider = new JSlider(0, 5, 1);
		brightnessSlider.setBounds(5, 410, 90, 40);
		brightnessSlider.setMajorTickSpacing(1);
		brightnessSlider.setMinorTickSpacing(1);
		brightnessSlider.setPaintTicks(true);
		brightnessSlider.setPaintLabels(true);
		brightnessSlider.addChangeListener(new ChangeListener() {
		
			
			@Override
			public void stateChanged(ChangeEvent e) {
				brightness_int = brightnessSlider.getValue();
			}
		});
		rightPanel.add(brightnessSlider);
		
		
		
		
		contrastButton = new JButton("Contrast");
		contrastButton.setBounds(10, 460, 80, 40);
		rightPanel.add(contrastButton);
		contrastButton.addActionListener(this);
		if(viewer.tier.equals("Free")) {
			contrastButton.setEnabled(false);
		}
		
		
		
		
		contrastSlider = new JSlider(0, 5, 1);
		contrastSlider.setBounds(5, 500, 90, 40);
		contrastSlider.setMajorTickSpacing(1);
		contrastSlider.setMinorTickSpacing(1);
		contrastSlider.setPaintTicks(true);
		contrastSlider.setPaintLabels(true);
		contrastSlider.addChangeListener(new ChangeListener() {
		
			
			@Override
			public void stateChanged(ChangeEvent e) {
				contrast_int = contrastSlider.getValue();
			}
		});
		rightPanel.add(contrastSlider);
		
		// the text on the jbutton about making the images public or private(determines its visibility 
		// in the discover page). 
		// 
		
		
		// if the is_public field of the imageclass object is true than the button will 
		// have the text public on it else it is private
		//we declare the Jbutton, to make it private or public. 
		String is_public_String;
		if(image.is_public) {
			is_public_String = "Public";
			
		}
		else {
			is_public_String = "Private";
		}
		makePublicButton = new JButton(is_public_String);
		makePublicButton.setBounds(10, 550, 80, 40);
		rightPanel.add(makePublicButton);
		makePublicButton.addActionListener(this);
		
		
		// setting up a button to discard the filter changes we made on the image. 
		discardButton = new JButton("Discard");
		discardButton.setBounds(10, 610, 80, 40);
		rightPanel.add(discardButton);
		discardButton.addActionListener(this);
		
		// setting up a button to save the image to any location on the computer. 
		saveButton = new JButton("Save");
		saveButton.setBounds(10, 610, 80, 40);
		leftPanel.add(saveButton);
		saveButton.addActionListener(this);
		
		
		
		
		
		
		
		//creating a duplicate of the image before applying effects
		// we do this in order to, make the discard button functional. 
		ImageSecretary.writeFilteredImageToResources(image_Matrix, image.file_name + "_dup", image.extension_name);
		
		
	}
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// when remove button is clicked the image is deleted from the user.images arraylist and the frame is called again. 
		
		 super.actionPerformed(e);
		 if(e.getSource() == removeButton)    {
		    	int reply = JOptionPane.showConfirmDialog(null, "Do You Wish to Delete this image from your profile?", "Delete Image", JOptionPane.YES_NO_OPTION);
		    	if (reply == JOptionPane.YES_OPTION) {
		    	    user.images.remove(image);
		    	    this.setVisible(false);
		    	    new LocalUserProfilePageFrame(user, viewer);
		    	} 
		
		    	
		 }
		 // the description edit button, calls the editdescription frame. 
		 if(e.getSource() == description_edit) {
				this.setVisible(false);
				new EditDescriptionFrame(image, user, viewer);
				
		}
		 
		 
		 
		 
		 
		 
		 
		 //----------------------------------------Filter Buttons. 
		 
		 
		 
		 // for blur, we get the int value from the slider.
		 // in a for loop we apply the blur method we created in the filtermethods class. 
		 // we then get a new imagematrix with the filters applied and then write this imagematrix as 
		 // an image file.
		 // then we create a new imageicon with this file and assign this imageicon as the imageicon field of the imageclass object.
		 // the procedure is the same for sharpen, grayscale and edge detection
		 // and for brightness and contrast
		 // instead of using a for loop we use the value we get from the sliders and plug them in as an argument
		 // this argument determines a certain value we add to the rgb value, and changes the intenstiy of the filter. 
		 
		 if(e.getSource() == blurButton) {	
			for(int i =0; i< blur_int*10; i++) {
				image_Matrix = FilterMethods.BlurFilter(image_Matrix);
			}
			System.out.println(blur_int);
			image.file_name +=  "_blur";
			ImageSecretary.writeFilteredImageToResources(image_Matrix, image.file_name, image.extension_name);
			ImageIcon icon = new ImageIcon(ImageSecretary.IMAGE_LOCATION + image.file_name + "." + image.extension_name);
			image.image = icon;
			this.setVisible(false);
			BaseLogger.info().log("Blur Applied").writeToLog();
			new LocalUserImageFrame(image, user, viewer); 
			 
		 }
		 
		 
		 if(e.getSource() == sharpenButton) {	
				for(int i =0; i< sharpen_int; i++) {
					image_Matrix = FilterMethods.SharpenFilter(image_Matrix);
				}
				System.out.println(sharpen_int);
				image.file_name += "_sharpen";
				ImageSecretary.writeFilteredImageToResources(image_Matrix, image.file_name, image.extension_name);
				ImageIcon icon = new ImageIcon(ImageSecretary.IMAGE_LOCATION + image.file_name + "." + image.extension_name);
				image.image = icon;
				BaseLogger.info().log("Sharpening Applied").writeToLog();
				this.setVisible(false);
				new LocalUserImageFrame(image, user, viewer); 
				 
			 }
		 
		 if(e.getSource() == grayscaleButton) {	
				for(int i =0; i< grayscale_int; i++) {
					image_Matrix = FilterMethods.GrayscaleFilter(image_Matrix);
				}
				System.out.println(grayscale_int);
				image.file_name += "_grayscale";
				ImageSecretary.writeFilteredImageToResources(image_Matrix, image.file_name, image.extension_name);
				ImageIcon icon = new ImageIcon(ImageSecretary.IMAGE_LOCATION + image.file_name + "." + image.extension_name);
				image.image = icon;
				BaseLogger.info().log("Grayscaling Applied").writeToLog();
				this.setVisible(false);
				new LocalUserImageFrame(image, user, viewer); 
				 
			 }
		 
		 
		 if(e.getSource() == edgeDetectionButton) {	
				for(int i =0; i< edgeDetection_int; i++) {
					image_Matrix = FilterMethods.EdgeDetectionFilter(image_Matrix);
				}
				System.out.println(edgeDetection_int);
				image.file_name += "_edgedetect";
				ImageSecretary.writeFilteredImageToResources(image_Matrix, image.file_name, image.extension_name);
				ImageIcon icon = new ImageIcon(ImageSecretary.IMAGE_LOCATION + image.file_name + "." + image.extension_name);
				image.image = icon;
				BaseLogger.info().log("Edge Detection Filter Applied.").writeToLog();
				this.setVisible(false);
				new LocalUserImageFrame(image, user, viewer); 
				 
			 }
		 
		 if(e.getSource() == brightnessButton) {	
				
				image_Matrix = FilterMethods.BrightnessFilter(image_Matrix, brightness_int * 20);
				image.file_name += "_brightness";
				ImageSecretary.writeFilteredImageToResources(image_Matrix, image.file_name, image.extension_name);
				ImageIcon icon = new ImageIcon(ImageSecretary.IMAGE_LOCATION + image.file_name + "." + image.extension_name);
				image.image = icon;
				BaseLogger.info().log("Brightness Filter Applied.").writeToLog();
				this.setVisible(false);
				new LocalUserImageFrame(image, user, viewer); 
				 
			 }
		 
		 if(e.getSource() == contrastButton) {	
				
				image_Matrix = FilterMethods.ContrastFilter(image_Matrix, contrast_int/1.2);
				System.out.println(contrast_int);
				System.out.println(contrast_int);
				image.file_name += "_contrast";
				ImageSecretary.writeFilteredImageToResources(image_Matrix, image.file_name, image.extension_name);
				ImageIcon icon = new ImageIcon(ImageSecretary.IMAGE_LOCATION + image.file_name + "." + image.extension_name);
				image.image = icon;
				BaseLogger.info().log("Contrast Filter Applied.").writeToLog();
				this.setVisible(false);
				new LocalUserImageFrame(image, user, viewer); 
				 
			 }
		 
		 
		 //writes the imagematrix duplicate we cretaed prior as an image file 
		 // and assings the imageicon we create from it as the imageicon as the imageicon field of the imageclass we have. 
		 
		 if(e.getSource() == discardButton) {
			 image.image = image.icon_dup;
			 try {
				System.out.println(image.file_name_dup); 
				image_Matrix = ImageSecretary.readResourceImage(image.file_name_dup, image.extension_name);
				image.file_name = image.file_name_dup;
				System.out.println(image.file_name);
				BaseLogger.info().log("Filters on Image are Discarded.").writeToLog();
			} catch (IOException e1) {
				e1.printStackTrace();
				BaseLogger.error().log(e1.getMessage()).writeToLog();
			}
			 
			 this.setVisible(false);
			 new LocalUserImageFrame(image, user, viewer);
			 
		 }
		 
		 // using the jfilechooser, we get a path. 
		 // then using this path, we use the helper method i have created which is a slightly modified 
		 // version of the writeImageResources in the samplecode(it does not have a fixed FILE_LOCATION).
		 // then we assign this path and the extension of the existing the image that we want to save to the method. 
		 // and then the image is written. 
		 
		 
		 if(e.getSource() == saveButton) {
			 JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setFileFilter(new ImageFilter());
				
				int response = fileChooser.showSaveDialog(null);
				
				if(response == JFileChooser.APPROVE_OPTION) {
					
					String path = fileChooser.getSelectedFile().getAbsolutePath();
					ImageSecretary.writeImageToResources_helper(image_Matrix, image.extension_name, path);
					
					BaseLogger.info().log("Saved image to " + path).writeToLog();
					JOptionPane.showMessageDialog(null, "Successfully saved image.", "PhotoCloud", JOptionPane.INFORMATION_MESSAGE);
					
				}
		 }
		 
		 //changes the text on the button, and makes the boolean value is_public the reverse 
		 // this boolean value controls the visibility of the images on the discover page
		 // the button is also removed and initialized again in order to be updated on the frame. 
		 
		 if(e.getSource() == makePublicButton) {
			 rightPanel.remove(makePublicButton);
			 image.is_public = !image.is_public;
			 String is_public_String;
				if(image.is_public) {
					BaseLogger.info().log("Image is made public.").writeToLog();
					is_public_String = "Private";
					
				}
				else {
					BaseLogger.info().log("Image is made private.").writeToLog();
					is_public_String = "Public";
				}
				makePublicButton = new JButton(is_public_String);
				makePublicButton.setBounds(10, 550, 80, 40);
				rightPanel.add(makePublicButton);
				makePublicButton.addActionListener(this);
				System.out.println(image.is_public);
			 
			 
		 }
		 
		 
		 
		 
		 
		 
		 
		 
	}

}
