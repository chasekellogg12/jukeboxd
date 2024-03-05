package com.example.goingSoloNew.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.goingSoloNew.CustomUserDetailsService;
import com.example.goingSoloNew.post.Post;
import com.example.goingSoloNew.user.NewActivity;
import com.example.goingSoloNew.user.NewInfoRequest;
import com.example.goingSoloNew.user.AuthorityRepository;
import com.example.goingSoloNew.user.CreateUserRequest;
import com.example.goingSoloNew.user.FollowRequest;
import com.example.goingSoloNew.user.LoginRequest;
import com.example.goingSoloNew.user.MyAuthority;
import com.example.goingSoloNew.user.MyUser;
import com.example.goingSoloNew.user.MyUserDTO;
import com.example.goingSoloNew.user.MyUserService;
import com.example.goingSoloNew.user.UserInfoRequest;
import com.example.goingSoloNew.user.UserRepository;

import jakarta.transaction.Transactional;

import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;

// OK HERE IS WHAT THIS DOES:
	// Anyone can send a POST request to /api/register with username and password set in the json body (on talend)
	// Then, to send a POST request to /api/authenticate, you must use a Basic Authorization header and login with a registered username/password
		// these POST requests to /authenticate/ and /register/ return a JWT token to the frontned.
		// all subsequent requests from the frontend must use this JWT token in order to be successful

// IN THE FUTURE:
	// figure out a way to connect to the frontend. Have my own frontend do what Telend does with Basic Authorization header.
		// ie: have frontend put the username/password in the header of the request when requesting to /authorize. 
	// figure out how to log a user out given their JWT token
	// all other requests will require a JWT token, which, when decoded, will contain details about that user
		// to access this token,

@RestController
@RequestMapping("/api")
public class NewAuthController {

    
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private MyUserService myUserService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
	@Autowired
    private JwtEncoder jwtEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    //@Transactional
    public ResponseEntity<JwtRespose> registerUser(@RequestBody CreateUserRequest registrationRequest) {
    //public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest registrationRequest) {
        // Assuming RegistrationRequest is a class with username and password fields

        // You may want to add validation for the request fields before proceeding

        // Create a new user
    	// Use our version of User instead of spring security's so we can include email and name fields
    	// we also need our version of UserDetials so that we can give it to UsernamePasswordAuthenticationToken
//        var user = User.withUsername(registrationRequest.getUsername())
//                .password(passwordEncoder.encode(registrationRequest.getPassword()))
//                .roles("USER")
//                .build();
        
//		var user = User.withUsername(registrationRequest.getUsername())
//			.password(passwordEncoder.encode(registrationRequest.getPassword()))
//			//.passwordEncoder(str -> passwordEncoder.encode(str))
//			.roles("USER")
//			.build();
//
//        // Add the new user to the user details manager
//        
//        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(user);
//        
////        MyUser newUser = new MyUser(registrationRequest.getName(), registrationRequest.getEmail(), registrationRequest.getUsername(), passwordEncoder.encode(registrationRequest.getPassword()));
////    	userRepository.save(newUser);
////        MyAuthority newAuth = new MyAuthority();
////        newAuth.setUsername(newUser.getUsername());
////        newAuth.setAuthority("ROLE_USER");
////        authorityRepository.save(newAuth);
//        
//        // Authenticate the user to obtain the Authentication object
//        // This creates an Authentication object in the same way that the httpBasic does it (I think. We might have to set the security context, but the token seems to work without doing so)
//        
//        // have 2 different repositories: 
//        	// one with jdbcUserDetailsManager which contains Username/Password
//        	// and one with JPA which contains username/email/name
//        
//        UserDetails userDetails = jdbcUserDetailsManager.loadUserByUsername(registrationRequest.getUsername());
//        //UserDetails userDetails = customUserDetailsService.loadUserByUsername(registrationRequest.getUsername());
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        System.out.println(authentication.getPrincipal());
//        System.out.println(authentication.getCredentials());
//        System.out.println(authentication.getName());
    	
	    MyUser newUser = new MyUser(registrationRequest.getName(), registrationRequest.getEmail(), registrationRequest.getUsername(), passwordEncoder.encode(registrationRequest.getPassword()));
		userRepository.save(newUser);
	    MyAuthority newAuth = new MyAuthority();
	    newAuth.setUsername(newUser.getUsername());
	    newAuth.setAuthority("ROLE_USER");
	    authorityRepository.save(newAuth);
    	
    	Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(registrationRequest.getUsername(), registrationRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>(new JwtRespose(createToken(authentication)), HttpStatus.CREATED); // ResponseEntity<String>
    }
    
	@PostMapping("/authenticate") 
	public ResponseEntity<JwtRespose> authenticate(@RequestBody LoginRequest loginRequest) {
    	Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
		
        return new ResponseEntity<>(new JwtRespose(createToken(authentication)), HttpStatus.OK);
	}
	
//	private Authentication authenticateUser(String username, String password) {
//	    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//	    return authenticationManagerBean.authenticate(authenticationToken);
//	}

	private String createToken(Authentication authentication) {
		var claims = JwtClaimsSet.builder() // JwtClaimsSet is a JSON object with details about what the JWT token should convey. You build it with these details
								.issuer("self")
								.issuedAt(Instant.now())
								.expiresAt(Instant.now().plusSeconds(60 * 30))
								.subject(authentication.getName())
								.claim("scope", createScope(authentication))
								.build();
		
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)) // then you create (encode) the token using it with JwtEncoder
						.getTokenValue();
	}

	private String createScope(Authentication authentication) {
		return authentication.getAuthorities().stream() // this takes the authorities (user, admin, etc.) from an authentication and returns a string of these authorities with a " " between them ("admin user")
			.map(a -> a.getAuthority())
			.collect(Collectors.joining(" "));			
	}
	
	record JwtRespose(String token) {} // JwtResponse is basically just a String that doesn't change. Records automatically have getters/setters for its parameters, so we can do JwtResponse.getToken()
	
	@GetMapping("/loggedInUserInfo") // a GET request to /username returns the user from a JWT Token
	public ResponseEntity<MyUserDTO> giveLoggedInUserInfo(Authentication authentication) {
		return new ResponseEntity<>(myUserService.getUserDTO(authentication.getName()), HttpStatus.OK);
		
//		try {
//			String thisUsername = authentication.getName();
//			Optional<MyUser> thisUser = userRepository.findById(thisUsername);
//			return thisUser // if the user isn't in the repository, throw an exception
//	                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
//	                .orElseThrow(() -> new NoSuchElementException("User not found"));
//		} catch (Exception e) {
//	        e.printStackTrace(); // Log the exception
//	        throw e; // Rethrow the exception to see it in the logs
//	    }
	} 
	
	@PostMapping("/loggedInUserInfo")
	public ResponseEntity<?> changeLoggedInUserInfo(Authentication authentication, @RequestBody NewInfoRequest newInfoRequest) {
		MyUser user = userRepository.findById(authentication.getName()).orElse(null);
		if (user != null) {
			user.setName(newInfoRequest.getName());
			user.setEmail(newInfoRequest.getEmail());
			userRepository.save(user);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/allUsernames")
	public ResponseEntity<List<String>> giveAllUserInfo() {
		// return the Strings of the Usernames (and no other info bc this is a .permitAll endpoint).
		return new ResponseEntity<>(userRepository.findAllUsernames(), HttpStatus.OK);
	}
	
	@PostMapping("/userInfo")
	public ResponseEntity<MyUserDTO> giveUserInfo(@RequestBody UserInfoRequest userInfoRequest) {
		return new ResponseEntity<>(myUserService.getUserDTO(userInfoRequest.getThisUsername()), HttpStatus.OK);
		
//		// return the Strings of the Usernames (and no other info bc this is a .permitAll endpoint).
//		Optional<MyUser> thisUser = userRepository.findById(userInfoRequest.getThisUsername());
//		return thisUser // if the user isn't in the repository, throw an exception
//                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
//                .orElseThrow(() -> new NoSuchElementException("User not found"));
	}
	
	@PostMapping("/follow")
	@Transactional
	public ResponseEntity<MyUserDTO> handleFollow(@RequestBody FollowRequest followRequest) {
		// u know what the issue probably is? We probably can't be sending MyUser objects from the front end
		// we should send String usernames from the frontend. Then we should get the MyUsers from the repository with these names
		// then we should alter their follower/following lists.
			// i think the issue is that there are two different objects in the UserRepository because of this
		
		Optional<MyUser> thisFollower = userRepository.findById(followRequest.getFollower());
		MyUser follower = thisFollower
				.map(user -> user)
				.orElseThrow(() -> new NoSuchElementException("User not found"));
		
		Optional<MyUser> thisFollowed = userRepository.findById(followRequest.getFollowed());
		MyUser followed = thisFollowed
				.map(user -> user)
				.orElseThrow(() -> new NoSuchElementException("User not found"));

//		MyUser follower = userRepository.findByfollowRequest.getFollower();
//		MyUser followed = followRequest.getFollowed();
		
		List<MyUser> newFollowing = follower.getFollowing();
		newFollowing.add(followed);
		follower.setFollowing(newFollowing);
		
		List<MyUser> newFollowers = followed.getFollowers();
		newFollowers.add(follower);
		followed.setFollowers(newFollowers);
		
		Calendar c = Calendar.getInstance();
		
		List<NewActivity> newRecentActivity = follower.getRecentActivity();
		newRecentActivity.add(new NewActivity("@" + follower.getUsername(), " followed ", "@" + followed.getUsername(), c.getTime()));
		follower.setRecentActivity(newRecentActivity);
		
		newRecentActivity = followed.getRecentActivity();
		newRecentActivity.add(new NewActivity("@" + followed.getUsername(), " was followed by ", "@" + follower.getUsername(), c.getTime()));
		followed.setRecentActivity(newRecentActivity);
		
	    userRepository.save(follower);
	    userRepository.save(followed);
				
		return new ResponseEntity<>(myUserService.getUserDTO(followed.getUsername()), HttpStatus.OK);
	}
	
	
	// you don't even to handle logout in the backend. Just remove it from local storage on the frontend
	// then, if a user tries to login again, they'll just get a new JWT token. The old one will eventually
	// expire so it won't be sitting somewhere forever
}