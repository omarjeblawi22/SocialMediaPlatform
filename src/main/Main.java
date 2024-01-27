/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own
without any help from anyone else. The effort in the project thus belongs
completely to me. I did not search for a solution, or I did not consult any
program written by others or did not copy any program from other sources. I
read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Ömer Maraş, 0082096>
*************************************************************************/


package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import users.Admin;
import users.FreeUser;
import users.HobbyistUser;
import users.ProfessionalUser;
import users.User;

public class Main {
	
	public static ArrayList<User> users = new ArrayList<>();
	public static ArrayList<String> paths = new ArrayList<>();
	private static ArrayList<String> comments;
	/**
	 * THIS MAIN METHOD READS THE TXT FILES AND CREATES THE NEEDED USER, POST AND COMMENT LISTS IN ORDER FOR 
	 * THE APPLICATION TO RUN WITH NO ERROR
	 * @param args
	 */
	public static void main(String[] args) {
		
		 try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\UserDatabase.txt"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] userData = line.split(",");
	                if (userData.length == 8) {
	                    String firstName = userData[0].trim();
	                    String lastName = userData[1].trim();
	                    String age = userData[2].trim();
	                    String email = userData[3].trim();
	                    String username = userData[4].trim();
	                    String password = userData[5].trim();
	                    String profilePic = userData[6].trim();
	                    String userType = userData[7].trim();
	                    if (profilePic.equals("null")) {
	                    	profilePic = "C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\default.jpg";
	                    }

	                    if (userType.equals("FreeUser")) {
	                        FreeUser newFreeUser = new FreeUser(username, password, firstName, lastName, age, email, profilePic);
	                        if (!users.contains(newFreeUser)) {
	                            users.add(newFreeUser);
	                        }
	                    } else if (userType.equals("HobbyistUser")) {
	                        HobbyistUser newHobbyistUser = new HobbyistUser(username, password, firstName, lastName, age, email, profilePic);
	                        if (!users.contains(newHobbyistUser)) {
	                            users.add(newHobbyistUser);
	                        }
	                    } else if (userType.equals("ProfessionalUser")) {
	                        ProfessionalUser newProUser = new ProfessionalUser(username, password, firstName, lastName, age, email, profilePic);
	                        if (!users.contains(newProUser)) {
	                            users.add(newProUser);
	                        }
	                    }
	                      else if (userType.equals("Admin")) {
	                    	Admin newAdmin = new Admin(username, password, firstName, lastName, age, email, profilePic);
	                    	if (!users.contains(newAdmin)) {
	                            users.add(newAdmin);
	                        }
	                    }

	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
		 try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ImageDetails"))) {
			    String line;
			    while ((line = reader.readLine()) != null) {
			        String[] parts = line.split(" ");
			        if (parts.length >= 3) {
			            String key = parts[0];
			            String value = parts[1];
			            String likes = parts[2];
			            int likeCount = Integer.parseInt(likes);
			            for (User user : users) {
			                if (user.getNickname().equals(key)) {
			                    Post addThisPost = new Post(value, key, likeCount, comments);
			                    if (!user.getPostsAsPosts().contains(addThisPost)) {
			                        user.getPostsAsPosts().add(addThisPost);
			                    }
			                }
			            }
			        }
			    }
	        } catch (IOException e) {
	        	
		        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
		            writer.write(e.getMessage());
		            System.out.println("Content written to the file successfully.");
		        } catch (IOException e6) {
		            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
		        }
	            e.printStackTrace();
	        }
		 
		 String filePath = "C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ImageUploadLog";
		 
		 try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			    String line;
			    HashMap<String, ArrayList<String>> dataMap = new HashMap<>(); // Move the declaration here
			    List<String> pathList = new ArrayList<>();
			    while ((line = reader.readLine()) != null) {
			        String[] parts = line.split(" ");
			        if (parts.length >= 2) {
			            String key = parts[0];
			            String value = parts[1];
			            for (User user : users) {
			            	if (user.getNickname().equals(key)){
			            		user.getPosts().add(value);
			            		dataMap.put(key, user.getPosts());
			            	}
			            }
			            paths.add(value);
			        }
			     }
			} catch (IOException e) {
				
		        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
		            writer.write(e.getMessage());
		            System.out.println("Content written to the file successfully.");
		        } catch (IOException e6) {
		            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
		        }
			    System.out.println("An error occurred while reading the file.");
			    e.printStackTrace();
			}
		 
		registerFrame.run();
		
		
		
	}
	

	
	
}
