package com.user.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.demo.dto.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer>{
	List<UserDTO> findByUserNameAndPassword(String userName, String password);
}
