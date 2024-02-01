package gui.profilepage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.color.ProfileDataException;
import java.awt.desktop.AboutHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ProgressBarUI;

import Users.User;
import gui.discoverpage.DiscoverPageFrame;
import gui.editinfo.EditInfoFrame;
import gui.imagepage.ImageFrame;
import gui.imagepage.LocalUserImageFrame;
import image.ImageClass;
import logger.BaseLogger;
import methods.ImageFilter;
import methods.Methods;

public class ProfilePageFrame extends JFrame implements ActionListener{
	
	
	// this class extends from JFrame, and represents the profile page window. 
	// The constructor of this class takes two arguments and they both objects of user class
	// One is viewer and one is the user which the profile page belongs to. 
	// The viewer is the local user in the system.
	User user;
	User viewer;
	JButton discoverButton;
	
	public ProfilePageFrame(User user, User viewer) {
		
		this.user = user;
		this.viewer = viewer;
		this.setSize(1280, 720);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BaseLogger.info().log("Starting the Profile Page.").writeToLog();
	
		
		//displaying the nickname of the user. 
		
		
		JLabel nicknameJLabel = new JLabel(user.nickname);
		nicknameJLabel.setBounds(580, 140, 150, 50);
		nicknameJLabel.setFont(new Font("Futura", Font.PLAIN, 20));
		this.add(nicknameJLabel);
		
		
		//displaying the tier of the user. 
		
		JLabel tierJLabel = new JLabel(user.tier);
		tierJLabel.setBounds(585, 225, 150, 50);
		tierJLabel.setFont(new Font("Futura", Font.PLAIN, 20));
		this.add(tierJLabel);
		
		
		
		//displays the name, surname of the user. 
		
		
		JLabel nameJLabel = new JLabel(user.realName);
		JLabel surnameJLabel = new JLabel(user.surname);
		
		
		nameJLabel.setBounds(550, 170, 100, 50);
		nameJLabel.setFont(new Font("Futura", Font.BOLD, 20));
		this.add(nameJLabel);
		
		surnameJLabel.setBounds(630, 170, 100, 50);
		surnameJLabel.setFont(new Font("Futura", Font.BOLD, 20));
		this.add(surnameJLabel);
		
		
		//displaying the profile picture of the user. 
		
		JLabel profilepic = new JLabel();
		ImageIcon ppIcon = user.profilePic.image;
		ppIcon = Methods.resizeImage(ppIcon, 150, 150);
		profilepic.setIcon(ppIcon);
		profilepic.setBounds(550, 20, 150, 150);
		this.add(profilepic);
		
		
		//displaying the age of the user:
		
		JLabel ageJLabel = new JLabel(user.age);
		ageJLabel.setBounds(640, 195, 100, 50);
		ageJLabel.setFont(new Font("Futura", Font.BOLD, 20));
		this.add(ageJLabel);
		
		JLabel agetextJLabel = new JLabel("Age: ");
		agetextJLabel.setBounds(580, 195, 100, 50);
		agetextJLabel.setFont(new Font("Futura", Font.BOLD, 20));
		this.add(agetextJLabel);
		
		
		
		
		
		
		
		//creating a panel which uses a grid layout to display the images of the user. 
		
		JPanel grid_Panel = new JPanel();
		grid_Panel.setBounds(0, 300, 1280, 420);
		grid_Panel.setLayout(null);
		grid_Panel.setBackground(Color.DARK_GRAY);
		this.add(grid_Panel);
		
		
		//adding the images of user to the panel. 
		//because I wasn't able to set spaces between images and place them however i wished in a grid layout 
		// i came up with this quick algorithm to place images to the panel precisely according to the size of the panel. 
		
		
		//In the profile page the size of the images are 256x210 
		// since the frame size 1280x720, 5 can fit horizontally
		// therefore in a for loop we take count of the images added to the panel. 
		// and when it is more than five we increment the y value by 210 and make the x cor = 0 
		// and in each step of the forloop we create a label which has an image of the user, and add it to 
		// the panel with the specified bounds.
		
		BaseLogger.info().log("Displaying the images on the profile page.").writeToLog();
		int xcor = 0;
		int ycor = 0;
		int count = 1;
		for (ImageClass image : user.images) {
			if(count % 5 == 0) {
				xcor = 0;
				ycor += 210;
			}
			ImageIcon imageIcon = image.image;
			imageIcon = Methods.resizeImage(imageIcon, 256, 210);
			JLabel imageLabel = new JLabel();
			imageLabel.setBounds(xcor, ycor, 256, 210);
			imageLabel.setIcon(imageIcon);
			grid_Panel.add(imageLabel);
			// implementing a mouslistener allows us to listen to mouseclicks on the gui components. 
			// and allows us to open a seperate image window when an image is clicked on. 
			imageLabel.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// since "this" keyword cant be used inside a mouselistener, i cant 
					//directly make references to the frame to use this.setvisible(false)
					//therfore i had to use component and window classes. 
					
					Component component = (Component) e.getSource();
					Window window = SwingUtilities.getWindowAncestor(component);
				    window.setVisible(false);
				    if(viewer == user) {
				    	new LocalUserImageFrame(image, user, user);
				    }
				    else {
				    	new ImageFrame(image, user, viewer);
				    }
					
					
					
				}
			});
			count++; 
			xcor += 256;
		}
	
		
		//adding a button which will take us to the discover page. 
		
		discoverButton = new JButton("Discover Page");
		discoverButton.setBounds(20,20, 125, 50);
		this.add(discoverButton);
		discoverButton.addActionListener(this);
		
		
		
		

				
		
		
		
		
		this.setVisible(true);
	}
	
	
	
	
	

	
	
	
	
	
	
	@Override
	//takes us to the discover page when the discover button is clicked on. 
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == discoverButton) {
			this.setVisible(false);
			new DiscoverPageFrame(viewer);
		}
		

		
	}
	
	

}
