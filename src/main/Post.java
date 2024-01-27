package main;

import java.util.ArrayList;

public class Post {

	
	private String path;
	private String owner;
	private int likes;
	private ArrayList<String> comments;
	
	public Post(String path, String owner, int likes, ArrayList<String> comments) {
		super();
		this.path = path;
		this.owner = owner;
		this.likes = likes;
		this.comments = comments;
		
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public ArrayList<String> getComments() {
		return comments;
	}
	
	public void addComment(String comment) {
		comments.add(comment);
	}
}
