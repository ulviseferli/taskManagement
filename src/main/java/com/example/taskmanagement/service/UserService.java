package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.PageResponse;
import com.example.taskmanagement.dto.userDtos.AllUserResponse;
import com.example.taskmanagement.entities.UserEntity;
import com.example.taskmanagement.mapper.UserMapper;
import com.example.taskmanagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
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
}
