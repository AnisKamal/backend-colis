package com.colis.mappers;

import com.colis.dto.ProfileDTO;
import com.colis.entities.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDTO map(ProfileEntity profile);

    ProfileEntity map(ProfileDTO profileDTO);

}
