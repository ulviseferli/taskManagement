package com.example.taskmanagement.dto.userDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UpdateUserRequest {
    @NotNull(message = "username can not be null")
    String username;

    @NotNull(message = "email can not be null")
    @Email(message = "email can not be format")
    String email;

    @NotNull(message = "password can not be null")
    String password;

    @NotNull(message = "firstName can not be null")
    String firstName;

    @NotNull(message = "lastName can not be null")
    String lastName;
}
