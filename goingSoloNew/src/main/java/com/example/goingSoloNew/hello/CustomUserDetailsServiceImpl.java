//package com.example.goingSoloNew.auth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.goingSoloNew.user.User;
//import com.example.goingSoloNew.user.UserRepository;
//
//@Service
//public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
//	private UserRepository userRepository;
//	
//	@Autowired
//	public CustomUserDetailsServiceImpl(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(username)
//                .password(user.getPassword())
//                .roles("USER")
//                .build();
//	}
//}
//package com;


