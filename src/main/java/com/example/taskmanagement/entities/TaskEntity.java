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

    @Column(name="title", length = 40, nullable = false, unique = true)
    private String title;

    @Column(name="description", columnDefinition = "TEXT", length = 2000)
    private String description;

    @Column(name="task_status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name="task_prioroty", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriorty;

    @Column(name="task_weight")
    private Short taskWeigth;

    @Column(name="status",nullable = false)
    private Boolean status;

    @Column(name="created_name",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate(){
        if(taskStatus == null){
            taskStatus = TaskStatus.TODO;
        }

        if(status == null){
            status = true;
        }

        createdAt = LocalDateTime.now();
    }

}
