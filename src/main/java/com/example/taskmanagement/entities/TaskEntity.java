package com.example.taskmanagement.entities;

import com.example.taskmanagement.entities.enums.TaskPriority;
import com.example.taskmanagement.entities.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="title", length = 40, nullable = false, unique = true)
    String title;

    @Column(name="description", columnDefinition = "TEXT", length = 2000)
    String description;

    @Column(name="task_status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    TaskStatus taskStatus;

    @Column(name="task_prioroty", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    TaskPriority taskPriorty;

    @Column(name="task_weight")
    Short taskWeigth;

    @Column(name="status",nullable = false)
    Boolean status;

    @Column(name="created_at",nullable = false,updatable = false)
    LocalDateTime createdAt;

    @Column(name="updated_at",nullable = false)
    LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;


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
