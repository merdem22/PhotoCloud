package gui.discoverpage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.crypto.BadPaddingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Users.User;
import gui.imagepage.ImageFrame;
import gui.imagepage.LocalUserImageFrame;
import gui.profilepage.LocalUserProfilePageFrame;
import gui.profilepage.ProfilePageFrame;
import image.ImageClass;
import logger.BaseLogger;
import main.Main;
import methods.Methods;

public class DiscoverPageFrame extends JFrame implements ActionListener{
	JButton profileButton;
	User viewer;
	
	public DiscoverPageFrame(User viewer) {
		
		// this class is represents the discover page
		// it takes one argument which is the viewer as this is a common page for all users
		// it doesnt require an owner user 
		
		//setting up the frame
		
		
		BaseLogger.info().log("Starting the discover page.").writeToLog();
		
		this.viewer = viewer;
		
		this.setSize(1280, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setBackground(Color.LIGHT_GRAY);
		
		
		//setting up the title panel and text
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0,0, 1280, 80);
		titlePanel.setBackground(Color.DARK_GRAY);
		titlePanel.setLayout(null);
		this.add(titlePanel);
		
		JLabel titletextLabel = new JLabel("Discover");
		titletextLabel.setForeground(Color.white);
		titletextLabel.setFont(new Font("Comic Sans", Font.BOLD, 30));
		titletextLabel.setBounds(550, 20, 300, 40);
		titlePanel.add(titletextLabel);
		
		
		//creating a panel for the images, and we will use a for loop
		// to iterate over all users in the platform and also iterate over each users images
		// to add them to the discover panel
		
		
		JPanel imagesJPanel = new JPanel();
		imagesJPanel.setBounds(0,80, 1280, 640);
		imagesJPanel.setBackground(Color.LIGHT_GRAY);
		imagesJPanel.setLayout(null);
		this.add(imagesJPanel);
		
		// this algorithm is very similar to the one used to paint the pictures of a user in
		// the profile page, however instead of a single user, this goes over all the users in the system
		// and checks whether if the images are made public or not
		// if they are then a panel which consists of the image, image_owner nickname and profile picture
		// is added to the larger panel. 
		
		BaseLogger.info().log("Adding the images to the discover page...").writeToLog();
		int xcor = 0;
		int ycor = 0;
		int count = 1;
		for (User user : Main.userlist) {
			for (ImageClass image : user.images) {
				if(count % 9 == 0) {
					xcor = 0;
					ycor += 160;
				}
				if(image.is_public) {
					JPanel user_Panel = new JPanel();
					user_Panel.setBounds(xcor,ycor+140, 160, 20);
					user_Panel.setLayout(null);
					user_Panel.setBackground(Color.white);
					
					
					JLabel user_nick = new JLabel(user.nickname);
					user_nick.setBounds(40,-5,100, 30);
					user_nick.setFont(new Font("Comic Sans", Font.PLAIN, 8));
					user_Panel.add(user_nick);
					
					
					JLabel user_pp = new JLabel();
					ImageIcon ppIcon = user.profilePic.image;
					ppIcon = Methods.resizeImage(ppIcon, 20, 20);
					user_pp.setIcon(ppIcon);
					user_pp.setBounds(10,0,20,20);
					user_pp.addMouseListener(new MouseListener() {
						
						// adding a mouse listener here, allows us to navigate 
						// to the profile pages of the owners of the profile pictures which we have
						// clicked on. 
						
						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
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
							//again since we can't use the keyword "this" insinde the mouse listener
							// we have to use the component and window classes to close the current window.
							
							Component component = (Component) e.getSource();
							Window window = SwingUtilities.getWindowAncestor(component);
						    window.setVisible(false);
						    // checks whether if the local user is admin if so, local profile frame is called
						    // thus, the profile info can be edited.
						    if (viewer.tier.equals("admin")) {
						    	System.out.println("admin");
						    	new LocalUserImageFrame(image, user, viewer);
						    	
						    }
						    // checks whether if the clicked profile page owner is the local user
						    // if so then the local page is called. 
						    else if(user == viewer) {
						    	new LocalUserProfilePageFrame(user, viewer);
						    	System.out.println("own");
						    }
						    // if the navigated profile page is not the local user than we call the 
						    // regular profile page frame. 
						    else {
						    	new ProfilePageFrame(user, viewer);
						    	System.out.println("regular");
						    }
							
						}
					});
					
					user_Panel.add(user_pp);
					
					
					imagesJPanel.add(user_Panel);
					
					ImageIcon imageIcon = image.image;
					imageIcon = Methods.resizeImage(imageIcon, 160, 140);
					JLabel imageLabel = new JLabel();
					imageLabel.setBounds(xcor, ycor, 160, 140);
					imageLabel.setIcon(imageIcon);
					imagesJPanel.add(imageLabel);
					imageLabel.addMouseListener(new MouseListener() {
						// again just like the profile page frame, to make the images clickable
						// we add mouse listeners to it. 
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
						    if (viewer.tier.equals("admin")) {
						    	new LocalUserImageFrame(image, user, viewer);
						    	System.out.println("admin");
						    }
						    else if(user == viewer) {
						    	new LocalUserImageFrame(image, user, viewer);
						    }
						    else {
						    	new ImageFrame(image, user, viewer);
						    }
							
							
							
						}
					});
					count++; 
					xcor += 160;
				}

			}
			}
			
		
		
		//adding a button which will take us to the local profile page. 
		
		profileButton = new JButton("Profile Page");
		profileButton.setBounds(1100, 20, 120, 40);
		profileButton.addActionListener(this);
		titlePanel.add(profileButton);
		
		
		
		
		
		
		
		
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == profileButton) {
			this.setVisible(false);
			new LocalUserProfilePageFrame(viewer, viewer);
		}
		
	}
	
}
