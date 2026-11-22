package com.example.taskmanagement.dto;

import com.example.taskmanagement.entities.enums.TaskStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AllTaskResponse {
     Integer id;
     String Title;
     TaskStatus taskStatus;
}
