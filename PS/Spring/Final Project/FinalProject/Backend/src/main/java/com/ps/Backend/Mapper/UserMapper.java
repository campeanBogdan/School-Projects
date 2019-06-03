package com.ps.Backend.Mapper;

import com.ps.Backend.Entity.User;
import com.ps.Common.DTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    //@Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);
}
