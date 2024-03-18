package com.example.goingSoloNew.user;

import java.util.ArrayList;
import java.util.List;

import com.example.goingSoloNew.myLike.MyLike;
import com.example.goingSoloNew.post.Post;
import com.example.goingSoloNew.track.TrackBasicInfo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// an entity is used with JPA (which basically makes it easier to use java objects/classes with a databse)
// an entity is something that will be a singular record in a table
	// we want a table of users, so user will be an entity

@Entity
@Table (name = "users")
public class MyUser {

	// @Id indicates that this variable will be used as the primary key for this record (to identify it)
	// @GeneratedValue assigns a value for us
		// the strategy = GenerationType.IDENTIFY specifies the strategy for assigning this value
	@Id
	@Column(length = 255)
	private String username;
	
	// @Column allows you to specify things about that column in the table (whether it has to be unique, etc.)
	
	// also have a String name
	@Column(nullable = false, length = 255)
	private String name;
	
	// also have a String email
	@Column(unique = true, nullable = false, length = 255)
	private String email;
	
	@Column(length = 255)
	private String avatar;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private boolean enabled;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "followers",
        joinColumns = @JoinColumn(name = "follower_username"),
        inverseJoinColumns = @JoinColumn(name = "following_username")
    )
    private List<MyUser> followers;
 
    @ManyToMany(mappedBy = "followers")
    private List<MyUser> following;
    
    @OneToMany(mappedBy = "author")
    private List<Post> posts;
    
    @OneToMany(mappedBy = "liker", cascade = CascadeType.REMOVE)
    private List<MyLike> likes;
 
    
//    @ElementCollection // prevents it from being mapped. Just a simple list of strings.
//    private List<String> topFourSongs; // list containing the names of top four songs
    
    @ElementCollection
    @CollectionTable(
        name = "user_recent_activities",
        joinColumns = @JoinColumn(name = "user_username")
    )
    private List<NewActivity> recentActivity;
    
    @ElementCollection
    @CollectionTable(
        name = "user_top_tracks",
        joinColumns = @JoinColumn(name = "user_username")
    )
    @Column(name = "topFour") // If you want to specify a column name for the array
    private TrackBasicInfo[] topFour; // this should be an array, not a list
	
	public MyUser() {

	}
	
	public MyUser(String name, String email, String username, String password) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.avatar = "https://i.imgur.com/GBqcras.png";
		this.enabled = true;
		
		List<MyUser> emptyFollowersList = new ArrayList<MyUser>();
		this.followers = emptyFollowersList;
		List<MyUser> emptyFollowingList = new ArrayList<MyUser>();
		this.following = emptyFollowingList;
	
		List<Post> emptyPostList = new ArrayList<Post>();
		this.posts = emptyPostList;
		
		TrackBasicInfo[] topFourSongs = new TrackBasicInfo[4];
		topFourSongs[0] = new TrackBasicInfo("None", "None", "None", "None", "None");
		topFourSongs[1] = new TrackBasicInfo("None", "None", "None", "None", "None");
		topFourSongs[2] = new TrackBasicInfo("None", "None", "None", "None", "None");
		topFourSongs[3] = new TrackBasicInfo("None", "None", "None", "None", "None");
		
//		topFourSongs.add(new TrackBasicInfo("None", "None", "None"));
//		topFourSongs.add(new TrackBasicInfo("None", "None", "None"));
//		topFourSongs.add(new TrackBasicInfo("None", "None", "None"));
//		topFourSongs.add(new TrackBasicInfo("None", "None", "None"));
		this.topFour = topFourSongs;
		
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}
	
	// if we return a User as a response to a GET request, only the variables with getter methods will appear in the browser
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}

	public String getUsername() {
		return username;
	}

	public List<MyUser> getFollowers() {
		return followers;
	}

	public void setFollowers(List<MyUser> followers) {
		this.followers = followers;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<MyUser> getFollowing() {
		return following;
	}

	public void setFollowing(List<MyUser> following) {
		this.following = following;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<MyLike> getLikes() {
		return likes;
	}

	public void setLikes(List<MyLike> likes) {
		this.likes = likes;
	}

	public List<NewActivity> getRecentActivity() {
		return recentActivity;
	}

	public void setRecentActivity(List<NewActivity> recentActivity) {
		this.recentActivity = recentActivity;
	}

	public TrackBasicInfo[] getTopFour() {
		return topFour;
	}

	public void setTopFour(TrackBasicInfo[] topFour) {
		this.topFour = topFour;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
	
}
