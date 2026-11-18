package com.example.taskmanagement.repository;
import com.example.taskmanagement.entities.TaskEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    boolean existsByTitleIgnoreCase(String title);
    boolean existsByTitleAndIdNot(String title, Integer id);
}
