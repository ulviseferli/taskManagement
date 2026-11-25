package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.PageResponse;
import com.example.taskmanagement.dto.UpdateTaskRequest;
import com.example.taskmanagement.dto.userDtos.*;
import com.example.taskmanagement.entities.TaskEntity;
import com.example.taskmanagement.entities.UserEntity;
import com.example.taskmanagement.exception.*;
import com.example.taskmanagement.mapper.UserMapper;
import com.example.taskmanagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final UserMapper converter;

    public UserService(UserRepository repository, UserMapper converter){
        this.repository = repository;
        this.converter = converter;
    }

    public PageResponse<AllUserResponse> getAll(
            Integer pageNumber,
            Integer pageSize
    )
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Page<UserEntity> page = repository.findAll(pageable);
        List<AllUserResponse> elements = page.getContent()
                .stream()
                .map(converter::toAllUserResponseFromEntity)
                .toList();

        return new PageResponse<AllUserResponse>(
               elements,
               page.getNumber(),
               page.getSize(),
               page.getTotalElements(),
               page.getTotalPages(),
               page.hasNext()
        );
    }

    public void saveUser(AddUserRequest request){
        if(repository.existsByUsernameIgnoreCase(request.getUsername())){
            throw new UserAlreadyExistsException("User already exists");
        }

        repository.save(converter.toEntityFromAddUserRequest(request));
    }

    private UserEntity getEntityById(Integer id){
        return repository.findByIdAndStatusTrue(id).orElseThrow(() -> {
             return new UserNotFoundException("User not found");
        });
    }

    public UserResponse getById(Integer id){
        UserEntity entity = getEntityById(id);

        return converter.toUserResponseFromEntity(entity);
    }

    public void deleteById(Integer id){
        UserEntity entity = getEntityById(id);

        if(entity.getStatus().equals(false)){
            throw new UserAlreadyDeletedException("User already deleted");
        }

        entity.setStatus(false);
        repository.save(entity);
    }

    public void updateUser(Integer id, UpdateUserRequest request){
        UserEntity entity = getEntityById(id);

        if(repository.existsByUsernameIgnoreCaseAndIdNot(request.getUsername(),id)){
            throw new UserAlreadyExistsException("User already exists");
        }

        if(entity.getStatus().equals(false)){
            throw new InvalidUserModificationException("Invalid user modification");
        }

        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());

        repository.save(entity);
    }

    public void changePassword(Integer id, ChangePasswordRequest request){
        UserEntity entity = getEntityById(id);

        if(!entity.getPassword().equals(request.getOldPassword())){
            throw new PasswordMismatchException("Password mismatch");
        }

        if(entity.getPassword().equals(request.getNewPassword())){
            throw  new InvalidUserModificationException("New password must be different old password");
        }

        entity.setPassword(request.getNewPassword());
        repository.save(entity);

    }

    public void activateUser(Integer id){
        UserEntity entity = getEntityById(id);

        if(entity.equals(null)){
            throw  new UserNotFoundException("User not found");
        }

        if(entity.getStatus().equals(Boolean.TRUE)){
            throw new UserAlreadyActiveException("User already active");
        }

        entity.setStatus(true);
        repository.save(entity);
    }



}
