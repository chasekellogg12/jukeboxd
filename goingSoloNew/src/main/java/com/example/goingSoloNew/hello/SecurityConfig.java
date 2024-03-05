//package com.example.goingSoloNew.hello;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.interfaces.RSAKey;
//import java.security.interfaces.RSAPublicKey;
//import java.util.UUID;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//// this configures how security is applied to http requests
////@Configuration
////@EnableWebSecurity
//public class SecurityConfig {
//	
////	private UserDetailsService userDetailsService;
////	
////    public SecurityConfig(UserDetailsService userDetailsService) {
////        this.userDetailsService = userDetailsService;
////    }
//	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//	
//	@Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf->csrf.disable())
//        	.authorizeHttpRequests((authz) -> authz // .authorizeHttpRequest configures security settings based on request authorization
//        		//.requestMatchers("/**").permitAll()
//        		//.requestMatchers("/api/", "/api/public/**").permitAll() // requests to / and /public/... are allowed without authentication
//        		//.requestMatchers(HttpMethod.POST, "/api/login").permitAll() // allows POST requests to /login
//        		//.requestMatchers(HttpMethod.POST, "/api/register").permitAll() // allows POST requests to /login
//        		.anyRequest().authenticated() 
//
//            );
//        http.httpBasic(withDefaults());
////        	.formLogin(formLogin -> formLogin // .formLogin configures form based authentication
////        		.loginPage("/api/login") // specifies that the login page is at /login. If user tries to access security resoruce, they'll be redirected here
////        		.permitAll() // gives all users access to login page
////        	)
////        	.logout(logout -> logout
////        		.logoutUrl("/api/logout")
////        		.permitAll()
////        	);
//
//        return http.build();
//    }
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails userDetails = User.withUsername("chase")
//			.password("password")
//			.passwordEncoder(str -> passwordEncoder().encode(str))
//			.roles("USER")
//			.build();
//
//		return new InMemoryUserDetailsManager(userDetails); // we can give JdbcManager instead
//	}
//	
//	@Bean
//	public AuthenticationManager authenticationManager(
//			UserDetailsService userDetailsService,
//			PasswordEncoder passwordEncoder) {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//		return new ProviderManager(authenticationProvider);
//	}
//
//	
//
//    // If you want to configure authentication, you can override configure(AuthenticationManagerBuilder auth) method.
//}
