package com.barnabasszoke.customerapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.barnabasszoke.customerapp.domain.Authority;
import com.barnabasszoke.customerapp.domain.User;

/**
 * REST controller for managing the current user's account.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

	private final Logger log = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private UserService userService;

	/**
	 * GET - check if the user is authenticated, and return its login.
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	@ResponseBody
	public String isAuthenticated(HttpServletRequest request) {
		log.debug("REST request to check if the current user is authenticated");
		return request.getRemoteUser();
	}

	/**
	 * GET - get the current user.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public UserDTO getAccount(HttpServletResponse response) {
		User user = userService.getUserWithAuthorities();
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}

		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (Authority authority : user.getAuthorities()) {
			roles.put(authority.getName(), Boolean.TRUE);
		}

		return new UserDTO(user.getLogin(), user.getFirstName(), user.getLastName(), user.getEmail(), roles);
	}

	/**
	 * POST - update the current user information.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void saveAccount(@RequestBody User user) throws IOException {
		userService.updateUserInformation(user);
	}

	/**
	 * POST - changes the current user's password
	 */
	@RequestMapping(value = "/change_password", method = RequestMethod.POST)
	public void changePassword(@RequestBody String password, HttpServletResponse response) throws IOException {
		if (password == null || password.equals("")) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Password should not be empty");
		} else {
			userService.changePassword(password);
		}
	}
}
