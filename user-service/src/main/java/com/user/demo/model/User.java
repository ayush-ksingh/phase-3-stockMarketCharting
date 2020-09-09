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
public class User {
	
	private Integer userId;
	private String userName;
	private String password;
	private UserType type;
	private String email;
	private String mobileNumber;
	private String confirmed;
}
