package users;

import java.util.ArrayList;
import java.util.Arrays;

import main.Main;
import main.Post;

public class FreeUser extends User{

	ArrayList<String> posts = new ArrayList<>();
	ArrayList<Post> postsAsPosts = new ArrayList<>();

	public FreeUser(String nickname, String password, String name, String surname, String age, String mailAddress,
			String profilePicture) {
		super(nickname, password, name, surname, age, mailAddress, profilePicture);
		Main.users.add(this);
	}

	@Override
	public ArrayList<String> getPrivileges() {
		return new ArrayList<>(Arrays.asList("blur", "sharpen"));
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
