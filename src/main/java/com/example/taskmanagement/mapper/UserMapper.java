package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.userDtos.AddUserRequest;
import com.example.taskmanagement.dto.userDtos.AllUserResponse;
import com.example.taskmanagement.dto.userDtos.UserResponse;
import com.example.taskmanagement.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponseFromEntity(UserEntity entity){
        return new UserResponse(
          entity.getId(),
          entity.getUsername(),
          entity.getEmail(),
          entity.getFirstName(),
          entity.getLastName(),
          entity.getCreatedAt()
        );
    }

    public AllUserResponse toAllUserResponseFromEntity(UserEntity entity){
        return new AllUserResponse(
          entity.getId(),
          entity.getUsername(),
          entity.getFirstName()
        );
    }

    public UserEntity toEntityFromAddUserRequest(AddUserRequest request){
        UserEntity entity = new UserEntity();
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());

        return entity;
    }

}
