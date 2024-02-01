package gui.imagepage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.print.attribute.standard.PDLOverrideSupported;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Users.User;
import gui.discoverpage.DiscoverPageFrame;
import gui.profilepage.LocalUserProfilePageFrame;
import gui.profilepage.ProfilePageFrame;
import image.Comment;
import image.ImageClass;
import logger.BaseLogger;
import methods.Methods;

public class ImageFrame extends JFrame implements ActionListener{
	
	ImageClass image;
	JTextField commenttextfield;
	JButton commentButton;
	User user;
	User viewer;
	JPanel panel_2;
	JPanel leftPanel;
	JPanel rightPanel;
	JPanel description_panel;
	JButton userProfileButton; 
	JButton discoverButton;
	
	
	
	// this class represents the larger image window
	// this class takes three arguments which are the image the window is representing,
	// the owner of the image
	// and the local user viewing the page.
	
	
	public ImageFrame(ImageClass image, User user, User viewer) {
		//setting up the image frame.
		
		BaseLogger.info().log("Starting the Image Page.").writeToLog();
		
		this.image = image;
		this.user = user;
		this.viewer = viewer;
		
		
		
		this.setSize(1280, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		this.setResizable(false);
		

		//creating a JPanel in which we will put our label of image and label for comments and edit buttons. 		
		JPanel panel_1 = new JPanel();
		
		panel_1.setBounds(390, 0, 500, 720);
		panel_1.setBackground(Color.darkGray);
		panel_1.setLayout(null);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
		this.add(panel_1);
		
		
		
		
		
		
		
		//creating a label in which we will put our images
		
		
		JLabel iconLabel = new JLabel();
		iconLabel.setBounds(0,0,500,480);
		
		ImageIcon imageIcon = image.image;
		imageIcon = Methods.resizeImage(imageIcon, 500, 480);
		iconLabel.setIcon(imageIcon);
		panel_1.add(iconLabel);
		
		
		
		//creating another panel for adding comments,
		
		panel_2 = new JPanel();
		
		panel_2.setBounds(0, 520, 500, 130);
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setLayout(null);
		panel_1.add(panel_2);
		
		

				
		
		
		
		//creating a panel for commenting a comment. 
		
		
		
		
		JPanel commentingJPanel = new JPanel();
		commentingJPanel.setBounds(390, 650,500,70);
		commentingJPanel.setBackground(Color.DARK_GRAY);
		commentingJPanel.setLayout(null);
		this.add(commentingJPanel);
		
		
		//adding a text field to the panel 
		
		commenttextfield = new JTextField();
		commenttextfield.setBounds(20, 5, 400, 35);
		commenttextfield.setBackground(Color.LIGHT_GRAY);
		commenttextfield.setFont(new Font("Comic Sans", Font.PLAIN, 10));
		commentingJPanel.add(commenttextfield);
		
		
		//adding a submit comment button to the panel. 
		
		commentButton = new JButton("Comment");
		commentButton.setBounds(420, 5, 75, 35);
		commentButton.setBackground(Color.LIGHT_GRAY);
		commentButton.setFont(new Font("Comic Sans", Font.BOLD, 11));
		commentButton.addActionListener(this);
		commentingJPanel.add(commentButton);
		
		
		//creating a panel which we will add labels related to the image liking feature. 
		
		JPanel like_panel = new JPanel();
		like_panel.setBounds(0, 480, 500, 40);
		like_panel.setBackground(Color.DARK_GRAY);
		like_panel.setLayout(null);
		panel_1.add(like_panel);
		
		description_panel = new JPanel();
		description_panel.setBounds(100, 0, 400, 40);
		description_panel.setBackground(Color.white);
		description_panel.setLayout(null);
		like_panel.add(description_panel);
		
		
		//label about the description of the image. 
		
		JLabel description_text = new JLabel(image.getDescription());
		description_text.setBounds(10, 5, 300, 35);
		description_text.setFont(new Font("Comic Sans", Font.PLAIN, 7));
		description_panel.add(description_text);
		
		
		
		
		
		//creating a jlabel for displaying a heart image which we will use to like images. 
		
		
		JLabel white_heart_JLabel = new JLabel();
		white_heart_JLabel.setBounds(5, 5, 35, 30);
		ImageIcon whiteheart = new ImageIcon("userdata/systemimages/heartwhite.png"); 
		whiteheart = Methods.resizeImage(whiteheart, 35, 30);
		white_heart_JLabel.setIcon(whiteheart);
		like_panel.add(white_heart_JLabel);
		
		// jlabel which shows the like count
		
		JLabel like_text = new JLabel(Integer.toString(image.likes) + " likes");
		like_text.setFont(new Font("Comic Sans", Font.BOLD, 13));
		like_text.setForeground(Color.white);
		like_text.setBounds(50, 0, 200, 40);
		like_panel.add(like_text);
		
		// another jlabel for displaying the liked image white heart will be switched to red heart. 
		
		JLabel red_heart_JLabel = new JLabel();
		red_heart_JLabel.setBounds(5, 5, 35, 30);
		ImageIcon redheart = new ImageIcon("userdata/systemimages/heartred.png"); 
		redheart = Methods.resizeImage(redheart, 35, 30);
		red_heart_JLabel.setIcon(redheart);
		red_heart_JLabel.setVisible(false);
		like_panel.add(red_heart_JLabel);
		
		white_heart_JLabel.addMouseListener(new MouseListener() {
			
			//adding a mouse listener to the white heart image
			// allows us to click on it and like the image.
			
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
				// se make the white heart invisible and show the red heart 
				// and we increment the like count of the image. 
				// we also repaint the panel, so that the change are visible.
				BaseLogger.info().log("Image Liked.").writeToLog();
				white_heart_JLabel.setVisible(false);
				image.likes++;
				red_heart_JLabel.setVisible(true);
				like_text.setText(Integer.toString(image.likes) + " likes");
				like_panel.revalidate();
				like_panel.repaint();
			
				
			}
		});
		
		
		
		red_heart_JLabel.addMouseListener(new MouseListener() {
			
			// we also have to add a mouse listener to the red heart
			// because we want to be able to cancel our like on the image. 
			
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
				// makes the white heart visible again, reduces the like count by 1. 
				// repaints the like panel. 
				BaseLogger.info().log("Like taken back.").writeToLog();
				white_heart_JLabel.setVisible(true);
				image.likes--;
				red_heart_JLabel.setVisible(false);
				like_text.setText(Integer.toString(image.likes) + " likes");
				like_panel.revalidate();
				like_panel.repaint();
				
			}
		});
		
		
		
		
		//calling the method we created, to update the comments. 
		
		
		createComments();
		
		
		
		
		
		
		//adding a panel to the left which we will put buttons to, 
		
		
		leftPanel = new JPanel();
		leftPanel.setBounds(280, 0, 100, 720);
		leftPanel.setBackground(Color.lightGray);
		leftPanel.setLayout(null);
		this.add(leftPanel);
		
		
		
		
		// adding a panel to the right which we will again put buttons to. 
		
		
		
		rightPanel = new JPanel();
		rightPanel.setBounds(900, 0, 100, 720);
		rightPanel.setBackground(Color.lightGray);
		rightPanel.setLayout(null);
		this.add(rightPanel);
		
		
		
		
		//adding a return button which will take us back to the profile page
		
		userProfileButton = new JButton(user.nickname);
		userProfileButton.setBounds(10, 20, 80, 40);
		leftPanel.add(userProfileButton);
		userProfileButton.addActionListener(this);
		
		
		
		
		// adding a button which will takes us to the discover page. 
		
		discoverButton = new JButton("Discover");
		discoverButton.setBounds(10, 70, 80, 40);
		leftPanel.add(discoverButton);
		discoverButton.addActionListener(this);
		
		
		
		this.setVisible(true);
		
		

		
	}


	
	
	
	
	
	

	
	public void createComments() {
		
		panel_2.removeAll();
		
		
		//To make code more managable I have decided to create a sepearte method, to display the comments
		// the method works by going over all the comments commented on an image, which is accessed by the 
		// comments arraylist field of a imageclass object.
		// then for each comment, we create panels and labels and add them to the larger panel for all comments
		
		int y_cor = 0; 
		
		BaseLogger.info().log("Displaying the comments.").writeToLog();
		for (Comment comment : image.getComments()) {
			
			//creating a label in which we will put our comments and the profile page of the person who sent it, and the nickname.
			
			
			
			JPanel panel_comment = new JPanel();
			panel_comment.setBounds(0,y_cor,500,40);
			panel_comment.setBackground(Color.LIGHT_GRAY);
			panel_comment.setLayout(null);
			
			//
			
			
			JLabel commentLabel = new JLabel(comment.getText());
			commentLabel.setFont(new Font("Comic Sans", Font.PLAIN, 8));
			commentLabel.setBounds(115 , -2 , 420, 50);
			panel_comment.add(commentLabel);
			
			
			//
			
			
			JLabel nicknameLabel = new JLabel(comment.getUser().nickname + ": ");
			nicknameLabel.setFont(new Font("Comic Sans", Font.BOLD, 10));
			nicknameLabel.setForeground(Color.DARK_GRAY);
			nicknameLabel.setBounds(40, -2 , 460, 50);
			nicknameLabel.addMouseListener(new MouseListener() {
				
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
					//allows us to navigate to the profile page of the user that commented on the image
					// by clicked on its nickname. 
					Component component = (Component) e.getSource();
					Window window = SwingUtilities.getWindowAncestor(component);
				    window.setVisible(false);
				    // checking for admin tiers, and locality of the user.
				    if(viewer.tier.equals("admin")) {
				    	new LocalUserProfilePageFrame(user, viewer);
				    }
				    else if (comment.getUser() == viewer) {
				    	new LocalUserProfilePageFrame(user, viewer);
				    	
				    }
				    else {
				    	new ProfilePageFrame(comment.getUser(), viewer);
				    }
					
				}
			});
			panel_comment.add(nicknameLabel);
			
			
			
			//adds the profile picture of the commenting user to the panel as well. 
			
			JLabel imageJLabel = new JLabel();
			ImageIcon pp = comment.getUser().profilePic.image;
			pp = Methods.resizeImage(pp, 40, 40);
			
			imageJLabel.setIcon(pp);
			imageJLabel.setBounds(0, 2 , 40, 40);
			panel_comment.add(imageJLabel);
			
			
			y_cor += 50;
			
			
		
			panel_2.add(panel_comment);
			
			panel_comment.repaint();
			panel_comment.revalidate();
			
		}
		
		panel_2.revalidate();
		panel_2.repaint();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	
	//the comment button first gets the text from the textfield and the viewer user and creates an instance of a comment class
	// adds this object to the comment list of the imageclass object
	// calls the createcomments method with the renewed commentlist. 
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == commentButton) {
			Comment comment1 = new Comment(viewer, commenttextfield.getText());
			image.getComments().add(comment1);
			commenttextfield.setText("");
	        createComments();
		}
		
		// navigates to the profile page of the owner of the image.
		
		if(e.getSource() == userProfileButton) {
			this.setVisible(false);
			if(viewer.tier.equals("admin")) {
				new LocalUserProfilePageFrame(user, viewer);
			}
			else if(user == viewer) {
				new LocalUserProfilePageFrame(user, viewer);
			}
			else {
				new ProfilePageFrame(user, viewer);
			}
			
		}
		
		//navigates to the discover page. 
		if(e.getSource() == discoverButton) {
			 this.setVisible(false);
			 new DiscoverPageFrame(viewer);
			 
		 }
		
		
	    
	   
	        	        
			
	}
		
	
	
	
}
