package com.example.taskmanagement.dto;

import com.example.taskmanagement.entities.enums.TaskPriority;
import com.example.taskmanagement.entities.enums.TaskStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTaskRequest {
  private String title;
  private String description;
  private TaskStatus taskStatus = TaskStatus.TODO;
  private TaskPriority taskPriority;
  private String taskWeight;
}
