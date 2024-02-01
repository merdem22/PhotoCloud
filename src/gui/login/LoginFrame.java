package gui.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Users.User;
import database.Database;
import database.Users;
import gui.profilepage.LocalUserProfilePageFrame;
import gui.profilepage.ProfilePageFrame;
import gui.signup.SignupFrame;
import logger.BaseLogger;
import main.Main;

public class LoginFrame extends JFrame implements ActionListener{
	
	
	JButton registerButton;
	JButton loginButton;
	JTextField nicknameField;
	JTextField passwordField;
	User user_to_log; 
	
	public LoginFrame() {
		
				BaseLogger.info().log("Setting up the Login Page").writeToLog();
				//setting the pixel size of the login frame. 
				this.setSize(new Dimension(1280, 720));

				// makes the program stop running when the frame is closed. 
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
				//using a null layout, which allows me to place every GUI component manually.
				this.setLayout(null);
		
				
				//declaring and initializing the textfields.
				
				
				nicknameField = new JTextField();
				passwordField = new JTextField();
				
				
				//creating a title text
				
				JLabel title_text = new JLabel("Log-in");
				title_text.setBounds(570, 180, 200, 50);
				title_text.setFont(new Font("Futura", Font.BOLD, 35));
				this.add(title_text);
				
				
				
				//setting up the textfields 
				
				nicknameField.setBackground(Color.LIGHT_GRAY);
				nicknameField.setBounds(570 ,270, 140, 40);
				JLabel text_nickname = new JLabel("Nickname:");
				text_nickname.setBounds(500, 280, 100, 20);
				this.add(text_nickname);
				this.add(nicknameField);
				
				passwordField.setBackground(Color.LIGHT_GRAY);
				passwordField.setBounds(570 ,330, 140, 40);
				JLabel text_password = new JLabel("Password:");
				text_password.setBounds(500, 340, 100, 20);
				this.add(text_password);
				this.add(passwordField);
				
				
				

				
				
				//creating a login button 
				
				loginButton = new JButton("Login");
				loginButton.setBounds(590, 390, 100, 40);
				this.add(loginButton);
				loginButton.addActionListener(this);
				
				//creating a button that will lead to the register page.
				
				
				registerButton = new JButton("Register");
				registerButton.setBounds(590, 450, 100, 40);
				this.add(registerButton);
				registerButton.addActionListener(this);

				
				
				
				
				
				
				
				
				
				
				this.setVisible(true);
		
	}
	// this is the required method of the actionlistener interface.
	// here we can listen for mouse presses on buttons and change the course of the program accordingly.
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == registerButton) {
			this.setVisible(false);
			new SignupFrame();
		}
		
		if(e.getSource() == loginButton) {
			// getting the texts as Strings from the textfield
			// and running through all users in the userlist as a for loop
			// and again for all users checking whether if their passwords and nicknames match with 
			// the text in the textfield. 
			String nicknameString = nicknameField.getText();
			String passwordString = passwordField.getText();
			boolean login_success = false;
			for (User user : Main.userlist) {
				if((user.nickname.equals(nicknameString)) && (user.password.equals(passwordString))) {
					login_success = true;
					BaseLogger.info().log("Login Success" + "User = " + user.nickname).writeToLog();
					user_to_log = user;
					this.setVisible(false);
					new LocalUserProfilePageFrame(user_to_log, user_to_log);
					break; 
				}
			}
			if(login_success) {
				JOptionPane.showMessageDialog(null, "Login successful.", "PhotoCloud", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				BaseLogger.error().log("Login Fail: Credentials don't Match.").writeToLog();
				JOptionPane.showMessageDialog(null, "Incorrect credentials", "PhotoCloud", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		
	}
	
	
	
}
