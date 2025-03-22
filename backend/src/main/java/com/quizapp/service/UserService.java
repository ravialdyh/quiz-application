package com.quizapp.service;

import com.quizapp.model.dto.UserDto;
import com.quizapp.model.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(UserDto userDto);
    User findByUsername(String username);
    User findById(Long id);
    List<User> findAllUsers();
    User updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}