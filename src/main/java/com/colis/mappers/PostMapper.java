package com.colis.mappers;

import com.colis.dto.PostDTO;
import com.colis.entities.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDTO map(PostEntity post);

    PostEntity map(PostDTO post);

    List<PostDTO> map(List<PostEntity> posts);

    //List<PostEntity> map(List<PostDTO> posts);
}
