package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.PageResponse;
import com.example.taskmanagement.dto.userDtos.*;
import com.example.taskmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping
    public PageResponse<AllUserResponse> getAll(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        return service.getAll(pageNumber,pageSize);
    }

    @GetMapping("{id}")
    public UserResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PostMapping
    public void saveUser(@RequestBody @Valid AddUserRequest request){
        service.saveUser(request);
    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody UpdateUserRequest request){
        service.updateUser(id,request);
    }

    @PostMapping("{id}")
    public void changePassword(@PathVariable Integer id, @RequestBody @Valid ChangePasswordRequest request){
        service.changePassword(id,request);
    }

    @PatchMapping("{id}")
    public void activateUser(@PathVariable Integer id){
        service.activateUser(id);
    }
}
