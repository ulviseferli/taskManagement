package com.example.taskmanagement.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "username", nullable = false, length = 30)
    String username;

    @Column(name = "email", nullable = false, length = 100)
    String email;

    @Column(name = "password",nullable = false, length = 150)
    String password;

    @Column(name = "firstName", length = 50)
    String firstName;

    @Column(name = "lastName",  length = 50)
    String lastName;

    @Column(name = "status", nullable = false)
    Boolean status;

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<TaskEntity> tasks = new ArrayList<>();

    @PrePersist
    public void onCreate(){
        if(status == null){
            status = true;
        }

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

}
