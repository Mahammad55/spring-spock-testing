package com.example.spocktestimplementation.service;

import com.example.spocktestimplementation.model.dto.reqest.UserRequest;
import com.example.spocktestimplementation.model.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    void saveUser(UserRequest userRequest);

    void updateUser(Long id, UserRequest userRequest);
}
