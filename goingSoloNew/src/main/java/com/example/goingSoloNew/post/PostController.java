package com.example.goingSoloNew.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;
	
	@PostMapping("/addPost")
	public ResponseEntity<?> addPost(Authentication authentication, @RequestBody CreatePostRequest createPostRequest) {
		// add Track to backend then 
		
		this.postService.handleCreatePost(authentication.getName(), createPostRequest.getPostText(),
										 createPostRequest.getId(),
										 createPostRequest.getName(),
										 createPostRequest.getArtist(),
										 createPostRequest.getAlbum(),
										 createPostRequest.getYear(),
										 createPostRequest.getAlbumCoverArt());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/allPosts")
	public ResponseEntity<List<PostDTO>> giveAllPosts() {
		return new ResponseEntity<>(this.postService.handleGiveAllPosts(), HttpStatus.OK);
	}
	
	@PostMapping("/certainPosts")
	public ResponseEntity<List<PostDTO>> giveCertainPosts(@RequestBody CertainPostRequest certainPostRequest) {
		// we need to give a list of usernames to user repository and receive a list of MyUsers
		return new ResponseEntity<>(this.postService.handleGivePostsByUsers(certainPostRequest.getListOfUsers()), HttpStatus.OK);
	}
	
}
