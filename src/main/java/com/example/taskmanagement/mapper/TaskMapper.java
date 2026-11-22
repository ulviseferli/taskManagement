package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.AddTaskRequest;
import com.example.taskmanagement.dto.AllTaskResponse;
import com.example.taskmanagement.dto.UpdateTaskRequest;
import com.example.taskmanagement.entities.TaskEntity;
import com.example.taskmanagement.entities.enums.TaskStatus;
import org.springframework.stereotype.Component;
import com.example.taskmanagement.dto.TaskResponse;

import java.time.LocalDateTime;

@Component
public class TaskMapper {
    public TaskResponse toTaskResponseFromEntity(TaskEntity taskEntity) {
        return new TaskResponse(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getTaskStatus(),
                taskEntity.getTaskPriorty(),
                taskEntity.getTaskWeigth(),
                taskEntity.getCreatedAt()
        );
    }

    public AllTaskResponse toAllTasksResponseFromEntity(TaskEntity taskEntity) {
        return new AllTaskResponse(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getTaskStatus()
        );
    }

    public TaskEntity toEntityFromAddTaskRequest(AddTaskRequest requestDto) {
        TaskEntity entity = new TaskEntity();
        Short taskWeight = requestDto.getTaskWeight() == null ? 0 : requestDto.getTaskWeight();

        entity.setTitle(requestDto.getTitle().trim());
        entity.setDescription(requestDto.getDescription().trim());
        entity.setTaskStatus(TaskStatus.TODO);
        entity.setTaskPriorty(requestDto.getTaskPriority());
        entity.setTaskWeigth(taskWeight);
        entity.setStatus(true);

        return entity;
    }
}
