package com.example.taskmanagement.dto;

import com.example.taskmanagement.entities.enums.TaskPriority;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddTaskRequest {
    private String title;
    private String description;
    private TaskPriority taskPriority;
    private Short taskWeight;
}
