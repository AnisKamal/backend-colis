package com.colis.mappers;

import com.colis.dto.UserDTO;
import com.colis.entities.UserEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest {

    @Test
    public void map_PostEntityToPostDTO(){
        //given
        UserEntity userEntity = createTestUserEntity();

        //when
        UserDTO userDTO = UserMapper.INSTANCE.map(userEntity);

        //then
        assertThat(userDTO).isNotNull();
        assertThat(userDTO.name()).isEqualTo("name");
        assertThat(userDTO.email()).isEqualTo("test@email.com");
        assertThat(userDTO.urlPhoto()).isEqualTo("testUrlPhoto");
    }

    @Test
    public void map_PostDTOtoPostEntity(){
        //given
        UserDTO userDTO = createUserDTO();
        //when
        UserEntity userEntity = UserMapper.INSTANCE.map(userDTO);
        //then
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getName()).isEqualTo("name");
        assertThat(userEntity.getEmail()).isEqualTo("test@email.com");
        assertThat(userEntity.getUrlPhoto()).isEqualTo("testUrlPhoto");
    }

    private UserEntity createTestUserEntity(){

        UserEntity user = new UserEntity();

        user.setName("name");
        user.setEmail("test@email.com");
        user.setUrlPhoto("testUrlPhoto");
        return user;
    }

    private UserDTO createUserDTO(){
        UserDTO userDTO = new UserDTO("1",
                "test@email.com",
                "name",
                "testUrlPhoto");
        return userDTO;
    }
}
