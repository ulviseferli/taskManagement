package com.example.taskmanagement.dto.userDtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AllUserResponse {
    Integer id;
    String username;
    String firstName;
}
