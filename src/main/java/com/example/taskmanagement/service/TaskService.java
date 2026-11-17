package com.example.taskmanagement.service;

import com.example.taskmanagement.converter.TaskConverter;
import com.example.taskmanagement.dto.AddTaskRequest;
import com.example.taskmanagement.dto.AllTaskResponse;
import com.example.taskmanagement.dto.TaskResponse;
import com.example.taskmanagement.dto.UpdateTaskRequest;
import com.example.taskmanagement.entities.TaskEntity;
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
                .map(converter::toAllTaskResponseFromEntity)
                .collect(Collectors.toList());
    }

    private TaskEntity getEntityGetByID(Integer id) {
        return repository.findById(id).get();
    }

    public TaskResponse getById(Integer id) {
        TaskEntity taskEntity = getEntityGetByID(id);

        return converter.toTaskResponseFromEntity(taskEntity);
    }

    public void saveTask(AddTaskRequest requestDto) {
        repository.save(converter.toEntityFromAddTaskRequest(requestDto));
    }

    public void deleteById(Integer id) {
        TaskEntity taskEntity = getEntityGetByID(id);
        taskEntity.setStatus(false);
        repository.save(taskEntity);
    }

    public void updateTask(Integer id, UpdateTaskRequest requestDto) {
        TaskEntity entity = getEntityGetByID(id);
        repository.save(converter.toEntityFromUpdateTaskRequest(entity, requestDto));
    }
}
