package com.example.taskmanagement.converter;

import com.example.taskmanagement.dto.AddTaskRequest;
import com.example.taskmanagement.dto.AllTaskResponse;
import com.example.taskmanagement.dto.UpdateTaskRequest;
import com.example.taskmanagement.entities.TaskEntity;
import com.example.taskmanagement.entities.enums.TaskStatus;
import org.springframework.stereotype.Component;
import com.example.taskmanagement.dto.TaskResponse;

import java.time.LocalDateTime;

@Component
public class TaskConverter {
    public TaskResponse toTaskResponseFromEntity(TaskEntity taskEntity){
        return new TaskResponse(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getTaskStatus(),
                taskEntity.getTaskPriorty(),
                taskEntity.getTaskWeigth(),
                taskEntity.getStatus(),
                taskEntity.getCreatedAt()
        );
    }

    public AllTaskResponse toAllTaskResponseFromEntity(TaskEntity taskEntity){
        return new AllTaskResponse(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getTaskStatus()
        );
    }

    public TaskEntity toEntityFromAddTaskRequest(AddTaskRequest requestDto){
        TaskEntity entity = new TaskEntity();
        entity.setTitle(requestDto.getTitle());
        entity.setDescription(requestDto.getDescription());
        entity.setTaskStatus(TaskStatus.TODO);
        entity.setTaskPriorty(requestDto.getTaskPriority());
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());

        return entity;
    }

    public TaskEntity toEntityFromUpdateTaskRequest(TaskEntity entity, UpdateTaskRequest requestDto) {
        entity.setTitle(requestDto.getTitle());
        entity.setDescription(requestDto.getDescription());
        entity.setTaskStatus(requestDto.getTaskStatus());
        entity.setTaskPriorty(requestDto.getTaskPriority());
        entity.setTaskWeigth(requestDto.getTaskWeight());

        return entity;
    }
}
