package com.example.taskmanagement.dto;

import com.example.taskmanagement.entities.enums.TaskPriority;
import com.example.taskmanagement.entities.enums.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private Integer id;
    private String title;
    private String description;
    private TaskStatus taskStatus = TaskStatus.TODO;
    private TaskPriority taskPriority;
    private Short taskWeight;
    private Boolean status = true;
    private LocalDateTime createdAt = LocalDateTime.now();

}
