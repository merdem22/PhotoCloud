package gui.imagepage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.lang.model.element.NestingKind;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Users.User;
import image.ImageClass;

public class EditDescriptionFrame extends JFrame implements ActionListener{
	
	User user;
	User viewer;
	JButton submit_button;
	JTextField editDescription;
	ImageClass image;
	
	public EditDescriptionFrame(ImageClass image, User user, User viewer) {
		this.user = user; 
		this.image = image; 
		this.viewer = viewer;
		this.setSize(1280, 720);
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		this.setResizable(false);
		
		
		
		JLabel title = new JLabel("Edit Description");
		title.setFont(new Font("Comic Sans", Font.BOLD, 30));
		title.setForeground(Color.black);
		title.setBounds(510, 50, 300, 150);
		this.add(title);
		
		editDescription = new JTextField(image.description);
		editDescription.setBounds(440, 200, 400, 150);
		this.add(editDescription);
		
			
		
		submit_button = new JButton("Submit");
		submit_button.setBounds(580, 400, 100, 50);
		submit_button.addActionListener(this);
		this.add(submit_button);
		
		
		
		
		
		
		
		
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit_button) {
			String descString = editDescription.getText();
			image.description = descString;
			this.setVisible(false);
			new LocalUserImageFrame(image, user, viewer);
			
			
		}
		
	}
	

}
