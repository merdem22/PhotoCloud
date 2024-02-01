package gui.editinfo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Users.User;
import database.Users;
import gui.login.LoginFrame;
import gui.profilepage.LocalUserProfilePageFrame;
import gui.profilepage.ProfilePageFrame;
import image.ImageClass;
import logger.BaseLogger;
import methods.ImageFilter;
import methods.Methods;

public class EditInfoFrame extends JFrame implements ActionListener{
	
	User user;
	User viewer;
	JButton submit_button;
	JButton back_button;
	JTextField textfield_nickname;
	JTextField textfield_password;
	JTextField textfield_name;
	JTextField textfield_surname;
	JTextField textfield_email;
	JTextField textfield_age;
	JButton pp_Button;
	ImageClass pp = new ImageClass(new ImageIcon("userdata/systemimages/defaultprofilepicture.png"), "", "", true);
	
	
	
	public EditInfoFrame(User user, User viewer) {
		BaseLogger.info().log("Opening the EditInfo page.").writeToLog();
		
		//setting the pixel size of the signup frame. 
		this.viewer = viewer;
		this.user = user;
		this.setSize(new Dimension(1280, 720));

		// makes the program stop running when the frame is closed. 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
		//using a null layout, which allows me to place every GUI component manually.
		this.setLayout(null);
				
				
		// setting up the textfields 
		textfield_nickname = new JTextField(user.nickname);
		textfield_password = new JTextField(user.password);
		textfield_name = new JTextField(user.realName);
		textfield_surname = new JTextField(user.surname);
		textfield_email = new JTextField(user.email);
		textfield_age = new JTextField(user.age);
				
				
				
				
		//Putting a a title text for the SignUp page. 
				
		JLabel title_text = new JLabel("Edit Information");
		title_text.setBounds(570, 50, 200, 50);
		title_text.setFont(new Font("Futura", Font.BOLD, 30));
		this.add(title_text);
			
				
				
				// setting up the textfield for nickname 
				
		textfield_nickname.setBackground(Color.LIGHT_GRAY);
		textfield_nickname.setBounds(570 ,120, 140, 40);
		JLabel text_nickname = new JLabel("Nickname:");
		text_nickname.setBounds(500, 130, 100, 20);
		this.add(text_nickname);
		this.add(textfield_nickname);
				
				
				
				// setting up the textfield for nickname 
				
		textfield_password.setBackground(Color.LIGHT_GRAY);
		textfield_password.setBounds(570 ,180, 140, 40);
		JLabel text_password= new JLabel("Password:");
		text_password.setBounds(500, 190, 100, 20);
		this.add(text_password);
		this.add(textfield_password);
				
				
				// setting up the textfield for real_name
				
		textfield_name.setBackground(Color.LIGHT_GRAY);
		textfield_name.setBounds(570 ,240, 140, 40);
		JLabel text_name = new JLabel("Real Name:");
		text_name.setBounds(500, 250, 100, 20);
		this.add(text_name);
		this.add(textfield_name);
				
				
				//setting up the textfield for surname
				
		textfield_surname.setBackground(Color.LIGHT_GRAY);
		textfield_surname.setBounds(570 ,300, 140, 40);
		JLabel text_surname = new JLabel("Surname:");
		text_surname.setBounds(500, 310, 100, 20);
		this.add(text_surname);
		this.add(textfield_surname);
				
				//setting up the textfield for the email address.
				
		textfield_email.setBackground(Color.LIGHT_GRAY);
		textfield_email.setBounds(570 ,360, 140, 40);
		JLabel text_email = new JLabel("Mail Address:");
		text_email.setBounds(480, 370, 100, 20);
		this.add(text_email);
		this.add(textfield_email);
				
				
				//setting up the textfield for the age
				
		textfield_age.setBackground(Color.LIGHT_GRAY);
		textfield_age.setBounds(570 ,420, 140, 40);
		JLabel text_age = new JLabel("Age:");
		text_age.setBounds(500, 430, 100, 20);
		this.add(text_age);
		this.add(textfield_age);
				
				
				//setting up a button for uploading a profile picture. 
				
		pp_Button = new JButton("Change Profile Picture");
		pp_Button.setBounds(520, 480, 250, 40);
		this.add(pp_Button);
		pp_Button.addActionListener(this);
				
				
				
				//declaring a submit button to create an account with the given input.
				
		submit_button = new JButton("Submit");
		submit_button.setBounds(590, 540, 100, 40);
		this.add(submit_button);
		submit_button.addActionListener(this);
				
				
				
				//creating a back button which will take us back to the login page. 
				
		back_button = new JButton("Return");
		back_button.setBounds(40, 40, 100, 40);
		this.add(back_button);
		back_button.addActionListener(this);
				
				
				
				
				
		this.setResizable(false);
		this.setVisible(true);
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
				
		// we get the information from the textfields
		// and assign them to the fields of the existing user object.
		if(e.getSource() == submit_button) {
			String nicknameString = textfield_nickname.getText();
			String passwordString = textfield_password.getText();
			String nameString = textfield_name.getText();
			String surnameString = textfield_surname.getText();
			String ageString = textfield_age.getText();
			String emailString = textfield_email.getText();
					
					
			// we check for the validity of the entered input. 		
			if((nicknameString.length() !=0 && nameString.length() !=0)&&(surnameString.length() !=0 && ageString.length() !=0)&&(emailString.length()!= 0 && Methods.isNumerical(ageString))&& passwordString.length() !=0) {
				System.out.println("success.");
				user.nickname = nicknameString;
				user.password = passwordString;
				user.realName = nameString;
				user.surname = surnameString;
				user.age = ageString;
				user.email = emailString;
				

				
				JOptionPane.showMessageDialog(null, "Account Information Changed.", "PhotoCloud", JOptionPane.INFORMATION_MESSAGE);
				BaseLogger.info().log("User information changed.").writeToLog();		
				this.setVisible(false);
				new EditInfoFrame(user, viewer);
						
			}
			else {
				System.out.println("incorrect.");
						
				JOptionPane.showMessageDialog(null, "Invalid Input.", "Error", JOptionPane.ERROR_MESSAGE);
				
										
				this.setVisible(false);
				new EditInfoFrame(user, viewer);
						
			}
		}
		// will take us back to the profile page without editing info. 
		if(e.getSource() == back_button) {
			this.setVisible(false);
			new LocalUserProfilePageFrame(user, viewer);
		}
		// changes the profile picture of the user. 
		if(e.getSource() == pp_Button){
			JFileChooser fileChooser = new JFileChooser();
					
			fileChooser.setFileFilter(new ImageFilter());
					
			int response = fileChooser.showOpenDialog(null);
					
			if(response == JFileChooser.APPROVE_OPTION) {
				ImageIcon ppicon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
				ImageClass pp = new ImageClass(ppicon, "", "", true);
				user.profilePic = pp;
				BaseLogger.info().log("Profile Picture changed.").writeToLog();
			
			}
			
					
		}
				
	
	} 
		


}
