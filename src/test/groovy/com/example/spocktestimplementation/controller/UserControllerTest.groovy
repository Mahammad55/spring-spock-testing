package com.example.spocktestimplementation.controller

import com.example.spocktestimplementation.model.dto.reqest.UserRequest
import com.example.spocktestimplementation.model.dto.response.UserResponse
import com.example.spocktestimplementation.service.UserService
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class UserControllerTest extends Specification {
    private UserController userController

    private UserService userService

    private MockMvc mockMvc

    private random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        userService = Mock()
        userController = new UserController(userService)
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build()
    }

    def "TestGetUserById success case"() {
        given:
        def id = random.nextObject(Long)
        def url = "/api/v1/users/{id}/"
        def userResponse = random.nextObject(UserResponse)
        def userJsonResponse = """
            {
                "id" : "$userResponse.id",
                "name" : "$userResponse.name",
                "surname" : "$userResponse.surname",
                "age" : "$userResponse.age"
            }
        """

        when:
        def result = mockMvc.perform(get(url, id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()

        then:
        1 * userService.getUserById(id) >> userResponse
        JSONAssert.assertEquals(userJsonResponse, result.response.contentAsString, true)
    }

    def "TestSaveUser success case"() {
        given:
        def userRequest = random.nextObject(UserRequest)
        def url = "/api/v1/users/"
        def createUserJsonRequest = """
            {
                "name" : "${userRequest.name}",
                "surname" : "${userRequest.surname}",
                "age" : "${userRequest.age}"
            }
        """

        when:
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserJsonRequest))
                .andExpect(status().isCreated())

        then:
        1 * userService.saveUser(userRequest)
    }
}
