package com.example.goingSoloNew.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.goingSoloNew.user.MyUser;
import com.example.goingSoloNew.user.UserRepository;

@Service
public class TrackService {
	@Autowired
	private TrackRepository trackRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public TrackService() {
		
	}
	
	public void handleUpdateUsersTopFour(String username,
													  int songToBeChanged,
													  String id,
													  String name,
													  String artist,
													  String album,
													  String year,
													  String albumCoverArt) {
		
		MyUser user = userRepository.findById(username).orElse(null);
		Track newTrack = trackRepository.findById(id).orElse(new Track(id, name, artist, album, year, albumCoverArt));
		trackRepository.save(newTrack);
		
		if (user != null) {
			user.getTopFour()[songToBeChanged] = new TrackBasicInfo(id, name, albumCoverArt, artist, year);
			userRepository.save(user);
		}
	}
	
}
