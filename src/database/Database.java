package database;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Users.User;
import image.Comment;
import image.ImageClass;
import logger.BaseLogger;

public class Database {
	
	ArrayList<User> userlist;



	
	
	// this is a class which we use as a database
	// Instead of creating the users in the main class we create them here
	// and create an instance of this class at the main class 
	// which makes the code easier to manage.
	// this class basically creates a list of users, and assigns these users
	// their attributes eg(name, age, images....)
	
	
	
	
	
	public Database() {
		
		BaseLogger.info().log("Initializing the database.").writeToLog();
		
		userlist = new ArrayList<User>();
		
		User user1 = new User("Ahmet123", "123456", "Ahmet", "Ozgur", "21", "ahmetozgur@gmail.com", "Hobbyist");	
		User user2 = new User("Hakan01", "password", "Tugcan", "Ozhan", "26", "sugarbaby31@gmail.com", "Free");
		User user3 = new User("Mehmet02", "02Mehmet", "Tugcan", "Ozhan", "26", "sugarbaby31@gmail.com", "Professional");
		User user4 = new User("Ayse03", "03012", "Tugcan", "Ozhan", "26", "sugarbaby31@gmail.com", "Hobbyist");
		User user5 = new User("Y_SelimOz2", "Selim2003", "Tugcan", "Ozhan", "26", "sugarbaby31@gmail.com", "Free");
		User user6 = new User("KaneMane23", "password", "Tugcan", "Ozhan", "26", "sugarbaby31@gmail.com", "Professional");
		User user7 = new User("LegendGamer21", "password", "Tugcan", "Ozhan", "26", "sugarbaby31@gmail.com", "Hobbyist");
		User user8 = new User("PhotoCloudLover34", "password", "Tugcan", "Ozhan", "26", "sugarbaby31@gmail.com", "Free");
		User user9 = new User("Mustafa23", "password", "Tugcan", "Ozhan", "26", "sugarbaby31@gmail.com", "Professional");
		User user10_admin = new User("PhotoCloudAdmin", "IamAdmin", "Admin", "Ozhan", "26", "sugarbaby31@gmail.com", "admin");
		BaseLogger.info().log("Gathering the users.").writeToLog();
		
		//creating comments for images. 
		
		Comment comment1 = new Comment(user2, "Pretty cool image man, Love it, keep up the good work.");
		Comment comment2 = new Comment(user3, "Lovely.");
		Comment comment3 = new Comment(user5, "cool image");
		Comment comment4 = new Comment(user6, "It is beautiful");
		Comment comment5 = new Comment(user7, "That is so pretty");
		Comment comment6= new Comment(user8, "Insert comment here.");
		Comment comment7 = new Comment(user9, "How are you doing little brother.");
		Comment comment8 = new Comment(user1, "How are you doing? man I haven't seen you in a while!");
		Comment comment9 = new Comment(user2, "We should see each other some time.");
		Comment comment10 = new Comment(user3, "Look at that!");
		Comment comment11 = new Comment(user4, "Just found out about this PhotoCloud app and this image truly is a beauty.");
		Comment comment12 = new Comment(user5, "How did you create this image?");
		Comment comment13 = new Comment(user7, "Keep it up.");
		Comment comment14 = new Comment(user4, "Nice :)");
		BaseLogger.info().log("Gathering the comments.").writeToLog();
		
		//Declaring imageicons which are fields of our imageclass objects. 
		
		ImageIcon imageicon1 = new ImageIcon("userdata/systemimages/1.png");
		ImageIcon imageicon2 = new ImageIcon("userdata/systemimages/2.png");
		ImageIcon imageicon3 = new ImageIcon("userdata/systemimages/3.png");
		ImageIcon imageicon4 = new ImageIcon("userdata/systemimages/4.png");
		ImageIcon imageicon5 = new ImageIcon("userdata/systemimages/5.png");
		ImageIcon imageicon6 = new ImageIcon("userdata/systemimages/6.png");
		ImageIcon imageicon7 = new ImageIcon("userdata/systemimages/7.png");
		ImageIcon imageicon8 = new ImageIcon("userdata/systemimages/8.png");
		ImageIcon imageicon9 = new ImageIcon("userdata/systemimages/9.png");
		ImageIcon imageicon10 = new ImageIcon("userdata/systemimages/10.png");
		ImageIcon imageicon11 = new ImageIcon("userdata/systemimages/11.png");
		ImageIcon imageicon12 = new ImageIcon("userdata/systemimages/12.png");
		ImageIcon imageicon13 = new ImageIcon("userdata/systemimages/13.png");
		ImageIcon imageicon14 = new ImageIcon("userdata/systemimages/14.png");
		ImageIcon imageicon15 = new ImageIcon("userdata/systemimages/15.png");
		BaseLogger.info().log("Gathering the images").writeToLog();
		
		
		//creating the imageclass objects.
		
		ImageClass image1 = new ImageClass(imageicon1, "1", "png", true);
		ImageClass image2 = new ImageClass(imageicon2, "2", "png", true);
		ImageClass image3 = new ImageClass(imageicon3, "3", "png", true);
		ImageClass image4 = new ImageClass(imageicon4, "4", "png", true);
		ImageClass image5 = new ImageClass(imageicon5, "5", "png", true);
		ImageClass image6 = new ImageClass(imageicon6, "6", "png", true);
		ImageClass image7 = new ImageClass(imageicon7, "7", "png", true);
		ImageClass image8 = new ImageClass(imageicon8, "8", "png", true);
		ImageClass image9 = new ImageClass(imageicon9, "9", "png", true);
		ImageClass image10 = new ImageClass(imageicon10, "10", "png", true);
		ImageClass image11 = new ImageClass(imageicon11, "11", "png", true);
		ImageClass image12 = new ImageClass(imageicon12, "12", "png", true);
		ImageClass image13 = new ImageClass(imageicon13, "13", "png", true);
		ImageClass image14 = new ImageClass(imageicon14, "14", "png", true);
		ImageClass image15 = new ImageClass(imageicon15, "15", "png", true);
		
		
		
		userlist.add(user1);
		userlist.add(user2);
		userlist.add(user3);
		userlist.add(user4);
		userlist.add(user5);
		userlist.add(user6);
		userlist.add(user7);
		userlist.add(user8);
		userlist.add(user9);
		userlist.add(user10_admin);
		
		//assigning the comments to the images.
		
		
		image1.getComments().add(comment1);
		image2.getComments().add(comment2);
		image3.getComments().add(comment3);
		image4.getComments().add(comment4);
		image6.getComments().add(comment5);
		image7.getComments().add(comment6);
		image8.getComments().add(comment7);
		image9.getComments().add(comment8);
		image10.getComments().add(comment9);
		image11.getComments().add(comment10);
		image12.getComments().add(comment11);
		image13.getComments().add(comment12);
		image14.getComments().add(comment13);
		image15.getComments().add(comment14);
		image1.getComments().add(comment6);
		image8.getComments().add(comment7);
		image11.getComments().add(comment8);
		
		
		// assigning the images to the users. 
		user1.images.add(image1);
		user2.images.add(image2);
		user3.images.add(image3);
		user4.images.add(image4);
		user5.images.add(image5);
		user6.images.add(image6);
		user7.images.add(image7);
		user8.images.add(image8);
		user9.images.add(image9);
		user1.images.add(image10);
		user2.images.add(image11);
		user3.images.add(image12);
		user4.images.add(image13);
		user5.images.add(image14);
		user6.images.add(image15);
		
		
	}





	public ArrayList<User> getUserlist() {
		return userlist;
	}

		
			
	
			
				
				
	
		


		
		
	

}
