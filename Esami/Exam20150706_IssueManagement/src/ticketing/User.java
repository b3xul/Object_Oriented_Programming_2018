package ticketing;

import java.util.Set;

public class User {

	private String username;
	private Set<IssueManager.UserClass> userClasses;
	private int solved;
	
	public User(String username,Set<IssueManager.UserClass> userClasses) {
		this.username=username;
		this.userClasses=userClasses;
	}

	public String getUsername() {
		return username;
	}
	public Set<IssueManager.UserClass> getUserClasses() {
		return userClasses;
	}
	public void addSolved() {
		this.solved++;
	}
	public int getSolved() {
		return solved;
	}
}
