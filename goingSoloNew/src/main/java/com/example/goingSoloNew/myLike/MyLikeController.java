package com.example.goingSoloNew.myLike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class MyLikeController {
	
	@Autowired
	private MyLikeService myLikeService;
	
	public MyLikeController() {
		
	}
	
	@PostMapping("/createLike")
	public ResponseEntity<?> handleCreateLike(Authentication authentication, @RequestBody MyLikeRequest myLikeRequest) {
		myLikeService.handleCreateLike(authentication.getName(), myLikeRequest.getPostId());
		return new ResponseEntity<>(HttpStatus.OK);
		// return the post that was liked
	}
	
	@PostMapping("/deleteLike/{username}/{postId}")
	public ResponseEntity<?> handleDeleteLike(@PathVariable String username, @PathVariable Long postId) {
		myLikeService.handleDeleteLike(username, postId);
		return new ResponseEntity<>(HttpStatus.OK);
		// return the post that was liked
	}
	
}
