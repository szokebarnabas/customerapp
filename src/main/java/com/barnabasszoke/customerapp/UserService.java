package com.barnabasszoke.customerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import com.barnabasszoke.customerapp.domain.User;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private PersistentTokenRepository persistentTokenRepository;
	
    public User getUserWithAuthorities() {
        User currentUser = new User();
        currentUser.setFirstName("firstname");
        currentUser.setLastName("lastname");
        currentUser.setEmail("email");
        //currentUser.getAuthorities().size(); // eagerly load the association
        return currentUser;
    }
    
}
