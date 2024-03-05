package com.example.goingSoloNew.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
public class TrackController {
	@Autowired
	private TrackService trackService;
	
	public TrackController() {
		
	}
	
	@PostMapping("/updateUserTop4")
	public ResponseEntity<?> updateUsersTopFour(Authentication authentication, @RequestBody TopFourChangeRequest topFourChangeRequest) {
		trackService.handleUpdateUsersTopFour(authentication.getName(),
											 topFourChangeRequest.getSongToBeChanged(),
											 topFourChangeRequest.getId(),
											 topFourChangeRequest.getName(),
											 topFourChangeRequest.getArtist(),
											 topFourChangeRequest.getAlbum(),
											 topFourChangeRequest.getYear(),
											 topFourChangeRequest.getAlbumCoverArt());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
