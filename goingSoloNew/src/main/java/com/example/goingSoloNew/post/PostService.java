package com.example.goingSoloNew.post;

import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goingSoloNew.myLike.MyLike;
import com.example.goingSoloNew.track.Track;
import com.example.goingSoloNew.track.TrackBasicInfo;
import com.example.goingSoloNew.track.TrackRepository;
import com.example.goingSoloNew.user.BasicUserInfo;
import com.example.goingSoloNew.user.MyUser;
import com.example.goingSoloNew.user.MyUserDTO;
import com.example.goingSoloNew.user.MyUserService;
import com.example.goingSoloNew.user.NewActivity;
import com.example.goingSoloNew.user.UserRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TrackRepository trackRepository;
	
	@Autowired
	private MyUserService myUserService;
	
    public PostDTO getPostDTO(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        return convertToDTO(post);
    }
    
    // given a MyUser, turn it into a DTO
    private PostDTO convertToDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setPostId(post.getPostId());
        dto.setAuthor(myUserService.convertToDTO(post.getAuthor()));
        dto.setText(post.getText());
        dto.setDatePosted(post.getDatePosted());
        dto.setUsernamesWhoLiked(likesToUsernameList(post.getLikes()));
        dto.setStringUsernamesWhoLiked(likesToStringUsernameList(post.getLikes()));
        Track subject = post.getPostSubject();
        dto.setPostSubjectBasicInfo(new TrackBasicInfo(subject.getTrackId(), subject.getName(), subject.getAlbumCoverArt(), subject.getArtist(), subject.getYear()));
        return dto;
    }
    
    private List<String> likesToStringUsernameList(List<MyLike> likes) {
    	// take in a list of MyLikes
    	// for each MyLike, get its liker MyUser. Convert this to BasicUserInfo. Then add this to result
    	return likes.stream()
	        .map(like -> like.getLiker().getUsername())
	        .collect(Collectors.toList()); 
    }
    
    private List<MyUserDTO> likesToUsernameList(List<MyLike> likes) {
    	// take in a list of MyLikes
    	// for each MyLike, get its liker MyUser. Convert this to BasicUserInfo. Then add this to result
    	return likes.stream()
	        .map(like -> myUserService.convertToDTO(like.getLiker()))
	        .collect(Collectors.toList()); 
    }
	
	public void handleCreatePost(String author, String postText,
								  String id,
								  String name,
								  String artist,
								  String album,
								  String year,
								  String albumCoverArt) {
		Optional<MyUser> thisUser = userRepository.findById(author);
		MyUser authorUser = thisUser
				.map(user -> user)
				.orElseThrow(() -> new NoSuchElementException("User not found"));
		
		Track newTrack = trackRepository.findById(id).orElse(new Track(id, name, artist, album, year, albumCoverArt));
		trackRepository.save(newTrack);
		
		Post newPost = new Post(authorUser, postText, newTrack);
		this.postRepository.save(newPost);
		Calendar c = Calendar.getInstance();
		List<NewActivity> newRecentActivity = authorUser.getRecentActivity();
		newRecentActivity.add(new NewActivity("@" + authorUser.getUsername(), " created a review of ", newPost.getPostSubject().getName(), c.getTime()));
		authorUser.setRecentActivity(newRecentActivity);
		this.userRepository.save(authorUser);
	}
	
	public List<PostDTO> handleGiveAllPosts() { // we want to take a list of posts and turn it into a list of post DTOs. So for each post in the list of Posts, do this
		List<Post> posts = this.postRepository.findAll();
		List<PostDTO> postList = posts.stream()
				.map(post -> convertToDTO(post))
				.collect(Collectors.toList());
		return postList;
	}
	
	public List<PostDTO> handleGivePostsByUsers(List<String> listOfUsernames) {
		List<MyUser> listOfUsers = userRepository.findByUsernames(listOfUsernames);
		
		List<Post> posts = this.postRepository.findByAuthorsIn(listOfUsers);
		List<PostDTO> postList = posts.stream()
				.map(post -> convertToDTO(post))
				.collect(Collectors.toList());
		return postList;
	}
}
