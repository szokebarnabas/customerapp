package com.barnabasszoke.customerapp.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(final String login) {
		log.debug("Authenticating {}", login);
		/* String lowercaseLogin = login.toLowerCase(); */

		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("user"));

		// User user = new User();
		return new org.springframework.security.core.userdetails.User("user", passwordEncoder.encode("user"), authorities);
	}
}
