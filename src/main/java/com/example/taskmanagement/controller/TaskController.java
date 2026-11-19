package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.AddTaskRequest;
import com.example.taskmanagement.dto.AllTaskResponse;
import com.example.taskmanagement.dto.TaskResponse;
import com.example.taskmanagement.dto.UpdateTaskRequest;
import com.example.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<AllTaskResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public TaskResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PostMapping
    public void saveTask(@RequestBody @Valid AddTaskRequest request){
         service.saveTask(request);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public void updateTask(@PathVariable Integer id, @RequestBody @Valid UpdateTaskRequest request){
        service.updateTask(id,request);
    }


}
