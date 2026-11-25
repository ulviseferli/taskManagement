package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.*;
import com.example.taskmanagement.mapper.TaskMapper;
import com.example.taskmanagement.entities.TaskEntity;
import com.example.taskmanagement.entities.enums.TaskStatus;
import com.example.taskmanagement.exception.InvalidTaskModificationException;
import com.example.taskmanagement.exception.TaskAlreadyDeletedException;
import com.example.taskmanagement.exception.TaskAlreadyExistsException;
import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper converter;

    public TaskService(TaskRepository repository, TaskMapper converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public PageResponse<AllTaskResponse> getAll(
            Integer pageNumber,
            Integer pageSize
    ) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Page<TaskEntity> page = repository.findAll(pageable);
        List<AllTaskResponse> elements = page.getContent()
                .stream()
                .map(converter::toAllTasksResponseFromEntity)
                .toList();

        return new PageResponse<AllTaskResponse>(
                elements,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext()
        );
    }

    private TaskEntity getEntityByID(Integer id) {
        return repository.findByIdAndStatusTrue(id).orElseThrow(() -> {
            log.warn("Task not found, id{}",id);
           return new TaskNotFoundException("Task not found");
        });
    }

    public TaskResponse getById(Integer id) {
        TaskEntity taskEntity = getEntityByID(id);

        return converter.toTaskResponseFromEntity(taskEntity);
    }

    public void saveTask(AddTaskRequest requestDto) {
        log.debug("Task saving, title{}",requestDto.getTitle());

        if(repository.existsByTitleIgnoreCase(requestDto.getTitle())){
            log.warn("Task already exists, title{}", requestDto.getTitle());
            throw new TaskAlreadyExistsException("Task already exists");
        }

        TaskEntity entity = repository.save(converter.toEntityFromAddTaskRequest(requestDto));
        log.info("Task created, id{}", entity.getId());
    }

    public void deleteById(Integer id) {
        log.debug("Task deleting, id{}",id);

        TaskEntity taskEntity = getEntityByID(id);
        if(taskEntity.getStatus().equals(false)){
            log.warn("Task already deleted, id{}",id);
            throw new TaskAlreadyDeletedException("Task already deleted");
        }
        taskEntity.setStatus(false);
        repository.save(taskEntity);
        log.info("Task deleted, id{}",id);
    }

    public void updateTask(Integer id, UpdateTaskRequest requestDto) {
        TaskEntity entity = getEntityByID(id);

        log.debug("Task updating, id{}",id);

        if(repository.existsByTitleIgnoreCaseAndIdNot(requestDto.getTitle(), id)) {
            log.warn("Task already exists, id{}, title{}",id,requestDto.getTitle());
            throw new TaskAlreadyExistsException("Task already exists");
        }

        if(entity.getStatus().equals(false) || entity.getTaskStatus().equals(TaskStatus.DONE)) {
            log.warn("Task modification failed,id{},status{},taskStatus{}",id,entity.getStatus(),entity.getTaskStatus());
            throw new InvalidTaskModificationException("Invalid task modification");
        }

        entity.setTitle(requestDto.getTitle().trim());
        entity.setDescription(requestDto.getDescription().trim());
        entity.setTaskStatus(requestDto.getTaskStatus());
        entity.setTaskPriorty(requestDto.getTaskPriority());
        entity.setTaskWeigth(requestDto.getTaskWeight());

        repository.save(entity);
        log.info("Task updated, id{}",id);
    }
}
