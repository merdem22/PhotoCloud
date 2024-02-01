package gui.profilepage;

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

import Users.User;
import gui.discoverpage.DiscoverPageFrame;
import gui.editinfo.EditInfoFrame;
import gui.login.LoginFrame;
import image.ImageClass;
import logger.BaseLogger;
import methods.ImageFilter;
import samplecodes.ImageSecretary;

public class LocalUserProfilePageFrame extends ProfilePageFrame{

	JButton editinfoButton;
	JButton uploadButton;
	JButton logOutButton;
	
	// this class is a subclass of the profilepageframe class and is called when 
	// the local user (viewer) is the same asa the profile owning user.
	// This class has additional features such as (editing profile info, uploading images and logging out.)
	
	public LocalUserProfilePageFrame(User user, User viewer) {
		super(user, viewer);
		
		
		//adding a button which will allow us to edit our profile information. 
		
		editinfoButton = new JButton("Edit Information");
		editinfoButton.setBounds(1100, 30, 125, 50);
		this.add(editinfoButton);
		editinfoButton.addActionListener(this);
		
		// adding a button which allolws us to upload images. 
		
		uploadButton = new JButton("Upload Image");
		uploadButton.setBounds(1100, 120, 125, 50);
		this.add(uploadButton);
		uploadButton.addActionListener(this);
		
		//adding a button for logging out. 
		
		logOutButton = new JButton("Log Out");
		logOutButton.setBounds(1100, 210, 125, 50);
		this.add(logOutButton);
		logOutButton.addActionListener(this);
		
		
		
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == editinfoButton) {
			this.setVisible(false);
			new EditInfoFrame(user, viewer);
		}
		
		
		if(e.getSource() == uploadButton) {
			// the JFileChooser allows us to choose files from the computer we are using.
			JFileChooser fileChooser = new JFileChooser();
			// Using the ImageFilter, we can make only files that contain the extension related to
			// images(eg: png,jpeg) choosable.
			fileChooser.setFileFilter(new ImageFilter());
			
			int response = fileChooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				// one we choose an image from the jfilechooser
				// we get its name, its path and its extension and we create a new ImageClass object
				// ImageClass is a helper class I have created which represents an Image in PhotoCloud.
				ImageIcon imageicon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
				String fileName = fileChooser.getSelectedFile().getName();
				String extension = "";
				//we get the full name of the file and get its extension and name substrings.
				int extensionIndex = fileName.lastIndexOf('.');
				if (extensionIndex > 0 && extensionIndex < fileName.length() - 1) {
				    extension = fileName.substring(extensionIndex + 1);
				    fileName = fileName.substring(0, extensionIndex);   
				}
				//We can upload any image to the profilepage, however in order to add filters to it
				// we have to use the sample codes given to us, which are ImageSecretary and ImageMatrix
				// and they only work in the specified IMAGE_LOCATION
				// therefore we copy and paste the image we have selected to the specified IMAGE_LOCATION.
				Path src = Paths.get(fileChooser.getSelectedFile().getAbsolutePath());
				Path dest = Paths.get(ImageSecretary.IMAGE_LOCATION + fileName + "." + extension);
				try {
					Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
					BaseLogger.info().log("File copied to " + dest).writeToLog();
				} catch (IOException e1) {
					e1.printStackTrace();
					BaseLogger.error().log(e1.getMessage()).writeToLog();
				}
				ImageClass image = new ImageClass(imageicon, fileName, extension, true);
				user.images.add(image);
				JOptionPane.showMessageDialog(null, "Successfully uploaded image.", "PhotoCloud", JOptionPane.INFORMATION_MESSAGE);
				BaseLogger.info().log("Sucessfully uploaded image to PhotoCloud").writeToLog();
			}
			
			
			
			
			this.setVisible(false);
			new LocalUserProfilePageFrame(user, viewer);
			// to update the frame we make the current frame invisible and create a new instance of the
			// same frame. 
		}
		
		if (e.getSource() == logOutButton) {
			this.setVisible(false);
			// this button closes this frame and opens a new loginframe.
			new LoginFrame();
		}
		

	}
	
	
}
