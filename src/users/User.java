package users;

import java.util.ArrayList;

import main.Post;

public abstract class User {
	
	private final String nickname;
	private String password;
	private String name;
	private String surname;
	private String age;
	private String mailAddress;
	private String profilePicture;
	public ArrayList<String> posts;
	
	
	public User(String nickname, String password, String name, String surname, String age, String mailAddress, String profilePicture) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.mailAddress = mailAddress;
		this.profilePicture = profilePicture;
	}
	
	public abstract ArrayList<String> getPrivileges();
	
	public abstract ArrayList<String> getPosts();
	
	public abstract ArrayList<Post> getPostsAsPosts();
	
	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getAge() {
		return age;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	
	
	
}
