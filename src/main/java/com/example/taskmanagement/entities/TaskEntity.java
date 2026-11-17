package com.example.taskmanagement.entities;

import com.example.taskmanagement.entities.enums.TaskPriority;
import com.example.taskmanagement.entities.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title", length = 30)
    private String title;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Column(name="task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus = TaskStatus.TODO;

    @Column(name="task_prioroty")
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriorty;

    @Column(name="task_weight")
    private String taskWeigth;

    @Column(name="status")
    private Boolean status;

    @Column(name="created_name")
    private LocalDateTime createdAt = LocalDateTime.now();

}
