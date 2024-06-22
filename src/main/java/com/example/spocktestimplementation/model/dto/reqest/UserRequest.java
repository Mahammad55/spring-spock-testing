package com.example.spocktestimplementation.model.dto.reqest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String name;

    private String surname;

    private Integer age;
}
