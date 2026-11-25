package com.example.taskmanagement.dto.userDtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordRequest {
    @NotNull(message = "required")
    String oldPassword;

    @NotNull(message = "required")
    String newPassword;
}
