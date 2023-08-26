package com.colis.mappers;

import com.colis.dto.ContactDTO;
import com.colis.entities.ContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDTO map(ContactEntity contactEntity);

    ContactEntity map(ContactDTO contactDTO);

}
