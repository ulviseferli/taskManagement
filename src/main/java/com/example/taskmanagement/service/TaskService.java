package com.example.taskmanagement.service;

import com.example.taskmanagement.converter.TaskConverter;
import com.example.taskmanagement.dto.AddTaskRequest;
import com.example.taskmanagement.dto.AllTaskResponse;
import com.example.taskmanagement.dto.TaskResponse;
import com.example.taskmanagement.dto.UpdateTaskRequest;
import com.example.taskmanagement.entities.TaskEntity;
import com.example.taskmanagement.entities.enums.TaskStatus;
import com.example.taskmanagement.exception.InvalidTaskModificationException;
import com.example.taskmanagement.exception.TaskAlreadyDeletedException;
import com.example.taskmanagement.exception.TaskAlreadyExistsException;
import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskConverter converter;

    public TaskService(TaskRepository repository, TaskConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<AllTaskResponse> getAll() {
        return repository.findAll().stream()
                .filter(x -> x.getStatus().equals(true))
                .map(converter::toAllTaskResponseFromEntity)
                .collect(Collectors.toList());
    }

    private TaskEntity getEntityGetByID(Integer id) {
        return repository.findById(id).filter(x -> x.getStatus().equals(true)).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    public TaskResponse getById(Integer id) {
        TaskEntity taskEntity = getEntityGetByID(id);

        return converter.toTaskResponseFromEntity(taskEntity);
    }

    public void saveTask(AddTaskRequest requestDto) {
        if(repository.existsByTitleIgnoreCase(requestDto.getTitle())){
            throw new TaskAlreadyExistsException("Task already exists");
        }
        repository.save(converter.toEntityFromAddTaskRequest(requestDto));
    }

    public void deleteById(Integer id) {
        TaskEntity taskEntity = getEntityGetByID(id);
        if(taskEntity.getStatus().equals(true)){
            throw new TaskAlreadyDeletedException("Task already deleted");
        }
        taskEntity.setStatus(false);
        repository.save(taskEntity);
    }

    public void updateTask(Integer id, UpdateTaskRequest requestDto) {
        TaskEntity entity = getEntityGetByID(id);
        if(repository.existsByTitleAndIdNot(requestDto.getTitle(),id)){
            throw new TaskAlreadyExistsException("Task already exists");
        }

        if(entity.getStatus().equals(false) || entity.getTaskStatus().equals(TaskStatus.DONE)){
            throw new InvalidTaskModificationException("Invalid task modification");
        }

        repository.save(converter.toEntityFromUpdateTaskRequest(entity, requestDto));
    }
}
