package com.example.taskmanagement.dto.userDtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.aspectj.weaver.ast.Not;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.logging.Level;

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddUserRequest {

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
