package com.example.taskmanagement.dto.userDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRequest {
    @NotNull(message = "username can not be null")
    String username;

    @NotNull(message = "email can not be null")
    @Email(message = "email can not be format")
    String email;

    @NotNull(message = "Lirstname can not be null")
    String firstName;

    @NotNull(message = "Lastname can not be null")
    String lastName;


}
