package com.example.goingSoloNew.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {
	@Autowired
	private UserRepository userRepository;
	
	// given a String username, return a DTO for it
    public MyUserDTO getUserDTO(String username) {
        MyUser user = userRepository.findById(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        return convertToDTO(user);
    }
    
    // given a MyUser, turn it into a DTO
    public MyUserDTO convertToDTO(MyUser user) {
        MyUserDTO dto = new MyUserDTO();
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAvatar(user.getAvatar());
        dto.setFollowersBasicInfo(getBasicUserInfo(user.getFollowers()));
        dto.setFollowingBasicInfo(getBasicUserInfo(user.getFollowing()));
        dto.setTopFour(user.getTopFour());
        dto.setRecentActivity(user.getRecentActivity());
        return dto;
    }
    
    // given a list of MyUsers, return a List of their usernames
    private List<BasicUserInfo> getBasicUserInfo(List<MyUser> users) {
        return users.stream()
                .map(user -> userToBasicUserInfo(user))
                .collect(Collectors.toList());
    }
    
    public BasicUserInfo userToBasicUserInfo(MyUser user) {
    	return new BasicUserInfo(user.getUsername(), user.getName(), user.getAvatar());
    }
}
