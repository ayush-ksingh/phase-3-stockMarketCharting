package com.user.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.dto.UserDTO;
import com.user.demo.model.User;
import com.user.demo.model.UserResponseModel;
import com.user.demo.service.AuthenticationService;
import com.user.demo.service.UserServiceImpl;

@RestController
@RequestMapping("/user-ws")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private AuthenticationService auth;

	@PostMapping("user")
	public UserDTO create(@RequestBody User user) {

		auth.sendEmail(user.getEmail(), user.getUserName());

		return userService.addNewUser(user);
	}

	@PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> userSignup(@RequestBody User user) {
		userService.addNewUser(user);
		return new ResponseEntity<String>("Signup Successful", HttpStatus.CREATED);
	}

//	@PostMapping("/login")
//	public ResponseEntity<UserResponseModel> login(@RequestBody User user) {
//		return new ResponseEntity<>(userService.loginService(user), HttpStatus.OK);
//	}

	@PostMapping(value = "/user/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.updateUserProfile(user), HttpStatus.OK);
	}
}
