package image;

import Users.User;

//this class is basic class that represents a comment.
// a comment has a user that owns the comment 
// and a text of type string which is the content of the comment. 
// comments are kept in arraylist inside an instance of an imageclass.

public class Comment {
	
	User user; 
	String text;
	
	public Comment(User user, String text) {
		this.user = user;
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String comment) {
		this.text = comment;
	}
	
	
	

}
