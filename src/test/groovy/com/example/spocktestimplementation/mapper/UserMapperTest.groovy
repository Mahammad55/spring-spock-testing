package com.example.spocktestimplementation.mapper

import com.example.spocktestimplementation.model.dto.reqest.UserRequest
import com.example.spocktestimplementation.model.entity.User
import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Specification

class UserMapperTest extends Specification {
    private random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestEntityToResponse success case"() {
        given:
        def user = random.nextObject(User)

        when:
        def userResponse = UserMapper.entityToResponse(user)

        then:
        userResponse.id == user.id
        userResponse.name == user.name
        userResponse.surname == user.surname
        userResponse.age == user.age
    }

    def "TestRequestToEntity success case"() {
        given:
        def userRequest = random.nextObject(UserRequest)

        when:
        def user = UserMapper.requestToEntity(userRequest)

        then:
        user.name == userRequest.name
        user.surname == userRequest.surname
        user.age == userRequest.age
    }
}
