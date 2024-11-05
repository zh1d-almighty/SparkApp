package com.project.start.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.start.dto.UserDto;
import com.project.start.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
    
    ResponseEntity<?> confirmEmail(String confirmationToken);

	void updateProfile(UserDto userDto);
}
