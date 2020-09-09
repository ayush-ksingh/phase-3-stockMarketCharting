package com.user.demo.service;

import com.user.demo.dto.UserDTO;
import com.user.demo.model.User;
import com.user.demo.model.UserResponseModel;

public interface UserService {
	UserDTO addNewUser(User user);
	UserResponseModel loginService(User user);
	String updateUserProfile(User user);
}
