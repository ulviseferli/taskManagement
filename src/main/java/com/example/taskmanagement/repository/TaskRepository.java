package com.example.taskmanagement.repository;
import com.example.taskmanagement.entities.TaskEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    boolean existsByTitleIgnoreCase(String title);
    boolean existsByTitleIgnoreCaseAndIdNot(String title, Integer id);
    Optional<TaskEntity> findByIdAndStatusTrue(Integer id);
}
