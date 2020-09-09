package com.user.demo.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.demo.dao.UserRepository;
import com.user.demo.dto.UserDTO;
import com.user.demo.model.User;
import com.user.demo.model.UserResponseModel;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public UserDTO addNewUser(User user) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return userRepository.save(mapper.map(user, UserDTO.class));
	}
	
	@Transactional
	public UserResponseModel loginService(User user) {
		UserDTO userDTO = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword()).iterator().next();
		UserResponseModel userResponseModel = null;
		if(userDTO != null) {
			userResponseModel = new UserResponseModel();
			if (userDTO.getConfirmed()=="yes") {
				// authenticate user
			}
		}
		return userResponseModel;
			
	}
	
	public String updateUserProfile(User user) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		userRepository.save(mapper.map(user, UserDTO.class));
		return "Profile Updated";
	}
	
}
