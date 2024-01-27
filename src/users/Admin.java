package users;

import java.util.ArrayList;
import java.util.Arrays;

import main.Main;
import main.Post;

public class Admin extends User{

	ArrayList<String> posts = new ArrayList<>();
	ArrayList<Post> postsAsPosts = new ArrayList<>();
	
	public Admin(String nickname, String password, String name, String surname, String age, String mailAddress,
			String profilePicture) {
		super(nickname, password, name, surname, age, mailAddress, profilePicture);
		this.posts = posts;
		Main.users.add(this);
	}

	@Override
	public ArrayList<String> getPrivileges() {
	    return new ArrayList<>(Arrays.asList("blur", "sharpen", "brightness", "contrast", "grayscale", "edgedetection", "delete"));
	}

	@Override
	public ArrayList<String> getPosts() {
		return posts;
	}

	@Override
	public ArrayList<Post> getPostsAsPosts() {
		return postsAsPosts;
	}


}
