package com.ps.Backend.Mapper.Impl;

import com.ps.Backend.Entity.Book;
import com.ps.Backend.Entity.User;
import com.ps.Backend.Mapper.UserMapper;
import com.ps.Common.DTO.UserDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        return user;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
