package com.example.goingSoloNew;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class JwtSecurityConfig {	

//    @Autowired
//    private DataSource dataSource; 
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
         
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
      
        return authProvider;
    }
	

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new JdbcUserDetailsManager(dataSource);
//    } 
    
    

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    	return authConfig.getAuthenticationManager();
    }
    
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
						auth -> {
							auth.requestMatchers("/api/register", "/api/authenticate", "/api/allUsernames", "/api/userInfo", "/post/allPosts", "/post/certainPosts").permitAll()
							.anyRequest().authenticated();
						});
		 
		http.sessionManagement(
						session -> 
							session.sessionCreationPolicy(
									SessionCreationPolicy.STATELESS)
						);
		
		http.authenticationProvider(authenticationProvider());
		
//		http.httpBasic(withDefaults()); // configures HTTP basic authentication, so the browser must include an Authorization header with Username/Password in its requests
//		
////		http.userDetailsService(customUserDetailsService);
//		
//        // Configure AuthenticationManager using AuthenticationManagerBuilder
//        http.authenticationManager(authenticationManager());
		
		http.csrf(csrf -> csrf.disable());
		
		http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable())); // just allows browser to display content within iFrame (not sure why he included this)
		
		http.oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()));
		// this configures the application as an Oauth2 resource server that can handle JWT tokens, which means it can verify and process tokens (now JWT tokens in particular) presented by clients
		
		
		return http.build();
	}
// READD?:	
//	@Bean
//	public DataSource dataSource() {
//		return new EmbeddedDatabaseBuilder()
//				.setType(EmbeddedDatabaseType.H2)
//				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//				.build();
//	}
//	
//	@Bean
//	public UserDetailsService userDetailService(DataSource dataSource) {
//		
//		// here, we're basically creating a user and an admin
//		// then we're creating a JDBC manager and adding this user and admin to this manager to be stored
//		
//		var user = User.withUsername("in28minutes")
//			//.password("{noop}dummy")
//			.password("dummy")
//			.passwordEncoder(str -> passwordEncoder().encode(str))
//			.roles("USER")
//			.build();
//		
//		var admin = User.withUsername("admin")
//				//.password("{noop}dummy")
//				.password("dummy")
//				.passwordEncoder(str -> passwordEncoder().encode(str))
//				.roles("ADMIN", "USER")
//				.build();
//		
//		//var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//		jdbcUserDetailsManager.createUser(user);
//		jdbcUserDetailsManager.createUser(admin);
//
//		return jdbcUserDetailsManager; // JdbcUserDetailsManager implements UserDetailsService, so we can return it
//	}
	
	
	
// DONT READD:	
//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
	
	@Bean
	public KeyPair keyPair() {
		// this basically creates a key pair bean. a key pair are a pair of keys: a public one and a private one
			// public key is openly shared. You use it to encrypt messages and verify digital signatures
			// private key is used decrypt messages encrypted by the public key and for creating digital signautures
		
		// the pair will be used to sign and verify tokens in a secure manner
		try {
			var keyPairGenerator = KeyPairGenerator.getInstance("RSA"); // uses the RSA algorithm
			keyPairGenerator.initialize(2048);
			return keyPairGenerator.generateKeyPair(); // this 
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Bean
	public RSAKey rsaKey(KeyPair keyPair) {
		// this basically creates a RSAKey bean given a keyPair.
		// a RSAKey is a representation of a JW Key in the Nimbus library
		
		return new RSAKey
				.Builder((RSAPublicKey)keyPair.getPublic()) // you build it with the keyPair's public
				.privateKey(keyPair.getPrivate()) // you give it a corresponding private
				.keyID(UUID.randomUUID().toString()) // you give it a random ID to identify it
				.build();
	}

	@Bean
	public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
		// takes a RSAKey, makes it into a JWK set, and makes a JWTSource which will be used to provide JWKs (JW keys) during authentication/auth
		var jwkSet = new JWKSet(rsaKey);
		
		return (jwkSelector, context) ->  jwkSelector.select(jwkSet);
		
	}
	
	@Bean
	public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
		return NimbusJwtDecoder
				.withPublicKey(rsaKey.toRSAPublicKey())
				.build();
	}
	
	@Bean
	public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
		return new NimbusJwtEncoder(jwkSource);
	}
	
//  private final CustomUserDetailsService customUserDetailsService;
//  
//  private final BCryptPasswordEncoder passwordEncoder;
//
//  @Autowired
//  public JwtSecurityConfig(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder passwordEncoder) {
//      this.customUserDetailsService = customUserDetailsService;
//      this.passwordEncoder = passwordEncoder;
//  }
//
//  @Bean
//  public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//      auth.userDetailsService(customUserDetailsService)
//          .passwordEncoder(passwordEncoder);
//      return auth.build();
//  }
}










