package com.example.goingSoloNew.track;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.goingSoloNew.post.PostRepository;
import com.example.goingSoloNew.user.MyUser;
import com.example.goingSoloNew.user.UserRepository;

@Service
public class TrackService {
	@Autowired
	private TrackRepository trackRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
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
	
	public List<Rand10> createRand10() {
		List<Rand10> result = new ArrayList<>();
		Random random = new Random();
		// get all the tracks and put them into a list
		List<Track> allTracks = trackRepository.findAll();
		if (allTracks.size() == 0) {
			return result;
		}
		int min = 0;
		int max = allTracks.size();
		
		Set<Integer> randomIndices = new HashSet<>();
		
		while (randomIndices.size() < 10 && randomIndices.size() < max) {
			randomIndices.add(random.nextInt(min, max));
		}
		
		for (int uniqueInt : randomIndices) {
			Track thisTrack = allTracks.get(uniqueInt);
			Float avgRating = postRepository.findAverageRating(thisTrack.getTrackId());
			Rand10 newRand10 = new Rand10((avgRating == null ? 0.0f : avgRating), trackToTrackBasicInfo(thisTrack));
			result.add(newRand10);
		}
		
		// randomly select 10 unique integers between 0 and length of list-1
		// for each of these 10 unique integers:
			// find the average rating across rows in table Posts where track_id == allTracks[randomInt].getTrackId().
			// make a Rand10 using this track and this average rating
			// if there are no rows in the posts table with this track (which is possible), make sure that the average rating is 0
		// 
		
		return result;
	}
	
	private TrackBasicInfo trackToTrackBasicInfo(Track thisTrack) {
		return new TrackBasicInfo(thisTrack.getTrackId(), thisTrack.getName(), thisTrack.getAlbumCoverArt(), thisTrack.getArtist(), thisTrack.getYear());
	}
	
}
