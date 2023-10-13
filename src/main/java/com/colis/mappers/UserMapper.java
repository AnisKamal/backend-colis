package com.colis.mappers;

import com.colis.dto.UserDTO;
import com.colis.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO map(UserEntity user);

    UserEntity map(UserDTO user);

}
