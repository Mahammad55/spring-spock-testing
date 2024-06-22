package com.example.spocktestimplementation.service.impl;

import com.example.spocktestimplementation.eception.UserNotFoundException;
import com.example.spocktestimplementation.mapper.UserMapper;
import com.example.spocktestimplementation.model.dto.reqest.UserRequest;
import com.example.spocktestimplementation.model.dto.response.UserResponse;
import com.example.spocktestimplementation.model.entity.User;
import com.example.spocktestimplementation.repository.UserRepository;
import com.example.spocktestimplementation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.spocktestimplementation.mapper.UserMapper.entityToResponse;
import static com.example.spocktestimplementation.mapper.UserMapper.requestToEntity;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return entityToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> userResponseList = userRepository.findAll()
                .stream().map(UserMapper::entityToResponse).toList();
        if (userResponseList.isEmpty()) throw new UserNotFoundException();
        return userResponseList;
    }

    @Override
    public void saveUser(UserRequest userRequest) {
        userRepository.save(requestToEntity(userRequest));
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) {
        User user = requestToEntity(userRequest);
        user.setId(id);
        userRepository.save(user);
    }
}
