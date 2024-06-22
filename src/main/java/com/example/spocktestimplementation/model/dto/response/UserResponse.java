package com.example.spocktestimplementation.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;

    private String name;

    private String surname;

    private Integer age;
}
