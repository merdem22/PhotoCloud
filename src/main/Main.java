package main;

import gui.signup.SignupFrame;
import image.Comment;
import image.ImageClass;
import logger.BaseLogger;

import java.awt.Image;
import java.awt.desktop.UserSessionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Users.User;
import database.Database;
import database.Users;
import gui.discoverpage.DiscoverPageFrame;
import gui.login.*;
import gui.profilepage.LocalUserProfilePageFrame;
import gui.profilepage.ProfilePageFrame;



/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own
without any help from anyone else. The effort in the project thus belongs
completely to me. I did not search for a solution, or I did not consult any
program written by others or did not copy any program from other sources. I
read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Mert Erdem, 83078>
*************************************************************************/




public class Main {
	
	public static Database database = new Database();
	
	public static ArrayList<User> userlist = database.getUserlist();
	
	public static void main(String[] args) {
	
		
		
	
	BaseLogger.info().log("Application Started").writeToLog();
	LoginFrame frame = new LoginFrame();

	}

}
