package gui.signup;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.TileObserver;
import java.io.File;
import java.time.temporal.JulianFields;

import methods.ImageFilter;
import methods.Methods;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Users.User;
import database.Database;
import database.Users;
import gui.login.LoginFrame;
import image.ImageClass;
import logger.BaseLogger;
import main.Main;


public class SignupFrame extends JFrame implements ActionListener{
	
	
	//declaring these fields outside the constructor because we will use them in methods of this class. 
	JButton submit_button;
	JButton back_button;
	JTextField textfield_nickname;
	JTextField textfield_password;
	JTextField textfield_name;
	JTextField textfield_surname;
	JTextField textfield_email;
	JTextField textfield_age;
	JLabel error_message;
	JLabel success_message;
	JButton pp_Button;
	ImageClass pp = new ImageClass(new ImageIcon("userdata/systemimages/defaultprofilepicture.png"), "defaultpicture", "png", true);
	
	
	
	
	public SignupFrame() {
		
		BaseLogger.info().log("Setting up the Signup Page.").writeToLog();
		
		//setting the pixel size of the signup frame. 
		this.setSize(new Dimension(1280, 720));

		// makes the program stop running when the frame is closed. 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//using a null layout, which allows me to place every GUI component manually.
		this.setLayout(null);
		
		
		//initializing the textfields
		textfield_nickname = new JTextField();
		textfield_password = new JTextField();
		textfield_name = new JTextField();
		textfield_surname = new JTextField();
		textfield_email = new JTextField();
		textfield_age = new JTextField();
		
		
		
		
		//Putting a a title text for the SignUp page. 
		
		JLabel title_text = new JLabel("Sign-Up");
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
		
		pp_Button = new JButton("Upload a Profile Picture (Optional)");
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
		
		//getting the text from the textfields as strings
		if(e.getSource() == submit_button) {
			String nicknameString = textfield_nickname.getText();
			String passwordString = textfield_password.getText();
			String nameString = textfield_name.getText();
			String surnameString = textfield_surname.getText();
			String ageString = textfield_age.getText();
			String emailString = textfield_email.getText();
			
			
			// we look whether if the textfields are empty or not, whether if the age is numerical. 
			// If the input is correct then with the strings we get, we pass them as arguments to the User Constructor and create a new User object
			// we also add the new user object to the userlist 
			if((nicknameString.length() !=0 && nameString.length() !=0)&&(surnameString.length() !=0 && ageString.length() !=0)&&(emailString.length()!= 0 && Methods.isNumerical(ageString))&& passwordString.length() !=0) {
				System.out.println("success.");
				BaseLogger.info().log("New user signup " + "User: " + nicknameString).writeToLog();
				// block of code to add information to database and create user.
				User user = new User(nicknameString, passwordString, nameString, surnameString, ageString, emailString, pp, "Free");
				Main.userlist.add(user);
				

				JOptionPane.showMessageDialog(null, "Account Created.", "PhotoCloud", JOptionPane.INFORMATION_MESSAGE);
				
				this.setVisible(false);
				this.setVisible(true);
				
			}
			else {
				System.out.println("incorrect.");
				BaseLogger.error().log("Signup Failed: Invalid Input").writeToLog();
				JOptionPane.showMessageDialog(null, "Invalid Input.", "Signup Error.", JOptionPane.ERROR_MESSAGE);
				
								
				this.setVisible(false);
				this.setVisible(true);
				// block of code to show red text highlighting incorrect input.
			}
		}
		
		if(e.getSource() == back_button) {
			this.setVisible(false);
			new LoginFrame();
		}
		
		if(e.getSource() == pp_Button){
			JFileChooser fileChooser = new JFileChooser();
			
			fileChooser.setFileFilter(new ImageFilter());
			
			int response = fileChooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				BaseLogger.info().log("Profile Picture Chosen.").writeToLog();
				ImageIcon ppicon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
				pp = new ImageClass(ppicon, "", "", false);
			}
			
		}
		
	}

}
