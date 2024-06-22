package com.example.spocktestimplementation.service

import com.example.spocktestimplementation.eception.UserNotFoundException
import com.example.spocktestimplementation.model.dto.reqest.UserRequest
import com.example.spocktestimplementation.model.entity.User
import com.example.spocktestimplementation.repository.UserRepository
import com.example.spocktestimplementation.service.impl.UserServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Specification

class UserServiceTest extends Specification {
    private UserServiceImpl userService

    private UserRepository userRepository

    private random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def setup() {
        userRepository = Mock()
        userService = new UserServiceImpl(userRepository)
    }

    def "TestGetUserById success case"() {
        given:
        def id = random.nextObject(Long)
        def user = random.nextObject(User);

        when:
        userService.getUserById(id)

        then:
        1 * userRepository.findById(id) >> Optional.of(user)
    }

    def "TestGetUserById user not found case"() {
        given:
        def id = random.nextObject(Long)

        when:
        userService.getUserById(id)

        then:
        1 * userRepository.findById(id) >> Optional.empty()
        UserNotFoundException userNotFoundException = thrown()
        userNotFoundException.message == "User not found by id = %s".formatted(id)
    }

    def "TestGetAllUsers success case"() {
        given:
        def userList = random.objects(User, 10).toList()

        when:
        def userResponseList = userService.getAllUsers()

        then:
        1 * userRepository.findAll() >> userList
        !userResponseList.isEmpty()
        userList.size() == userResponseList.size()
    }

    def "TestGetAllUsers user not found case"() {
        given:
        def userList = []

        when:
        userService.getAllUsers()

        then:
        1 * userRepository.findAll() >> userList
        UserNotFoundException userNotFoundException = thrown()
        userNotFoundException.message == "User not found"
    }

    def "TestSaveUser success case"() {
        given:
        def userRequest = random.nextObject(UserRequest)

        when:
        userService.saveUser(userRequest)

        then:
        1 * userRepository.save({ User savedUser ->
            savedUser.name == userRequest.name &&
                    savedUser.surname == userRequest.surname &&
                    savedUser.age == userRequest.age
        })
    }

    def "TestUpdateUser success case"() {
        given:
        def id = random.nextObject(Long)
        def userRequest = random.nextObject(UserRequest)

        when:
        userService.updateUser(id, userRequest)

        then:
        1 * userRepository.save({ User savedUser ->
            savedUser.id == id &&
                    savedUser.name == userRequest.name &&
                    savedUser.surname == userRequest.surname &&
                    savedUser.age == userRequest.age
        })
    }
}
