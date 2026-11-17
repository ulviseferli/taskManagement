package com.example.taskmanagement.dto;

import com.example.taskmanagement.entities.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AllTaskResponse {
    private Integer id;
    private String Title;
    private TaskStatus taskStatus;
}
