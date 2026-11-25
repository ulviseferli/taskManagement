package com.example.taskmanagement.repository;

import com.example.taskmanagement.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByUsernameIgnoreCase(String username);
    Boolean existsByUsernameIgnoreCaseAndIdNot(String username, Integer id);
    Optional<UserEntity> findByIdAndStatusTrue(Integer id);
}
