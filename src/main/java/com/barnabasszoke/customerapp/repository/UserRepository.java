package com.barnabasszoke.customerapp.repository;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.barnabasszoke.customerapp.domain.Authority;
import com.barnabasszoke.customerapp.domain.User;

@Repository
public class UserRepository {

	private User admin;
	private User user;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserRepository() {
		Authority adminAuth = new Authority();
		adminAuth.setName("Admin authority");
		
		Authority userAuth = new Authority();
		userAuth.setName("User authority");
		
		admin = new User();
		admin.setFirstName("Admin firstname");
		admin.setLastName("Admin lastname");
		admin.setEmail("admin@foo.com");
		admin.setLogin("admin");
		admin.setPassword("admin");
		admin.setAuthorities(new HashSet<Authority>(Arrays.asList(adminAuth)));
		
		user = new User();
		user.setFirstName("User firstname");
		user.setLastName("User lastname");
		user.setEmail("user@foo.com");
		user.setLogin("user");
		user.setPassword("user");
		user.setAuthorities(new HashSet<Authority>(Arrays.asList(userAuth)));
		
	}
	public User findOne(String loginName) {
		if(loginName.equals("admin")) {
			return admin;
		} else if(loginName.equals("user")) {
			return user;
		}
		return null;
	}
}
