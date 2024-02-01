package Users;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import image.ImageClass;

public class User {
	
	//this class represents a user inside the photocloud platform. 
	
	//each user has a nickname,password, name and surname an age,email, a profile picture
	// an list of images and a tier. 
	
	
	
	public String nickname;
	public String password;
	public String realName;
	public String surname;
	public String age;
	public String email;
	public ImageClass profilePic;
	public ArrayList<ImageClass> images = new ArrayList<ImageClass>();
	public String tier;
	
	public User(String nickname, String password, String realName, String surname, String age, String email, ImageClass profilePic, String tier) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.realName = realName;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.profilePic = profilePic;
		this.tier = tier;
	}
	
	public User(String nickname, String password, String realName, String surname, String age, String email, String tier) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.realName = realName;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.tier = tier;
		// if the profile picture is not specified then it is set to a default image. 
		this.profilePic = new ImageClass(new ImageIcon("userdata/systemimages/defaultprofilepicture.png"), "", "", true);
	}
	
	
	
	
}
