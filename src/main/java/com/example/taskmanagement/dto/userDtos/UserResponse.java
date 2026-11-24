package com.example.taskmanagement.dto.userDtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Integer id;
    String username;
    String email;
    String firstName;
    String lastName;
    LocalDateTime createdAt;
}
