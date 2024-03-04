package com.example.goingSoloNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.goingSoloNew.user.MyUser;
import com.example.goingSoloNew.user.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Build and return a Spring Security User object
        return User.withUsername(myUser.getUsername())
                .password(myUser.getPassword()) // Ensure you have a password field in MyUser
                .authorities("ROLE_USER") // Set authorities or roles
                .build();
        
//        JdbcUserDetailsManager
    }
}
 