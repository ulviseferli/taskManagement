package com.example.taskmanagement.dto;

import com.example.taskmanagement.entities.enums.TaskPriority;
import com.example.taskmanagement.entities.enums.TaskStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {
     Integer id;
     String title;
     String description;
     TaskStatus taskStatus;
     TaskPriority taskPriority;
     Short taskWeight;
     LocalDateTime createdAt;
}
