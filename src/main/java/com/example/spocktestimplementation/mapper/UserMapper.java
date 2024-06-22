package com.example.spocktestimplementation.mapper;

import com.example.spocktestimplementation.model.dto.reqest.UserRequest;
import com.example.spocktestimplementation.model.dto.response.UserResponse;
import com.example.spocktestimplementation.model.entity.User;

public class UserMapper {
    public static UserResponse entityToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .build();
    }

    public static User requestToEntity(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .age(userRequest.getAge())
                .build();
    }
}
