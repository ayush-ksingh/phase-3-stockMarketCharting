package com.user.demo.model;

import com.user.demo.dto.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseModel {
	
	private String userName;
	private UserType type;

}
